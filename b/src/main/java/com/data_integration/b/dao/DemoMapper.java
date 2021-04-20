package com.data_integration.b.dao;


import com.data_integration.b.pojo.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DemoMapper {
    List<Student> getStudents();
}
