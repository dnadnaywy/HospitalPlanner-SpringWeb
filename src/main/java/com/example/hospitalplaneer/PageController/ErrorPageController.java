package com.example.hospitalplaneer.PageController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorPageController {
    @GetMapping(value = "/error")
    public String errorPage() {
        return "error";
    }
}
