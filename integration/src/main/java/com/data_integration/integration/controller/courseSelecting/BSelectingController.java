package com.data_integration.integration.controller.courseSelecting;

import com.data_integration.integration.service.courseSelecting.BSelectingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/b/courseSelecting")
public class BSelectingController {

    @Autowired
    BSelectingService selectingService;

    @PostMapping("/addCourseSelecting")
    public String addCourseSelecting(@RequestBody String content) throws Exception {
        // 分割学生和选课
        int splitIndex = content.indexOf("</students>")+"</students>".length();
        String studentXml = content.substring(0,splitIndex);
        String choiceXml = content.substring(splitIndex);
        return selectingService.addCourseSelecting(studentXml, choiceXml);

    }

    @PostMapping("/deleteCourseSelecting")
    public String deleteCourseSelecting(@RequestBody String content) throws Exception {
        // 分割学生和选课
//        int splitIndex = content.indexOf("</students>")+"</students>".length();
//        String studentXml = content.substring(0,splitIndex);
        return selectingService.deleteCourseSelecting(content);

    }
}
