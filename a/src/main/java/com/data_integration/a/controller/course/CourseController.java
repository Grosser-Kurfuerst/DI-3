package com.data_integration.a.controller.course;

import com.data_integration.a.VO.CourseVO;
import com.data_integration.a.service.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/a/course")
public class CourseController {
    @Autowired
    CourseService courseService;

    @GetMapping("/getAllCourses")
    public List<CourseVO> getAllCourses(){
        return courseService.getAllCourses();
    }

    @PostMapping("/updateCourseInfo")
    public void updateCourseInfo(@RequestBody CourseVO courseVO){
        courseService.updateCourseInfo(courseVO);
    }

    @PostMapping("/updateCourseShare")
    public void updateCourseShare(@RequestParam String coursenum, @RequestParam String share){
        courseService.updateCourseShare(coursenum,share);
    }
}
