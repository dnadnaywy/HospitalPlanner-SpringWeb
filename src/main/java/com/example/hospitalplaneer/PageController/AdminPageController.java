package com.example.hospitalplaneer.PageController;

import basicClasses.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminPageController {
    @GetMapping(value = "/adminPage")
    public String adminPage() {
        return "adminPage";
    }

    @GetMapping(value = "/adminAddUser")
    public String adminAddUser(Model model) {
        model.addAttribute("greeting", new User());
        return "adminAddUser";
    }
}
