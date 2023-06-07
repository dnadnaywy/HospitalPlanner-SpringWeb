package basicClasses;
//TODO: modify this class

public class PatientUser {

    private String firstName;
    private String lastName;
    private Integer age;
    private Integer insurance;
    private String email;
    private String phone;
    private String password;
    private String illness;

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

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setIllness(String illness) {
        this.illness = illness;
    }

    public void setInsurance(Integer insurance) {
        this.insurance = insurance;
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

    public String getIllness() {
        return illness;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getInsurance() {
        return insurance;
    }

    @Override
    public String toString() {
        return "PatientUser{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", insurance=" + insurance +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", illness='" + illness + '\'' +
                '}';
    }
}
