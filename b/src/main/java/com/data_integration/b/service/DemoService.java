package com.data_integration.b.service;

import com.data_integration.b.pojo.Student;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DemoService {

    public List<Student> getStudents();
}
