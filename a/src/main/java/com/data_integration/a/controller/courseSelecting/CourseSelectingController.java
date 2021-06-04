package com.data_integration.a.controller.courseSelecting;

import com.data_integration.a.VO.CourseSelectingVO;
import com.data_integration.a.VO.SelectCourseVO;
import com.data_integration.a.service.courseSelecting.CourseSelectingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/a/courseSelecting")
public class CourseSelectingController {
    @Autowired
    CourseSelectingService courseSelectingService;

    @GetMapping("/getAllCourseSelecting")
    public List<CourseSelectingVO> getAllCourseSelecting(){
        return courseSelectingService.getAllCourseSelecting();
    }

    @GetMapping("/getCourseSelectingBySno/{studentnum}")
    public List<CourseSelectingVO> getCourseSelectingBySno(@PathVariable String studentnum){
        return courseSelectingService.getCourseSelectingBySno(studentnum);
    }

    @GetMapping("/getCourseSelectingByCno/{coursenum}")
    public List<CourseSelectingVO> getCourseSelectingByCno(@PathVariable String coursenum){
        return courseSelectingService.getCourseSelectingByCno(coursenum);
    }

    @PostMapping("/addCourseSelecting")
    public boolean addCourseSelecting(@RequestBody SelectCourseVO selectCourseVO) throws Exception{
        return courseSelectingService.addCourseSelecting(selectCourseVO);
    }

    @PostMapping("/deleteCourseSelecting")
    public void deleteCourseSelecting(@RequestBody SelectCourseVO selectCourseVO){
        courseSelectingService.deleteCourseSelecting(selectCourseVO);
    }

    @PostMapping("/updateGrade")
    public void updateGrade(@RequestBody CourseSelectingVO courseSelectingVO){
        courseSelectingService.updateGrade(courseSelectingVO);
    }

    @PostMapping("/addCourseSelectingXml")
    public String addCourseSelectingXml(@RequestBody String content) throws Exception {
        return courseSelectingService.addCourseSelectingXml(content);
    }
}
