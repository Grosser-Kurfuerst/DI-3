package com.data_integration.c.controller.course;

import com.data_integration.c.VO.CourseVO;
import com.data_integration.c.service.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/c/course")
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
    public void updateCourseShare(@RequestParam String cno, @RequestParam String share){
        courseService.updateCourseShare(cno,share);
    }

    @GetMapping("/getSharedCoursesXml")
    public String getSharedCoursesXml() throws Exception{
        return courseService.getSharedCoursesXml();
    }
}
