package com.data_integration.b.service.course;

import com.data_integration.b.pojo.course.Course;

import java.util.List;

public interface CourseService {

    /**根据cid获取课程信息*/
    Course getCourseByCid (String cid);

    /**设置课程的共享信息*/
    int setCourseShareFlag (String flag, String courseId);

    /**更新课程的信息*/
    int update(Course course);

    /**获取所有课程的信息*/
    List<Course> getAllCourses();

    /**获取所有的 共享课程*/
    List<Course> getSharedCourses();

    /**获取所有的 共享课程的xml文件*/
    String getSharedCoursesXml() throws Exception;

    /**获取其它院系的共享课程*/
    List<Course> getOtherDepartmentCourses() throws Exception;
}
