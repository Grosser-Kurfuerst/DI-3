package com.data_integration.c.service.student;

import com.data_integration.c.PO.Student;
import com.data_integration.c.VO.StudentLoginVO;
import com.data_integration.c.VO.StudentVO;
import com.data_integration.c.mapper.student.StudentMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    StudentMapper studentMapper;

    public StudentVO studentLogin(StudentLoginVO studentLoginVO){
        Student student = studentMapper.getStudentBySno(studentLoginVO.sno);
        if(student==null || !student.pwd.equals(studentLoginVO.pwd))
            return null;
        StudentVO studentVO = new StudentVO();
        BeanUtils.copyProperties(student,studentVO);
        return studentVO;
    }

    public List<StudentVO> getAllStudents(){
        List<StudentVO> studentVOList = studentMapper.getAllStudents().stream().map(s->{
            StudentVO studentVO = new StudentVO();
            BeanUtils.copyProperties(s,studentVO);
            return studentVO;
        }).collect(Collectors.toList());
        return studentVOList;
    }

    public void updateStudentInfo(Student student){
        studentMapper.updateStudentInfo(student);
    }
}
