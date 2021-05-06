package com.data_integration.b.controller.course;


import com.data_integration.b.pojo.course.Course;
import com.data_integration.b.service.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CourseService courseService;


    @GetMapping("/getCourse/{cid}")
    Course getCourseByCid (@PathVariable String cid) {
        return courseService.getCourseByCid(cid);
    }


    @PostMapping("/setShare/{flag}/{courseId}")
    Course setCourseShareFlag (@PathVariable String flag, @PathVariable String courseId) {
        return courseService.setCourseShareFlag(flag, courseId);
    }


    @PostMapping("/updateCourse")
    Course updateCourse (@RequestBody Course course) {
        return courseService.update(course);
    }



}
