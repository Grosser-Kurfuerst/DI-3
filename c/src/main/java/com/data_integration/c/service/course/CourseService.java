package com.data_integration.c.service.course;

import com.data_integration.c.PO.Course;
import com.data_integration.c.VO.CourseVO;
import com.data_integration.c.mapper.course.CourseMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {
    @Autowired
    CourseMapper courseMapper;

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

    public void updateCourseShare(String cno, String share){
        courseMapper.updateCourseShare(cno,share);
    }
}
