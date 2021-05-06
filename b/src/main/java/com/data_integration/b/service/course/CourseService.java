package com.data_integration.b.service.course;

import com.data_integration.b.pojo.course.Course;

public interface CourseService {

    /**根据cid获取课程信息*/
    Course getCourseByCid (String cid);

    /**设置课程的共享信息*/
    Course setCourseShareFlag (String flag, String courseId);

    /**更新课程的信息*/
    Course update(Course course);
}
