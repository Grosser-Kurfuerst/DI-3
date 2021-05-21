package com.data_integration.b.serviceImpl.course;

import com.data_integration.b.dao.course.CourseDao;
import com.data_integration.b.pojo.course.Course;
import com.data_integration.b.service.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseDao courseDao;

    /**根据cid获取课程信息*/
    @Override
    public Course getCourseByCid(String cid) {
        return courseDao.getCourseByCid(cid);
    }

    /**设置课程的共享信息*/
    @Override
    public int setCourseShareFlag(String flag, String courseId) {
         return courseDao.setCourseShareFlag(flag, courseId);
    }

    /**更新课程的信息*/
    @Override
    public int update(Course course) {
        return courseDao.updateCourseInfo(course);
    }


    /**获取所有课程的信息*/
    @Override
    public List<Course> getAllCourses() {
        return courseDao.getAllCourses();
    }
}
