package com.data_integration.b.serviceImpl.course;

import com.data_integration.b.dao.course.CourseDao;
import com.data_integration.b.pojo.course.Course;
import com.data_integration.b.service.course.CourseService;
import com.data_integration.b.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseDao courseDao;

    @Autowired
    RestTemplate restTemplate;

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

    /**获取所有的 共享课程*/
    @Override
    public List<Course> getSharedCourses() {
        return courseDao.getSharedCourses();
    }

    /**获取所有共享课程的XML文件*/
    @Override
    public String getSharedCoursesXml() {
        List<Course> sharedClassList = getSharedCourses();
        try {
            String outcome = Utils.coursesToXml(sharedClassList);
            return outcome;
        } catch (Exception ignored) {}
        return null;
    }

    /**获取其它院系的共享课程*/
    @Override
    public List<Course> getOtherDepartmentCourses() {
        try {
            String content = restTemplate.getForObject("http://localhost:9000/b/course/getOtherDepartmentCourses",String.class);
            return Utils.xmlToCourses(content);
        } catch (Exception ignored) {}
        return null;
    }
}
