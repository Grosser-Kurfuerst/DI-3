package com.data_integration.c.mapper.courseSelecting;

import com.data_integration.c.PO.CourseSelecting;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CourseSelectingMapper {
    List<CourseSelecting> getAllCourseSelecting();
    List<CourseSelecting> getCourseSelectingBySno(String sno);
    List<CourseSelecting> getCourseSelectingByCno(String sco);
    CourseSelecting getCourseSelectingBySnoAndCno(CourseSelecting courseSelecting);
    void addCourseSelecting(CourseSelecting courseSelecting);
    void deleteCourseSelecting(CourseSelecting courseSelecting);
    void updateGrade(CourseSelecting courseSelecting);
}
