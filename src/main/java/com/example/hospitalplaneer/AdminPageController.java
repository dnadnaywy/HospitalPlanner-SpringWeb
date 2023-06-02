package com.example.hospitalplaneer;

import basicClasses.Table;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.hospitalplaneer.HospitalPlaneerApplication.admin;

@RestController
@RequestMapping("/admin")
public class AdminPageController {
    private List<Table> tableList = new ArrayList<>();

    @GetMapping
    public String admin() {
        return "here is the admin page";
    }

    @GetMapping("/tables")
    public List<Table> getTables() throws SQLException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        tableList = objectMapper.readValue(new File("src/main/resources/jsonFiles/tablesData.json"), new TypeReference<List<Table>>() {
        });
        return tableList;
    }
}
