package com.data_integration.integration.controller.course;

import com.data_integration.integration.service.course.CCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/c/course")
public class CCourseController {
    @Autowired
    CCourseService cCourseService;

    @GetMapping("/getOtherDepartmentCourses")
    public String getOtherDepartmentCourses() throws Exception{
        return cCourseService.getOtherDepartmentCourses();
    }
}
