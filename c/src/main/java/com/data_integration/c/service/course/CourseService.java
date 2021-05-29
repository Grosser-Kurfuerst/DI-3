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

    public String getAllCoursesXml() throws Exception{
        List<Course> sharedCourseList = courseMapper.getAllCourses()
                .stream()
                .filter(course -> course.share.equals("Y"))
                .collect(Collectors.toList());
        Document document = Utils.getDocument(null);
        Element root = document.createElement("classes");
        root.setAttribute("xmlns", "http://c.nju.edu.cn/schema");
        document.appendChild(root);
        for (Course course:sharedCourseList){
            Element classElement = document.createElement("class");
            root.appendChild(classElement);

            Element cnoElement = document.createElement("Cno");
            cnoElement.setTextContent(course.cno);
            classElement.appendChild(cnoElement);

            Element cnmElement = document.createElement("Cnm");
            cnmElement.setTextContent(course.cnm);
            classElement.appendChild(cnmElement);

            Element ctmElement = document.createElement("Ctm");
            ctmElement.setTextContent(""+course.ctm);
            classElement.appendChild(ctmElement);

            Element cptElement = document.createElement("Cpt");
            cptElement.setTextContent(""+course.cpt);
            classElement.appendChild(cptElement);

            Element tecElement = document.createElement("Tec");
            tecElement.setTextContent(course.tec);
            classElement.appendChild(tecElement);

            Element plaElement = document.createElement("Pla");
            plaElement.setTextContent(course.pla);
            classElement.appendChild(plaElement);
        }
        return Utils.toFormatedXML(document);
    }
}
