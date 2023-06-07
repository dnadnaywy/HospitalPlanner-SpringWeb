package com.example.hospitalplaneer.PageController;

import basicClasses.DoctorUser;
import databaseStuff.Database;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Controller
public class signupPageDoctorsController {

    @GetMapping("/doctorsSignupForm")
    public String signupPageDoctors() {
        return "signupPageDoctors";
    }

    @PostMapping("/signupDoctors")
    public String doctorsSignup(@ModelAttribute DoctorUser doctor) {
        System.out.println(doctor.toString());
        //verif existenta in BD
        boolean doctorExists = checkDoctorExists(doctor);
        if (!doctorExists) {
            insertDoctor(doctor);
        }
        return "doctorsPage";
    }

    private boolean checkDoctorExists(DoctorUser doctor) {
        try {
            Connection conn = Database.getConnection();

            //cu ce sa verificam daca exista?
            String sql = "SELECT COUNT(*) FROM doctors WHERE name = ?";
            String name = doctor.getLastName() + " " + doctor.getFirstName();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);

            ResultSet rs = stmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);

            rs.close();
            stmt.close();
//            conn.close();
            return count > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void insertDoctor(DoctorUser doctor) {
        try {

            Connection conn = Database.getConnection();

            // cum inseram? trb adaugata parola si maybe verificam mai sus in fct de parola?

            String sql = "INSERT INTO doctors (name, specialization, phone_number, email, password) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, doctor.getLastName() + " " + doctor.getFirstName());
            stmt.setString(2, doctor.getSpecialiation());
            stmt.setString(3, doctor.getPhone());
            stmt.setString(4, doctor.getEmail());
            stmt.setString(5, doctor.getPassword());

            stmt.executeUpdate();
            Database.getConnection().commit();

            stmt.close();
//            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

