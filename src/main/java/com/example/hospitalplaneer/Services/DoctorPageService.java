package com.example.hospitalplaneer.Services;

import ClassesDAO.DoctorsDAO;
import basicClasses.DoctorsSchedule;
import databaseStuff.Database;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Objects;

@Service
public class DoctorPageService {
    private static String errorMessage = null;

    public String sayHello(String name) {
        return "Greetings from " + name;
    }

    //TODO: verify if what he enters is ok

    public boolean checkInputFormUpdate(DoctorsSchedule doctorsSchedule) throws SQLException {
        errorMessage = "";
        if (Objects.isNull(doctorsSchedule.getDay()) || doctorsSchedule.getDay() < 1 || doctorsSchedule.getDay() > 7) {
            errorMessage += "Please provide a number between 1 and 7 for day of the week!\n";
        }
        //TODO: hours (done) + idDoctor cases !!! modified the hour in db to hour + minute
        if (Objects.isNull(doctorsSchedule.getHour()) || doctorsSchedule.getHour() < 8 || doctorsSchedule.getHour() > 18) {
            errorMessage += "Please provide an hour between 8 and 18, as our doctors don't work that late!\n";
        }
        if (Objects.isNull(doctorsSchedule.getMinute()) || doctorsSchedule.getMinute() < 0 || doctorsSchedule.getMinute() > 59 || doctorsSchedule.getMinute() % 30 != 0) {
            errorMessage += "We kindly remind you that the minutes are only between 0 and 59 and\n" +
                    "that it is mandatory to have appointments at minimum 30 minutes between each other!\n";
        }
        String doctorName = null;
        doctorName = DoctorsDAO.findById(doctorsSchedule.getIdDoctor());
        if (doctorName == null) {
            errorMessage += "We didn't find the id you provided, please try again!\n";
        }
        if (!errorMessage.equals("")) {
            return false;
        }
        return true;
    }

    public static String getErrorMessage() {
        return errorMessage;
    }

}
