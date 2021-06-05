package com.data_integration.a.controller.student;

import com.data_integration.a.PO.Student;
import com.data_integration.a.VO.StudentLoginVO;
import com.data_integration.a.VO.StudentVO;
import com.data_integration.a.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/a/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping("/login")
    public Student studentLogin(@RequestBody StudentLoginVO studentLoginVO){
        return studentService.studentLogin(studentLoginVO);
    }

    @GetMapping("/getAllStudents")
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @PostMapping("/updateStudentInfo")
    public void updateStudentInfo(@RequestBody Student student){
        studentService.updateStudentInfo(student);
    }
}
