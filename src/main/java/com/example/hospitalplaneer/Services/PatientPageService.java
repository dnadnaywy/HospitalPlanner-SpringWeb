package com.example.hospitalplaneer.Services;

import ClassesDAO.DoctorsDAO;
import ClassesDAO.PatientsDAO;
import basicClasses.DoctorsSchedule;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Objects;

@Service
public class PatientPageService {
    private static String errorMessage = null;

    //TODO: verify if what he enters is ok

    public boolean checkInputFormUpdateAppointment(DoctorsSchedule appointment) throws SQLException {
        errorMessage = "";
        if (Objects.isNull(appointment.getDay()) || appointment.getDay() < 1 || appointment.getDay() > 7) {
            errorMessage += "Please provide a number between 1 and 7 for day of the week!\n";
        }
        //TODO: hours (done) + idDoctor cases !!! modified the hour in db to hour + minute
        if (Objects.isNull(appointment.getHour()) || appointment.getHour() < 8 || appointment.getHour() > 18) {
            errorMessage += "Please provide an hour between 8 and 18, as our doctors don't work that late!\n";
        }
        if (Objects.isNull(appointment.getMinute()) || appointment.getMinute() < 0 || appointment.getMinute() > 59 || appointment.getMinute() % 30 != 0) {
            errorMessage += "We kindly remind you that the minutes are only between 0 and 59 and\n" +
                    "that it is mandatory to have appointments at minimum 30 minutes between each other!\n";
        }
        Integer rezultat = DoctorsDAO.findByHourAndMinute(appointment.getIdDoctor(), appointment.getHour(), appointment.getMinute());
        if (rezultat != null) {
            errorMessage += "There is already an appointment, try again!\n";
        }
        String doctorName = null;
        doctorName = DoctorsDAO.findById(appointment.getIdDoctor());
        if (doctorName == null) {
            errorMessage += "We didn't find the doctor id you provided, please try again!\n";
        }
        String patientName = null;
        patientName = PatientsDAO.findById(appointment.getIdPatient());
        if (patientName == null) {
            errorMessage += "We didn't find your id, please try again!\n";
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
