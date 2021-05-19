package com.data_integration.c.service.courseSelecting;

import com.data_integration.c.PO.Course;
import com.data_integration.c.PO.CourseSelecting;
import com.data_integration.c.PO.Student;
import com.data_integration.c.VO.CourseSelectingVO;
import com.data_integration.c.VO.SelectCourseVO;
import com.data_integration.c.mapper.course.CourseMapper;
import com.data_integration.c.mapper.courseSelecting.CourseSelectingMapper;
import com.data_integration.c.mapper.student.StudentMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseSelectingService {
    @Autowired
    CourseSelectingMapper courseSelectingMapper;
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    StudentMapper studentMapper;

    public List<CourseSelectingVO> getAllCourseSelecting(){
        List<CourseSelectingVO> courseSelectingVOList = courseSelectingMapper.getAllCourseSelecting().stream().map(courseSelecting -> {
            CourseSelectingVO courseSelectingVO = new CourseSelectingVO();
            BeanUtils.copyProperties(courseSelecting,courseSelectingVO);
            return courseSelectingVO;
        }).collect(Collectors.toList());
        return courseSelectingVOList;
    }

    public List<CourseSelectingVO> getCourseSelectingBySno(String sno){
        List<CourseSelecting> courseSelectingList = courseSelectingMapper.getCourseSelectingBySno(sno);
        List<CourseSelectingVO> courseSelectingVOList = courseSelectingList.stream().map(courseSelecting -> {
            CourseSelectingVO courseSelectingVO = new CourseSelectingVO();
            BeanUtils.copyProperties(courseSelecting,courseSelectingVO);
            return courseSelectingVO;
        }).collect(Collectors.toList());
        return courseSelectingVOList;
    }

    public List<CourseSelectingVO> getCourseSelectingByCno(String cno){
        List<CourseSelecting> courseSelectingList = courseSelectingMapper.getCourseSelectingByCno(cno);
        List<CourseSelectingVO> courseSelectingVOList = courseSelectingList.stream().map(courseSelecting -> {
            CourseSelectingVO courseSelectingVO = new CourseSelectingVO();
            BeanUtils.copyProperties(courseSelecting,courseSelectingVO);
            return courseSelectingVO;
        }).collect(Collectors.toList());
        return courseSelectingVOList;
    }

    public boolean addCourseSelecting(SelectCourseVO selectCourseVO){
        Course course = courseMapper.getCourseByCno(selectCourseVO.cno);
        Student student = studentMapper.getStudentBySno(selectCourseVO.sno);
        if(student.permission<course.permission)
            return false;
        CourseSelecting courseSelecting = new CourseSelecting();
        BeanUtils.copyProperties(selectCourseVO,courseSelecting);
        try {
            courseSelectingMapper.addCourseSelecting(courseSelecting);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public void deleteCourseSelecting(SelectCourseVO selectCourseVO){
        CourseSelecting courseSelecting = new CourseSelecting();
        BeanUtils.copyProperties(selectCourseVO,courseSelecting);
        courseSelectingMapper.deleteCourseSelecting(courseSelecting);
    }

    public void updateGrade(CourseSelectingVO courseSelectingVO){
        CourseSelecting courseSelecting = new CourseSelecting();
        BeanUtils.copyProperties(courseSelectingVO,courseSelecting);
        courseSelectingMapper.updateGrade(courseSelecting);
    }
}
