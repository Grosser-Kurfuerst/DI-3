package com.data_integration.a.mapper.courseSelecting;

import com.data_integration.a.PO.CourseSelecting;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CourseSelectingMapper {
    List<CourseSelecting> getAllCourseSelecting();
    List<CourseSelecting> getCourseSelectingBySno(String studentnum);
    List<CourseSelecting> getCourseSelectingByCno(String coursenum);
    CourseSelecting getCourseSelectingBySnoAndCno(CourseSelecting courseSelecting);
    void addCourseSelecting(CourseSelecting courseSelecting);
    void deleteCourseSelecting(CourseSelecting courseSelecting);
    void updateGrade(CourseSelecting courseSelecting);
}
