package com.data_integration.a.service.student;

import com.data_integration.a.PO.Student;
import com.data_integration.a.VO.StudentLoginVO;
import com.data_integration.a.VO.StudentVO;
import com.data_integration.a.mapper.student.StudentMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    StudentMapper studentMapper;

    public Student studentLogin(StudentLoginVO studentLoginVO){
        Student student = studentMapper.getStudentBySno(studentLoginVO.stunum);
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
