package com.data_integration.b.controller.test;

import com.data_integration.b.pojo.election.Election;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/sendXml")
    public void sendXml(){
        Election election = new Election();
        election.setCourseId("b123");
        election.setStudentId("b12345678");
        election.setScore("90");

    }
}
