package com.example.hospitalplaneer.PageController;


import ClassesDAO.DoctorsDAO;
import ClassesDAO.PatientsDAO;
import basicClasses.DoctorUser;
import basicClasses.DoctorsSchedule;
import com.example.hospitalplaneer.Services.DoctorPageService;
import com.example.hospitalplaneer.Services.PatientPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PatientsPageController {
    @GetMapping(value = "/patientsPage")
    public String patientsPage() {
        return "patientsPage";
    }

    public static List<DoctorsSchedule> appointments = new ArrayList<>();

    @Autowired
    private PatientPageService makeAppointmentService;

    public PatientsPageController() throws SQLException {
//        appointments = DoctorsDAO.returnAllSchedules();
    }

    @GetMapping("/appointment")
    public String greetingForm(Model model) {
        model.addAttribute("personForm", new DoctorsSchedule());
        return "makeAppointment";
    }

    @PostMapping("/appointment")
    public String greetingSubmit(@ModelAttribute DoctorsSchedule scheduleForm, Model model) throws SQLException {
        String errorMessage = new String();
        model.addAttribute("personForm", scheduleForm);
        System.out.println(scheduleForm.toString());

        boolean correctInput = makeAppointmentService.checkInputFormUpdateAppointment(scheduleForm);

        if (!correctInput) {
            //TODO: maybe display sth like you messed up, try again
            errorMessage = DoctorPageService.getErrorMessage();
            scheduleForm.setErrorMessage(errorMessage);
            return "makeAppointment";
        }
        //TODO: adauga in baza de date - pt appointments
        PatientsDAO.insertAppointment(scheduleForm);

        return "results";
    }



}
