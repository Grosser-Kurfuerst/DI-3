package com.data_integration.b.serviceImpl.student;

import com.data_integration.b.dao.student.StudentDao;
import com.data_integration.b.pojo.account.Account;
import com.data_integration.b.pojo.student.Student;
import com.data_integration.b.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentDao studentDao;

    /**根据账号名和密码获取账号对象*/
    @Override
    public Account getAccountByNameAndPassword(String name, String password) {
        return studentDao.getAccountByNameAndPassword(name, password);
    }


    /**根据学生id获取学生对象*/
    @Override
    public Student getStudentBySid(String sid) {
        return studentDao.getStudentBySid(sid);
    }


    /**更新学生对象*/
    @Override
    public Student updateStudentInfo(Student student) {
        studentDao.updateStudentInfo(student);
        return getStudentBySid(student.getSid());
    }
}
