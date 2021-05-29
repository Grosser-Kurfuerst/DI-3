package com.data_integration.c.service.course;

import com.data_integration.c.PO.Course;
import com.data_integration.c.VO.CourseVO;
import com.data_integration.c.mapper.course.CourseMapper;
import com.data_integration.c.utils.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

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

    public void updateCourseShare(String cno, String share){
        courseMapper.updateCourseShare(cno,share);
    }

    public String getSharedCoursesXml() throws Exception{
        List<Course> sharedCourseList = courseMapper.getAllCourses()
                .stream()
                .filter(course -> course.share.equals("Y"))
                .collect(Collectors.toList());
        return Utils.coursesToXml(sharedCourseList);
    }

    public List<Course> getOtherDepartmentCourses() throws Exception {
        // TODO 调用集成服务器接口 获得a和b的xml
        String content = "";
        return Utils.xmlToCourses(content);
    }
}
