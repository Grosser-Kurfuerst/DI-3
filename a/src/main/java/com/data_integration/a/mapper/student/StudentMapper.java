package com.data_integration.a.mapper.student;

import com.data_integration.a.PO.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentMapper {
    Student getStudentBySno(String stunum);
    Student getStudentByAcc(String acc);
    List<Student> getAllStudents();
    void updateStudentInfo(Student student);
}
