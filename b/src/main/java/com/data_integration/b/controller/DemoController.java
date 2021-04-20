package com.data_integration.b.controller;


import com.data_integration.b.pojo.Student;
import com.data_integration.b.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("demo")
public class DemoController {
    @Autowired
    DemoService demoService;

    @RequestMapping("/test")
    public List<Student> getStudents() {
        return demoService.getStudents();
    }
}
