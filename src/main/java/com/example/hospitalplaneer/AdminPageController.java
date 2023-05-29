package com.example.hospitalplaneer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

import static com.example.hospitalplaneer.HospitalPlaneerApplication.admin;

@RestController
@RequestMapping("/admin")
public class AdminPageController {
    @GetMapping("/tables")
    public String getTables() throws SQLException {
        return admin.returnTablesData();
    }
}
