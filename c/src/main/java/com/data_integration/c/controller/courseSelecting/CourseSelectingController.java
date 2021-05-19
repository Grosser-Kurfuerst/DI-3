package com.data_integration.c.controller.courseSelecting;

import com.data_integration.c.PO.CourseSelecting;
import com.data_integration.c.VO.CourseSelectingVO;
import com.data_integration.c.VO.SelectCourseVO;
import com.data_integration.c.service.courseSelecting.CourseSelectingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/c/courseSelecting")
public class CourseSelectingController {
    @Autowired
    CourseSelectingService courseSelectingService;

    @GetMapping("/getAllCourseSelecting")
    public List<CourseSelectingVO> getAllCourseSelecting(){
        return courseSelectingService.getAllCourseSelecting();
    }

    @GetMapping("/getCourseSelectingBySno/{sno}")
    public List<CourseSelectingVO> getCourseSelectingBySno(@PathVariable String sno){
        return courseSelectingService.getCourseSelectingBySno(sno);
    }

    @GetMapping("/getCourseSelectingByCno/{cno}")
    public List<CourseSelectingVO> getCourseSelectingByCno(@PathVariable String cno){
        return courseSelectingService.getCourseSelectingByCno(cno);
    }

    @PostMapping("/addCourseSelecting")
    public boolean addCourseSelecting(@RequestBody SelectCourseVO selectCourseVO){
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
}
