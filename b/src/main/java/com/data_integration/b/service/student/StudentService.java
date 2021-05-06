package com.data_integration.b.service.student;


import com.data_integration.b.pojo.account.Account;
import com.data_integration.b.pojo.student.Student;

public interface StudentService {

    /**根据账号名和密码获取账号对象*/
    Account getAccountByNameAndPassword(String name, String password);

    /**根据学生id获取学生对象*/
    Student getStudentBySid(String sid);


    /**更新学生对象*/
    Student updateStudentInfo(Student student);
}
