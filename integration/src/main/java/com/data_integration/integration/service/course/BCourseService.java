package com.data_integration.integration.service.course;

import com.data_integration.integration.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.net.URLDecoder;

@Service
public class BCourseService {
    @Autowired
    SharedCoursesGetter sharedCoursesGetter;

    public String getOtherDepartmentCourses() throws Exception {
        String aXml = sharedCoursesGetter.getASharedCourses();
        String cXml = sharedCoursesGetter.getCSharedCourses();
        // 从集成格式转换到b格式
        URL xslUrl = getClass().getResource("/xsl/b/classToB.xsl");
        String aToB = Utils.transform(URLDecoder.decode(xslUrl.getFile(),"UTF-8"),aXml);
        String cToB = Utils.transform(URLDecoder.decode(xslUrl.getFile(),"UTF-8"),cXml);
        // aToB去掉尾，cToB去掉头，拼接返回
        int index_tail = aToB.indexOf("</classes>");
        aToB = aToB.substring(0,index_tail);
        int index_head = cToB.indexOf("<class>");
        cToB = cToB.substring(index_head);
        return aToB+cToB;
    }
}
