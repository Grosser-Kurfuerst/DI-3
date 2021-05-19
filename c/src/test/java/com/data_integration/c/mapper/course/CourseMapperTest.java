package com.data_integration.c.mapper.course;

import com.data_integration.c.PO.Course;
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
public class CourseMapperTest {
    @Autowired
    CourseMapper courseMapper;

    @Test
    public void testGetAllCourses() throws Exception{
        List<Course> courseList = courseMapper.getAllCourses();
        for(Course course:courseList)
            System.out.println(course.cno+course.cnm);
    }

    @Test
    public void testUpdateCourseInfo() throws Exception{
        Course course = new Course();
        course.cno="1233";
        course.cnm="Calc";
        course.ctm=4;
        course.cpt=4;
        course.tec="zyq";
        course.pla="404";
        course.share="Y";
        course.permission=2;
        courseMapper.updateCourseInfo(course);
        course = courseMapper.getCourseByCno("1233");
        Assert.assertEquals("1233Calc44zyq404Y2", course.cno+course.cnm+course.ctm+course.cpt+course.tec+course.pla+course.share+course.permission);
    }

    @Test
    public void testUpdateCourseShare() throws Exception{
        courseMapper.updateCourseShare("1234","N");
        Assert.assertEquals("N",courseMapper.getCourseByCno("1234").share);
    }
}
