package com.example.hospitalplaneer;

import ClassesDAO.AdminDAO;
import ClassesDAO.DoctorsDAO;
import ClassesDAO.PatientsDAO;
import databaseStuff.Database;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.sql.SQLException;

@SpringBootApplication
public class HospitalPlaneerApplication {

    public static void main(String[] args) throws SQLException {
        try {
            Database.createConnection();
            var doctors = new DoctorsDAO();
            System.out.println(doctors.returnDocsSpec("ORL"));

            var patients = new PatientsDAO();
            patients.deleteMichaelJackson();

            var admin = new AdminDAO();
            System.out.println(admin.returnTablesData());
            /*SpringApplication.run(HospitalPlaneerApplication.class, args);*/
            Database.closeConnection();
        } catch (SQLException e) {
            System.err.println(e);
            Database.rollback();
        }
    }

}
