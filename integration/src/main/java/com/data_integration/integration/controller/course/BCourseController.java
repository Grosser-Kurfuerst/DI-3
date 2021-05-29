package com.data_integration.integration.controller.course;

import com.data_integration.integration.service.course.BCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/b/course")
public class BCourseController {
    @Autowired
    BCourseService bCourseService;
}
