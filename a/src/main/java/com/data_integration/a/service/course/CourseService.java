package com.data_integration.a.service.course;

import com.data_integration.a.PO.Course;
import com.data_integration.a.VO.CourseVO;
import com.data_integration.a.mapper.course.CourseMapper;
import com.data_integration.a.utils.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    RestTemplate restTemplate;

    public List<CourseVO> getAllCourses(){
        List<CourseVO> courseVOList = courseMapper.getAllCourses().stream().map(course -> {
            CourseVO courseVO = new CourseVO();
            BeanUtils.copyProperties(course,courseVO);
            return courseVO;
        }).collect(Collectors.toList());
        return courseVOList;
    }

    public void updateCourseInfo(CourseVO courseVO){
        Course course = new Course();
        BeanUtils.copyProperties(courseVO,course);
        courseMapper.updateCourseInfo(course);
    }

    public void updateCourseShare(String coursenum, String share){
        courseMapper.updateCourseShare(coursenum,share);
    }


    public List<Course> getOtherDepartmentCourses() throws Exception {
        // TODO 这里是集成服务器url
        String content = restTemplate.getForObject("http://localhost:9000/a/course/getOtherDepartmentCourses",String.class);
        return Utils.xmlToCourses(content);
    }
}
