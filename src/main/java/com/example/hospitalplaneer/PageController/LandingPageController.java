package com.example.hospitalplaneer.PageController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LandingPageController {
    @GetMapping(value = "/landingPage")
    public String landingPage() {
        return "landingPage";
    }
}