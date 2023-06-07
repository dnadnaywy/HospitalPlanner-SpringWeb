package basicClasses;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import javax.print.Doc;

public class DoctorsSchedule {

//    @NotNull(message = "Name is required")
    private Integer idDoctor;

//    @NotNull(message = "Day of the appointment is required")
//    @Min(1)
//    @Max(7)
    private Integer day;

    private Integer hour;

    private Integer minute;

    private String errorMessage = "This is the end of the form";

    public DoctorsSchedule() {}

    public DoctorsSchedule(Integer idDoctor, Integer day, Integer hour, Integer minute) {
        this.day = day;
        this.idDoctor = idDoctor;
        this.hour = hour;
        this.minute = minute;
    }

    public Integer getDay() {
        return day;
    }

    public Integer getIdDoctor() {
        return idDoctor;
    }

    public Integer getHour() {
        return hour;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public void setIdDoctor(Integer idDoctor) {
        this.idDoctor = idDoctor;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getMinute() {
        return minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }

    public String toString() {
        return "Schedule (id_doctor: " + this.idDoctor + ", day: " + this.day + ")";
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
