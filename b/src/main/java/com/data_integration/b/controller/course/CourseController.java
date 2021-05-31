package com.data_integration.b.controller.course;


import com.data_integration.b.pojo.course.Course;
import com.data_integration.b.service.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/b/course")
public class CourseController {

    @Autowired
    CourseService courseService;


    @GetMapping("/getCourse/{cid}")
    Course getCourseByCid (@PathVariable String cid) {
        return courseService.getCourseByCid(cid);
    }


    @GetMapping("/setShare/{flag}/{courseId}")
    String setCourseShareFlag (@PathVariable String flag, @PathVariable String courseId) {
        int updateNum = courseService.setCourseShareFlag(flag, courseId);
        if (updateNum == 1) return "修改成功";
        return "修改失败";
    }

    @GetMapping("/getSharedCoursesXml")
    String getSharedCoursesXml() throws Exception {
        return courseService.getSharedCoursesXml();
    }


    @PostMapping("/updateCourse")
    String updateCourse (@RequestBody Course course) {
        int updateNum = courseService.update(course);
        if (updateNum == 1) return "更新成功";
        return "更新失败";
    }

    @GetMapping("/getAllCourses")
    List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/getOtherDepartmentCourses")
    public List<Course> getOtherDepartmentCourses() throws Exception{
        return courseService.getOtherDepartmentCourses();
    }

}
