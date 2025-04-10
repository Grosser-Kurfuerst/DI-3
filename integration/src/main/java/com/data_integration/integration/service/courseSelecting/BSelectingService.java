package com.data_integration.integration.service.courseSelecting;


import com.data_integration.integration.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;

@Service
public class BSelectingService {

    @Autowired
    RestTemplate restTemplate;

    /**
     * 将学生和选课转换为课程所在院系的格式，拼接后发送给课程所在院系的服务器
     */
    public String addCourseSelecting(String studentXml, String choiceXml) throws Exception {
        // 验证集成服务器的studentXML
//        URL schemaUrl = getClass().getResource("/schema/format/formatStudent.xsd");
//        File schemaFile = new File(URLDecoder.decode(schemaUrl.getFile(), "UTF-8"));
        File schemaFile = Utils.resource2File("/schema/format/formatStudent.xsd");
        Utils.validateSchema(schemaFile, studentXml);
        // 验证集成服务器的choiceXML
//        schemaUrl = getClass().getResource("/schema/format/formatClassChoice.xsd");
//        schemaFile = new File(URLDecoder.decode(schemaUrl.getFile(), "UTF-8"));
        schemaFile = Utils.resource2File("/schema/format/formatClassChoice.xsd");
        Utils.validateSchema(schemaFile, choiceXml);

        // 验证没问题后转换为B的xml
        URL xslUrl = getClass().getResource("/xsl/b/studentToB.xsl");
        studentXml = Utils.transform(URLDecoder.decode(xslUrl.getFile(), "UTF-8"), studentXml);
        xslUrl = getClass().getResource("/xsl/b/choiceToB.xsl");
        choiceXml = Utils.transform(URLDecoder.decode(xslUrl.getFile(), "UTF-8"), choiceXml);

        // 验证所转换出来的B的xml
//        schemaUrl = getClass().getResource("/schema/b/studentB.xsd");
//        schemaFile = new File(URLDecoder.decode(schemaUrl.getFile(), "UTF-8"));
        schemaFile = Utils.resource2File("/schema/b/studentB.xsd");
        Utils.validateSchema(schemaFile, studentXml);
//        schemaUrl = getClass().getResource("/schema/b/choiceB.xsd");
//        schemaFile = new File(URLDecoder.decode(schemaUrl.getFile(), "UTF-8"));
        schemaFile = Utils.resource2File("/schema/b/choiceB.xsd");
        Utils.validateSchema(schemaFile, choiceXml);

        // 将B的student格式和choice格式的发送给B服务器
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/xml;charset=UTF-8");
        headers.setContentType(type);
        HttpEntity<String> httpEntity = new HttpEntity<>(studentXml + choiceXml, headers);
        // TODO 这里是b的url
        String res = restTemplate.postForObject(Utils.serverB + "/b/courseSelecting/addCourseSelectingXml", httpEntity, String.class);
        System.out.println(res);
        return res;
    }


    /**
     * 将学生和选课转换为课程所在院系的格式，拼接后发送给课程所在院系的服务器
     */
    public String deleteCourseSelecting(String choiceXml) throws Exception {
        // 验证集成服务器的studentXML
//        URL schemaUrl = getClass().getResource("/schema/format/formatStudent.xsd");
//        File schemaFile = new File(URLDecoder.decode(schemaUrl.getFile(), "UTF-8"));
//        Utils.validateSchema(schemaFile, studentXml);
        // 验证集成服务器的choiceXML
//        URL schemaUrl = getClass().getResource("/schema/format/formatClassChoice.xsd");
//        File schemaFile = new File(URLDecoder.decode(schemaUrl.getFile(), "UTF-8"));
        File schemaFile = Utils.resource2File("/schema/format/formatClassChoice.xsd");
        Utils.validateSchema(schemaFile, choiceXml);

        // 验证没问题后转换为B的xml
//        URL xslUrl = getClass().getResource("/xsl/b/studentToB.xsl");
//        studentXml = Utils.transform(URLDecoder.decode(xslUrl.getFile(), "UTF-8"), studentXml);
        URL xslUrl = getClass().getResource("/xsl/b/choiceToB.xsl");
        choiceXml = Utils.transform(URLDecoder.decode(xslUrl.getFile(), "UTF-8"), choiceXml);

        // 验证所转换出来的B的xml
//        schemaUrl = getClass().getResource("/schema/b/studentB.xsd");
//        schemaFile = new File(URLDecoder.decode(schemaUrl.getFile(), "UTF-8"));
//        Utils.validateSchema(schemaFile, studentXml);
//        schemaUrl = getClass().getResource("/schema/b/choiceB.xsd");
//        schemaFile = new File(URLDecoder.decode(schemaUrl.getFile(), "UTF-8"));
        schemaFile = Utils.resource2File("/schema/b/choiceB.xsd");
        Utils.validateSchema(schemaFile, choiceXml);

        // 将B的student格式和choice格式的发送给B服务器
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/xml;charset=UTF-8");
        headers.setContentType(type);
        HttpEntity<String> httpEntity = new HttpEntity<>(choiceXml, headers);
        // TODO 这里是b的url
        String res = restTemplate.postForObject(Utils.serverB + "/b/courseSelecting/deleteCourseSelectingXml", httpEntity, String.class);
        System.out.println(res);
        return res;
    }

}
