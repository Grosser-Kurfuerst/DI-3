package com.data_integration.c.controller.student;

import com.data_integration.c.PO.Student;
import com.data_integration.c.VO.StudentLoginVO;
import com.data_integration.c.VO.StudentVO;
import com.data_integration.c.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/c/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping("/login")
    public StudentVO studentLogin(@RequestBody StudentLoginVO studentLoginVO){
        return studentService.studentLogin(studentLoginVO);
    }

    @GetMapping("/getAllStudents")
    public List<StudentVO> getAllStudents(){
        return studentService.getAllStudents();
    }

    @PostMapping("/updateStudentInfo")
    public void updateStudentInfo(@RequestBody Student student){
        studentService.updateStudentInfo(student);
    }
}
