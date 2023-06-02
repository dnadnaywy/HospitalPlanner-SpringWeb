package com.example.hospitalplaneer;

import basicClasses.Table;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.hospitalplaneer.HospitalPlaneerApplication.admin;

@RestController
@RequestMapping("/admin")
public class AdminPageController {
    private final List<Table> tables = new ArrayList<>();

    @GetMapping
    public String admin() {
        return "here is the admin page";
    }

    @GetMapping("/tables")
    public List<Table> getTables() throws SQLException {
        return tables;
    }
}
