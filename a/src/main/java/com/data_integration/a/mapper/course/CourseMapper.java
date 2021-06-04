package com.data_integration.a.mapper.course;

import com.data_integration.a.PO.Course;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CourseMapper {
    List<Course> getAllCourses();
    Course getCourseByCno(String cno);
    void updateCourseInfo(Course course);
    void updateCourseShare(String coursenum, String share);

}
