package com.data_integration.b.controller.student;

import com.data_integration.b.pojo.account.Account;
import com.data_integration.b.pojo.student.Student;
import com.data_integration.b.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/b/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    /**
     * 登录，
     * 如果存在用户名密码匹配的账户，那么返回账号的客体（学生）的信息
     * 不存在则返回null
     */
    @PostMapping("/login")
    public Student login(@RequestBody Account account) {
        // 获取匹配的账户
        Account matchedAccount = studentService.getAccountByNameAndPassword(account.getAname(), account.getPassword());
        //判断账户是否为null
        if (matchedAccount == null) return null;
        return getStudentInfoBySid(matchedAccount.getGuest_id());
    }

    /**
     * 根据学生id获取学生对象（隐藏密码）
     */
    @GetMapping("/{sid}")
    public Student getStudentInfoBySid(@PathVariable String sid) {
        Student student = studentService.getStudentBySid(sid);
        return student;
    }


    /**
     * 个人信息管理，更新Student对象
     */
    @PostMapping("/update")
    public Student updateStudentInfo(@RequestBody Student student) {
        Student updatedStudent = studentService.updateStudentInfo(student);
        return updatedStudent;
    }


    /**
     * 获取所有学生信息
     */
    @GetMapping("/getAllStudents")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

}
