package com.data_integration.b.serviceImpl.student;

import com.data_integration.b.dao.student.StudentDao;
import com.data_integration.b.pojo.account.Account;
import com.data_integration.b.pojo.student.Student;
import com.data_integration.b.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**获取所有学生信息*/
    @Override
    public List<Student> getAllStudents() {
        return studentDao.getAllStudents();
    }


    /**根据学号获取学生Account*/
    @Override
    public Account getAccountByGuestId(String sid) {
        return studentDao.getAccountByGuestId(sid);
    }
}
