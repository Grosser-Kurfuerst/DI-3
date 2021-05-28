package com.data_integration.integration.test;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping("/test")
    public String test(@RequestBody String content){

        System.out.println(content);
        return "hello";
    }
}
