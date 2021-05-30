package com.data_integration.integration.services;

import com.data_integration.integration.service.course.ACourseService;
import com.data_integration.integration.utils.Utils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ACourseServiceTest {
    @Autowired
    ACourseService aCourseService;

    @Test
    public void testGetOtherDepartmentCourses() throws Exception {
        String res = aCourseService.getOtherDepartmentCourses();
        System.out.println(res);
        // 验证
        URL schemaUrl = getClass().getResource("/schema/a/classA.xsd");
        File schemaFile = new File(URLDecoder.decode(schemaUrl.getFile(),"UTF-8"));
        Utils.validateSchema(schemaFile,res);
    }
}
