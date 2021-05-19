package com.data_integration.c.mapper.student;

import com.data_integration.c.PO.Student;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentMapperTest {
    @Autowired
    StudentMapper studentMapper;

    @Test
    public void testGetStudentBySno() throws Exception{
        Student student = studentMapper.getStudentBySno("123456789");
        Assert.assertEquals("123456789",student.sno);
        System.out.println(student.snm);
    }

    @Test
    public void testGetAllStudents() throws Exception{
        List<Student> studentList = studentMapper.getAllStudents();
        for(Student student:studentList)
            System.out.println(student.sno+student.snm);
    }

    @Test
    public void testUpdateStudentInfo() throws Exception{
        Student student = new Student();
        student.sno="123456788";
        student.snm="Billy";
        student.pwd="123";
        student.sex="M";
        student.sde="CS";
        student.permission=2;
        studentMapper.updateStudentInfo(student);
        student = studentMapper.getStudentBySno("123456788");
        Assert.assertEquals("Billy",student.snm);
        Assert.assertEquals("123",student.pwd);
        Assert.assertEquals("M",student.sex);
        Assert.assertEquals("CS",student.sde);
        Assert.assertEquals(Integer.valueOf(2),student.permission);
    }
}
