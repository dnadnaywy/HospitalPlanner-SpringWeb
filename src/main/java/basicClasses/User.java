package basicClasses;

import jakarta.validation.constraints.*;

public class User {

    @NotNull
    private String role;

    @NotNull
    @Size(min=2)
    private String name;

    @Size(min=8)
    private String password;

    private int id;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String toString() {
        return "User(Name: " + this.name + ", Role: " + this.role + ")";
    }
}
