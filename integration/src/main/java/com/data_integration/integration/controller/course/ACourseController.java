package com.data_integration.integration.controller.course;

import com.data_integration.integration.service.course.ACourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/a/course")
public class ACourseController {
    @Autowired
    ACourseService aCourseService;
}
