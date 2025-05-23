package com.data_integration.b.dao.student;


import com.data_integration.b.pojo.account.Account;
import com.data_integration.b.pojo.student.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentDao {

    Account getAccountByNameAndPassword(String name, String password);

    Student getStudentBySid(String sid);

    void updateStudentInfo(Student student);

    List<Student> getAllStudents();

    Account getAccountByGuestId(String sid);
}
