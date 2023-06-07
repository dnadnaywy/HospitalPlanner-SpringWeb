package com.example.hospitalplaneer.PageController;

import ClassesDAO.DoctorsDAO;
import basicClasses.DoctorUser;
import basicClasses.DoctorsSchedule;
import basicClasses.Table;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.hospitalplaneer.PageController.DoctorPageController.doctors;
import static com.example.hospitalplaneer.PageController.DoctorPageController.schedules;

@RestController
@RequestMapping("/doctors")
public class DoctorPageRestController {
    @GetMapping
    public String doctors() {
        System.out.println("here is doctors page");
        return "here is doctors page";
    }

    @GetMapping("/allDoctors")
    public List<DoctorUser> getTables() throws SQLException, IOException {
        if (doctors.isEmpty()) {
            System.out.println("Sorry, not good, no doctors at all for now!");
        }
        List<DoctorUser> returnDoctors = new ArrayList<>();
        returnDoctors = DoctorsDAO.returnAllDoctors();
        return returnDoctors;
    }

    @GetMapping("/{name}") //TODO: see why this does not work
    public List<DoctorsSchedule> getSchedule(@PathVariable("name") String name) throws SQLException {
        if (schedules.isEmpty()) {
            System.out.println("Sorry, not good, no schedule at all for now!");
        }
        if (DoctorsDAO.findByName(name) == null) {
            return null;
        }
        List<DoctorsSchedule> returnSchedules = new ArrayList<>();
        returnSchedules = DoctorsDAO.returnAllSchedulesByDoctorId(DoctorsDAO.findByName(name));
        return returnSchedules;
    }

    @GetMapping("/allSchedules")
    public List<DoctorsSchedule> getAllSchedules() throws SQLException, IOException {
        schedules = DoctorsDAO.returnAllSchedules();
        return schedules;
    }
}
