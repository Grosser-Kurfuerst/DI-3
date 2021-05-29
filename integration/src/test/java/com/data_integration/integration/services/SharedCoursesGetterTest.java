package com.data_integration.integration.services;

import com.data_integration.integration.Application;
import com.data_integration.integration.service.course.SharedCoursesGetter;
import com.data_integration.integration.utils.Utils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;
import java.net.URLDecoder;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SharedCoursesGetterTest {
    @Autowired
    SharedCoursesGetter sharedCoursesGetter;

    @Test
    public void testGetCSharedCourses() throws Exception{
        String res = sharedCoursesGetter.getCSharedCourses();
        System.out.println(res);

        // 从集成格式转换到b格式
        URL xslUrl = getClass().getResource("/xsl/b/classToB.xsl");
        String transformed = Utils.transform(URLDecoder.decode(xslUrl.getFile(),"UTF-8"),res);
        System.out.println(transformed);
        // 去除头尾
        int index_tail = transformed.indexOf("</classes>");
        transformed = transformed.substring(0,index_tail);
        int index_head = transformed.indexOf("<class>");
        transformed = transformed.substring(index_head);
        System.out.println(transformed);
    }
}
