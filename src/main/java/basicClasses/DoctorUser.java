package basicClasses;
//TODO: modify this class

public class DoctorUser {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private String specialiation;
    private String medicalGrade;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSpecialiation(String specialiation) {
        this.specialiation = specialiation;
    }
    
    public void setMedicalGrade(String medicalGrade) {
        this.medicalGrade = medicalGrade;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public String getSpecialiation() {
        return specialiation;
    }
    
    public String getMedicalGrade() {
        return medicalGrade;
    }

    @Override
    public String toString() {
        return "DoctorUser{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", specialiation='" + specialiation + '\'' +
                ", medicalGrade='" + medicalGrade + '\'' +
                '}';
    }
}
