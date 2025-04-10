package com.data_integration.b.controller.election;

import com.data_integration.b.pojo.account.Account;
import com.data_integration.b.pojo.course.Course;
import com.data_integration.b.pojo.election.Election;
import com.data_integration.b.pojo.student.Student;
import com.data_integration.b.service.course.CourseService;
import com.data_integration.b.service.election.ElectionService;
import com.data_integration.b.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/b/courseSelecting")
public class ElectionController {


    @Autowired
    ElectionService electionService;

    @Autowired
    CourseService courseService;

    @Autowired
    StudentService studentService;

    /**
     * 获取某学生所选择的课程信息
     */
    @GetMapping("/getElectionsBySid/{sid}")
    public List<Election> getElectionsBySid(@PathVariable String sid) {
        return electionService.getElectionsBySid(sid);
    }


    /**
     * 获取某课程的选课信息
     */
    @GetMapping("/getElectionsByCid/{cid}")
    public List<Election> getElectionsByCid(@PathVariable String cid) {
        return electionService.getElectionsByCid(cid);
    }

    /**
     * 获取所有选课的信息
     */
    @GetMapping("/getElections")
    public List<Election> getElections() {
        return electionService.getElections();
    }


    /**
     * 添加学生的选课
     */
    @PostMapping("/addCourseSelecting")
    public boolean addCourseSelecting(@RequestBody Election election) throws Exception{
        return electionService.addCourseSelecting(election);
    }


    /**
     * 删除学生的选课
     */
    @GetMapping("/delete/{cid}/{sid}")
    public String deleteElectionBySidCid(@PathVariable String cid, @PathVariable String sid) throws Exception {
        int outcome = electionService.deleteElectionBySidCid(cid, sid);
        if (outcome == 1) return "删除成功";
        return "删除失败";
    }

    /**
     * 更新选课中的学生的成绩
     */
    @GetMapping("/updateGrade/{cid}/{sid}/{score}")
    public String updateGrade(@PathVariable String cid, @PathVariable String sid, @PathVariable int score) {
        int outcome = electionService.updateGrade(cid, sid, score);
        if (outcome == 1) return "成绩更新成功";
        return "成绩更新失败";
    }

    /**
     * 其它院系同学通过xml进行选课的接口
     */
    @PostMapping("/addCourseSelectingXml")
    public String addCourseSelectingXml(@RequestBody String content) throws Exception {
        return electionService.addCourseSelectingXml(content);
    }

    @PostMapping("/deleteCourseSelectingXml")
    public String deleteCourseSelectingXml(@RequestBody String content) throws Exception {
        return electionService.deleteCourseSelectingXml(content);
    }


}
