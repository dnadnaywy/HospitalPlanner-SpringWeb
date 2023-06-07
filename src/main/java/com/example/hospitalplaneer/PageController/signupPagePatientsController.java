package com.example.hospitalplaneer.PageController;

import basicClasses.PatientUser;
import databaseStuff.Database;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.*;

@Controller
public class signupPagePatientsController {

    @GetMapping("/patientsSignupForm")
    public String signupPagePatients() {
        return "signupPagePatients";
    }

    @PostMapping("/signupPatients")
    public String doctorsSignup(@ModelAttribute PatientUser patient) {
        System.out.println(patient.toString());

        boolean patientExists = checkPatientExists(patient);
        if (!patientExists) {
            insertPatient(patient);
        }
        return "patientsPage";
    }

    private boolean checkPatientExists(PatientUser patient) {
        try {
            Connection conn = Database.getConnection();
            //cu ce sa verificam daca exista?
            String sql = "SELECT COUNT(*) FROM patients WHERE name = ?";
            String name = patient.getLastName() + " " + patient.getFirstName();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            rs.next();
            int count = rs.getInt(1);

            rs.close();
            stmt.close();

            return count > 0; // returnez daca ex sau nu pacientul
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void insertPatient(PatientUser patient) {
        try {
            Connection conn = Database.getConnection();
            // cum inseram? trb adaugata parola si maybe verificam mai sus in fct de parola?

            String sql = "INSERT INTO patients (name, age, medical_insurance, illness_category, phone_number, email, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, patient.getLastName() + " " + patient.getFirstName());
            stmt.setInt(2, patient.getAge());
            stmt.setInt(3, patient.getInsurance());
            stmt.setString(4, patient.getIllness());
            stmt.setString(5, patient.getPhone());
            stmt.setString(6, patient.getEmail());
            stmt.setString(7, patient.getPassword()); //TODO: hash the password

            stmt.executeUpdate();
            Database.getConnection().commit();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

