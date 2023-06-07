package com.example.hospitalplaneer;

import ClassesDAO.AdminDAO;
import ClassesDAO.DoctorsDAO;
import ClassesDAO.PatientsDAO;
import databaseStuff.Database;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

import java.io.IOException;
import java.sql.SQLException;

import static ClassesDAO.AdminDAO.findTableByName;

@SpringBootApplication
public class HospitalPlaneerApplication {
    public static AdminDAO admin = new AdminDAO();
    public static void main(String[] args) throws SQLException {
        try {
            Database.createConnection();
//            System.out.println(Database.getConnection());
//            System.out.println(Database.getConnection().isClosed());
//            var doctors = new DoctorsDAO();
//            System.out.println("Doctors with ORL specialization:");
//            System.out.println(doctors.returnDocsSpec("ORL"));

//            System.out.println(DoctorsDAO.findById((Integer)21));
//            doctors.create("new name here");
//
//            System.out.println("Doctors with a random specialization:");
//            System.out.println(doctors.returnDocsSpec("random"));
//
//            var patients = new PatientsDAO();
//            patients.deleteMichaelJackson();
//            Database.getConnection().commit();

//            DoctorsDAO.returnAllSchedulesByDoctorId(1);

            DoctorsDAO.returnAllSchedulesByDoctorId(1);
            DoctorsDAO.returnAllDoctors();
            System.out.println(DoctorsDAO.findByName("Macovei Rares"));

            System.out.println(DoctorsDAO.findByNameAndPassword("Macovei Rares", "admin1234"));
            SpringApplication.run(HospitalPlaneerApplication.class, args);
        } catch (SQLException e) {
            System.err.println(e);
            Database.rollback();
        }
    }
}
