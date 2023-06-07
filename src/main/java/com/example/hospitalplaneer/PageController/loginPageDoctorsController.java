package com.example.hospitalplaneer.PageController;

import ClassesDAO.DoctorsDAO;
import basicClasses.DoctorUser;
import databaseStuff.Database;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.*;

@Controller
public class loginPageDoctorsController {
    @GetMapping("/doctorsLoginForm")
    public String signupPagePDoctors() {
        return "loginPageDoctors";
    }

    @GetMapping("/loginD")
    public String doctorsSignup(@ModelAttribute DoctorUser doctor) throws SQLException {
        System.out.println(doctor.toString());

        // Check if the patient exists in the database
        boolean doctorExists = checkDoctorExists(doctor);

        System.out.println("doctorExists : " + doctorExists);

        if (!doctorExists) {
            return "loginPageDoctors";
        } else {

            return "doctorsPage";
        }
    }

    private boolean checkDoctorExists(DoctorUser doctor) {
        try {
            // Establish a database connection
            Connection conn = Database.getConnection();
            String name = doctor.getLastName() + " " + doctor.getFirstName();
            Integer count = DoctorsDAO.findByNameAndPassword(name, doctor.getPassword());
            if (count == null) return false;
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Default to false if an error occurs
    }
}

