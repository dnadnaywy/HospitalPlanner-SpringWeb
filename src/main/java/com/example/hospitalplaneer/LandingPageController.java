package com.example.hospitalplaneer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LandingPageController {
    @GetMapping(value = "/landingPage")
    public String landingPage() {
        return "landingPage";
    }

    @GetMapping(value = "/doctorsPage")
    public String doctorsPage() {
        return "doctorsPage";
    }

    @GetMapping(value = "/patientsPage")
    public String patientsPage() {
        return "patientsPage";
    }
}