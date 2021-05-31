package com.data_integration.integration.service.course;

import com.data_integration.integration.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

@Service
public class CCourseService {
    @Autowired
    SharedCoursesGetter sharedCoursesGetter;

    public String getOtherDepartmentCourses() throws Exception {
        String aXml = sharedCoursesGetter.getASharedCourses();
        String bXml = sharedCoursesGetter.getBSharedCourses();
        // 从集成格式转换到c格式
        URL xslUrl = getClass().getResource("/xsl/c/classToC.xsl");
        String aToC = Utils.transform(URLDecoder.decode(xslUrl.getFile(),"UTF-8"),aXml);
        String bToC = Utils.transform(URLDecoder.decode(xslUrl.getFile(),"UTF-8"),bXml);
        // aToC去掉尾，bToC去掉头，拼接返回
        int index_tail = aToC.indexOf("</classes>");
        aToC = aToC.substring(0,index_tail);
        int index_head = bToC.indexOf("<class>");
        bToC = bToC.substring(index_head);
        return aToC+bToC;
    }
}
