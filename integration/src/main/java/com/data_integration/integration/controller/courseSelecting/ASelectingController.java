package com.data_integration.integration.controller.courseSelecting;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/a/courseSelecting")
public class ASelectingController {

    @PostMapping("/addCourseSelecting")
    public String addCourseSelecting(@RequestBody String content){
        // 分割学生和选课
        int splitIndex = content.indexOf("</students>")+"</students>".length();
        String studentXml = content.substring(0,splitIndex);
        String choiceXml = content.substring(splitIndex);
        System.out.println(studentXml);
        System.out.println(choiceXml);
        // TODO 业务
        return "true";
    }
}
