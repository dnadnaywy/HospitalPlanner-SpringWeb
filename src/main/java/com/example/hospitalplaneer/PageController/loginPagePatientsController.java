package com.example.hospitalplaneer.PageController;

import basicClasses.PatientUser;
import databaseStuff.Database;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.*;

@Controller
public class loginPagePatientsController {

    @GetMapping("/patientsLoginForm")
    public String signupPagePatients() {
        return "loginPagePatients";
    }

    @GetMapping("/loginP")
    public String patientsSignup(@ModelAttribute PatientUser patient) {
        System.out.println(patient.toString());

        // Check if the patient exists in the database
        boolean patientExists = checkPatientExists(patient);

        if (!patientExists) {
            return "error";
        } else {
            return "welcome";
        }
    }

    private boolean checkPatientExists(PatientUser patient) {
        try {
            // Establish a database connection
            Connection conn = Database.getConnection();

            //cu ce sa verificam daca exista?
            String sql = "SELECT COUNT(*) FROM patients WHERE name = ?";
            String name = patient.getLastName() + " " + patient.getFirstName();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);

            // Execute the query
            ResultSet rs = stmt.executeQuery();

            // Retrieve the result
            rs.next();
            int count = rs.getInt(1);

            //TODO: verify the password

            // Close the resources
            rs.close();
            stmt.close();
//            conn.close();

            // Return whether the patient exists or not
            return count > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // Default to false if an error occurs
    }
}

