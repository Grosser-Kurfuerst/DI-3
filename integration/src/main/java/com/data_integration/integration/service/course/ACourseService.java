package com.data_integration.integration.service.course;

import com.data_integration.integration.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.net.URLDecoder;

@Service
public class ACourseService {
    @Autowired
    SharedCoursesGetter sharedCoursesGetter;

    public String getOtherDepartmentCourses() throws Exception {
        String bXml = sharedCoursesGetter.getBSharedCourses();
        String cXml = sharedCoursesGetter.getCSharedCourses();
        // 从集成格式转换到a格式
        URL xslUrl = getClass().getResource("/xsl/a/classToA.xsl");
        String bToA = Utils.transform(URLDecoder.decode(xslUrl.getFile(),"UTF-8"),bXml);
        String cToA = Utils.transform(URLDecoder.decode(xslUrl.getFile(),"UTF-8"),cXml);
        // bToA去掉尾，cToA去掉头，拼接返回
        int index_tail = bToA.indexOf("</classes>");
        bToA = bToA.substring(0,index_tail);
        int index_head = cToA.indexOf("<class>");
        cToA = cToA.substring(index_head);
        return bToA+cToA;
    }
}
