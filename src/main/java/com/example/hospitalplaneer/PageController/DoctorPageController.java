package com.example.hospitalplaneer.PageController;

import ClassesDAO.DoctorsDAO;
import basicClasses.DoctorsSchedule;
import com.example.hospitalplaneer.Services.DoctorPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/doctors")
@Controller
public class DoctorPageController implements WebMvcConfigurer {

    private List<DoctorsSchedule> schedules = new ArrayList<>();

    @Autowired
    private DoctorPageService updateScheduleService;

    public DoctorPageController() throws SQLException {
//        schedules = DoctorsDAO.returnAllSchedules();
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
    public String greetingSubmit(@ModelAttribute DoctorsSchedule personForm, Model model) throws SQLException {
        String errorMessage = new String();
        model.addAttribute("personForm", personForm);
        System.out.println(personForm.toString());

        boolean correctInput = updateScheduleService.checkInputFormUpdate(personForm);

        if (!correctInput) {
            //TODO: maybe display sth like you messed up, try again
            errorMessage = DoctorPageService.getErrorMessage();
            personForm.setErrorMessage(errorMessage);
            return "doctorsUpdateSchedule";
        }
        return "results";
    }

    //    the page where the user selects to view or to modify their schedule
    @GetMapping(value = "/doctorsPage")
    public String doctorsPage() {
        return "doctorsPage";
    }

    @GetMapping("/{id}") //TODO: see why this does not work
    public DoctorsSchedule getSchedule(@PathVariable("id") int id) {
        if (schedules.isEmpty()) {
            System.out.println("Sorry, not good!");
        }
        return schedules.stream()
                .filter(p -> p.getIdDoctor() == id).findFirst().orElse(null);
    }

}
