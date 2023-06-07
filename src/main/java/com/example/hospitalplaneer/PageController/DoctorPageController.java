package com.example.hospitalplaneer.PageController;

import ClassesDAO.DoctorsDAO;
import basicClasses.DoctorUser;
import basicClasses.DoctorsSchedule;
import basicClasses.Table;
import com.example.hospitalplaneer.Services.DoctorPageService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ClassesDAO.DoctorsDAO.returnAllSchedules;

@Controller
@RequestMapping("/doctors")
public class DoctorPageController implements WebMvcConfigurer {

    public static List<DoctorsSchedule> schedules = new ArrayList<>();

    public static List<DoctorUser> doctors = new ArrayList<>();

    @Autowired
    private DoctorPageService updateScheduleService;

    public DoctorPageController() throws SQLException {
        schedules = DoctorsDAO.returnAllSchedules();
        doctors = DoctorsDAO.returnAllDoctors();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    @GetMapping("/schedule")
    public String greetingForm(Model model) {
        model.addAttribute("personForm", new DoctorsSchedule());
        return "doctorsUpdateSchedule";
    }

    @PostMapping("/schedule")
    public String greetingSubmit(@ModelAttribute DoctorsSchedule scheduleForm, Model model) throws SQLException {
        String errorMessage = new String();
        model.addAttribute("personForm", scheduleForm);
        System.out.println(scheduleForm.toString());

        boolean correctInput = updateScheduleService.checkInputFormUpdate(scheduleForm);

        if (!correctInput) {
            //TODO: maybe display sth like you messed up, try again
            errorMessage = DoctorPageService.getErrorMessage();
            scheduleForm.setErrorMessage(errorMessage);
            return "doctorsUpdateSchedule";
        }
        //TODO: adauga in baza de date
        DoctorsDAO.insertSchedule(scheduleForm);

        return "results";
    }

    //    the page where the user selects to view or to modify their schedule
    @GetMapping(value = "/doctorsPage")
    public String doctorsPage() {
        return "doctorsPage";
    }

}
