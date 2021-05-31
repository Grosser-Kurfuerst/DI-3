package com.data_integration.integration.service.course;


import com.data_integration.integration.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

/**
 * 获得共享课程并转换为统一格式
 */
@Component
public class SharedCoursesGetter {

    @Autowired
    RestTemplate restTemplate;

    public String getASharedCourses() throws Exception{
        // 获得共享课程
        // TODO 这里是a服务器url
        String res = restTemplate.getForObject("http://localhost:8086/a/course/getSharedCoursesXml",String.class);
        System.out.println(res);

        // 验证
        URL schemaUrl = getClass().getResource("/schema/a/classA.xsd");
        File schemaFile = new File(URLDecoder.decode(schemaUrl.getFile(),"UTF-8"));
        Utils.validateSchema(schemaFile,res);

        // 转换为统一格式
        URL xslUrl = getClass().getResource("/xsl/format/formatClass.xsl");
        return Utils.transform(URLDecoder.decode(xslUrl.getFile(),"UTF-8"),res);
    }

    public String getBSharedCourses() throws Exception {
        // TODO 这里是b的url
        String res = restTemplate.getForObject("http://localhost:8888/b/course/getSharedCoursesXml",String.class);
        System.out.println(res);
        // 验证
        URL schemaUrl = getClass().getResource("/schema/b/classB.xsd");
        File schemaFile = new File(URLDecoder.decode(schemaUrl.getFile(),"UTF-8"));
        Utils.validateSchema(schemaFile,res);
        // 转换为统一格式
        URL xslUrl = getClass().getResource("/xsl/format/formatClass.xsl");
        return Utils.transform(URLDecoder.decode(xslUrl.getFile(),"UTF-8"),res);
    }

    public String getCSharedCourses() throws Exception {
        // 获得共享课程
        // TODO 这里是c服务器url
        String res = restTemplate.getForObject("http://localhost:8085/c/course/getSharedCoursesXml",String.class);
        System.out.println(res);

        // 验证
        URL schemaUrl = getClass().getResource("/schema/c/classC.xsd");
        File schemaFile = new File(URLDecoder.decode(schemaUrl.getFile(),"UTF-8"));
        Utils.validateSchema(schemaFile,res);

        // 转换为统一格式
        URL xslUrl = getClass().getResource("/xsl/format/formatClass.xsl");
        return Utils.transform(URLDecoder.decode(xslUrl.getFile(),"UTF-8"),res);
    }
}
