package com.data_integration.c.utils;

import com.data_integration.c.service.course.CourseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UtilsTest {
    @Autowired
    CourseService courseService;

    @Test
    public void testXmlToCourses() throws Exception{
        String content = courseService.getSharedCoursesXml();
        Utils.xmlToCourses(content);
    }
}
