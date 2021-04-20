package com.data_integration.b.serviceImpl;

import com.data_integration.b.dao.DemoMapper;
import com.data_integration.b.pojo.Student;
import com.data_integration.b.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DemoServiceImpl  implements DemoService {
    @Autowired
    DemoMapper demoMapper;

    @Override
    public List<Student> getStudents() {
        return demoMapper.getStudents();
    }
}
