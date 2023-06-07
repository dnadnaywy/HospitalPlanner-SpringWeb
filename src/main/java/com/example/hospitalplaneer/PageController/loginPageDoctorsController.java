package com.example.hospitalplaneer.PageController;

import basicClasses.DoctorUser;
import databaseStuff.Database;
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
    public String doctorsSignup(@ModelAttribute DoctorUser doctor) {
        System.out.println(doctor.toString());

        // Check if the patient exists in the database
        boolean doctorExists = checkDoctorExists(doctor);

        if (!doctorExists) {
            return "error";
        } else {
            return "welcome";
        }
    }

    private boolean checkDoctorExists(DoctorUser doctor) {
        try {
            // Establish a database connection
            Connection conn = Database.getConnection();

            //cu ce sa verificam daca exista?
            String sql = "SELECT COUNT(*) FROM doctors WHERE name = ?";
            String name = doctor.getLastName() + " " + doctor.getFirstName();
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
            return count > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Default to false if an error occurs
    }
}

