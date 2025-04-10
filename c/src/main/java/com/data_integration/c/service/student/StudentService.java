package com.data_integration.c.service.student;

import com.data_integration.c.PO.Student;
import com.data_integration.c.VO.StudentLoginVO;
import com.data_integration.c.mapper.student.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentMapper studentMapper;

    public Student studentLogin(StudentLoginVO studentLoginVO){
        Student student = studentMapper.getStudentBySno(studentLoginVO.sno);

        if(student==null || !student.pwd.equals(studentLoginVO.pwd))
            return null;
        return student;
    }

    public List<Student> getAllStudents(){
        return studentMapper.getAllStudents();
    }

    public void updateStudentInfo(Student student){
        studentMapper.updateStudentInfo(student);
    }
}
