package com.data_integration.c.mapper.student;

import com.data_integration.c.PO.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentMapper {
    Student getStudentBySno(String sno);
    List<Student> getAllStudents();
    void updateStudentInfo(Student student);
}
