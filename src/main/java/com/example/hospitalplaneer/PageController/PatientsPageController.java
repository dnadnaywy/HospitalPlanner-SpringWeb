package com.example.hospitalplaneer.PageController;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PatientsPageController {
    @GetMapping(value = "/patientsPage")
    public String patientsPage() {
        return "patientsPage";
    }
}
