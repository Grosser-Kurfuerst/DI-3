package com.data_integration.c.controller.courseSelecting;

import com.data_integration.c.VO.CourseSelectingVO;
import com.data_integration.c.VO.SelectCourseVO;
import com.data_integration.c.service.courseSelecting.CourseSelectingService;
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
    public boolean addCourseSelecting(@RequestBody CourseSelectingVO courseSelectingVO) throws Exception{
        return courseSelectingService.addCourseSelecting(courseSelectingVO);
    }

    @PostMapping("/deleteCourseSelecting")
    public void deleteCourseSelecting(@RequestBody SelectCourseVO selectCourseVO) throws Exception {
        courseSelectingService.deleteCourseSelecting(selectCourseVO);
    }

    @PostMapping("/updateGrade")
    public void updateGrade(@RequestBody CourseSelectingVO courseSelectingVO){
        courseSelectingService.updateGrade(courseSelectingVO);
    }

    /**
     * 其它院系同学通过xml进行选课的接口
     */
    @PostMapping("/addCourseSelectingXml")
    public String addCourseSelectingXml(@RequestBody String content) throws Exception {
        return courseSelectingService.addCourseSelectingXml(content);
    }

    /**
     * 其它院系同学通过xml进行退课的接口
     */
    @PostMapping("/deleteCourseSelectingXml")
    public String deleteCourseSelectingXml(@RequestBody String content) throws Exception {
        return courseSelectingService.deleteCourseSelectingXml(content);
    }
}
