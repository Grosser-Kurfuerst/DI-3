package com.data_integration.integration.service.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ACourseService {
    @Autowired
    RestTemplate restTemplate;
}
