package com.data_integration.c.mapper.courseSelecting;

import com.data_integration.c.PO.CourseSelecting;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseSelectingTest {
    @Autowired
    CourseSelectingMapper courseSelectingMapper;

    @Test
    public void testGetAllCourseSelecting() throws Exception{
        List<CourseSelecting> courseSelectingList = courseSelectingMapper.getAllCourseSelecting();
        for (CourseSelecting courseSelecting : courseSelectingList)
            System.out.println(courseSelecting.cno +' '+ courseSelecting.sno +' '+ courseSelecting.grd);
    }

    @Test
    public void testGetCourseSelectingBySno() throws Exception{
        List<CourseSelecting> courseSelectingList = courseSelectingMapper.getCourseSelectingBySno("123456789");
        for (CourseSelecting courseSelecting : courseSelectingList)
            System.out.println(courseSelecting.cno +' '+ courseSelecting.sno +' '+ courseSelecting.grd);
    }

    @Test
    public void testGetCourseSelectingBySnoAndCno() throws Exception{
        CourseSelecting courseSelecting = courseSelectingMapper.getCourseSelectingBySnoAndCno(new CourseSelecting(){{this.sno ="123456789";this.cno ="1234";}});
        Assert.assertEquals("1234", courseSelecting.cno);
    }

    @Test
    public void testAddCourseSelecting() throws Exception{
        courseSelectingMapper.addCourseSelecting(new CourseSelecting(){{this.sno ="123456788";this.cno ="1234";}});
        CourseSelecting courseSelecting = courseSelectingMapper.getCourseSelectingBySnoAndCno(new CourseSelecting(){{this.sno ="123456788";this.cno ="1234";}});
        Assert.assertEquals("1234", courseSelecting.cno);
    }

    @Test
    public void testDeleteCourseSelecting() throws Exception{
        courseSelectingMapper.deleteCourseSelecting(new CourseSelecting(){{this.sno ="123456789";this.cno ="1234";}});
        CourseSelecting courseSelecting = courseSelectingMapper.getCourseSelectingBySnoAndCno(new CourseSelecting(){{this.sno ="123456789";this.cno ="1234";}});
        Assert.assertNull(courseSelecting);
    }

    @Test
    public void testUpdateGrade() throws Exception{
        courseSelectingMapper.updateGrade(new CourseSelecting(){{this.sno ="123456789";this.cno ="1233";this.grd =70;}});
        CourseSelecting courseSelecting = courseSelectingMapper.getCourseSelectingBySnoAndCno(new CourseSelecting(){{this.sno ="123456789";this.cno ="1233";}});
        Assert.assertEquals(Integer.valueOf(70), courseSelecting.grd);
    }
}
