package com.data_integration.b.dao.course;

import com.data_integration.b.pojo.course.Course;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CourseDao {

    Course getCourseByCid (String cid);

    int setCourseShareFlag (String flag, String courseId);

    int updateCourseInfo(Course course);
}
