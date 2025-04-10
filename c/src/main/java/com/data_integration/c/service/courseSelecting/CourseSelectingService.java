package com.data_integration.c.service.courseSelecting;

import com.data_integration.c.PO.Course;
import com.data_integration.c.PO.CourseSelecting;
import com.data_integration.c.PO.Student;
import com.data_integration.c.VO.CourseSelectingVO;
import com.data_integration.c.VO.SelectCourseVO;
import com.data_integration.c.mapper.course.CourseMapper;
import com.data_integration.c.mapper.courseSelecting.CourseSelectingMapper;
import com.data_integration.c.mapper.student.StudentMapper;
import com.data_integration.c.utils.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
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
    @Autowired
    RestTemplate restTemplate;

    public List<CourseSelectingVO> getAllCourseSelecting() {
//        List<CourseSelecting> coursesSelectings = courseSelectingMapper.getAllCourseSelecting();
//        List<CourseSelectingVO> courseSelectingVOList = coursesSelectings.stream().map(courseSelecting -> {
//            Course c = courseMapper.getCourseByCno(courseSelecting.cno);
//            CourseSelectingVO courseSelectingVO = new CourseSelectingVO();
//            BeanUtils.copyProperties(c, courseSelectingVO);
//            courseSelectingVO.grd = courseSelecting.grd;
//            courseSelectingVO.sno = courseSelecting.sno;
//            return courseSelectingVO;
//        }).collect(Collectors.toList());
        List<CourseSelecting> courses = courseSelectingMapper.getAllCourseSelecting();
        List<CourseSelectingVO> courseSelectingVOList = courses.stream().map(course -> {
            CourseSelectingVO courseSelectingVO = new CourseSelectingVO();
            BeanUtils.copyProperties(course, courseSelectingVO);
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
//        List<CourseSelectingVO> courseSelectingVOList = courseSelectingList.stream().map(courseSelecting -> {
//            Course c = courseMapper.getCourseByCno(courseSelecting.cno);
//            CourseSelectingVO courseSelectingVO = new CourseSelectingVO();
//            BeanUtils.copyProperties(c, courseSelectingVO);
//            courseSelectingVO.grd = courseSelecting.grd;
//            courseSelectingVO.sno = courseSelecting.sno;
//            return courseSelectingVO;
//        }).collect(Collectors.toList());
        return courseSelectingVOList;
    }

    public List<CourseSelectingVO> getCourseSelectingByCno(String cno){
        List<CourseSelecting> courseSelectingList = courseSelectingMapper.getCourseSelectingByCno(cno);
        List<CourseSelectingVO> courseSelectingVOList = courseSelectingList.stream().map(courseSelecting -> {
            CourseSelectingVO courseSelectingVO = new CourseSelectingVO();
            BeanUtils.copyProperties(courseSelecting,courseSelectingVO);
            return courseSelectingVO;
        }).collect(Collectors.toList());
//        List<CourseSelectingVO> courseSelectingVOList = courseSelectingList.stream().map(courseSelecting -> {
//            Course c = courseMapper.getCourseByCno(courseSelecting.cno);
//            CourseSelectingVO courseSelectingVO = new CourseSelectingVO();
//            BeanUtils.copyProperties(c, courseSelectingVO);
//            courseSelectingVO.grd = courseSelecting.grd;
//            courseSelectingVO.sno = courseSelecting.sno;
//            return courseSelectingVO;
//        }).collect(Collectors.toList());
        return courseSelectingVOList;
    }

    public boolean addCourseSelecting(CourseSelectingVO courseSelectingVO) throws Exception {
        Student student = studentMapper.getStudentBySno(courseSelectingVO.sno);
        // 是本院的课
        if(courseSelectingVO.cno.startsWith("CS")){
            Course course = courseMapper.getCourseByCno(courseSelectingVO.cno);
//            if(student.permission<course.permission)
//                return false;
            CourseSelecting courseSelecting = new CourseSelecting();
            BeanUtils.copyProperties(courseSelectingVO, courseSelecting);
            try {
                courseSelectingMapper.addCourseSelecting(courseSelecting);
            }catch (Exception e){
                return false;
            }
            return true;
        }

        // 不是本院的课，生成xml并转为集成格式
        // 学生
        String studentXml = Utils.studentToXml(student);
        // 验证
        URL schemaUrl = getClass().getResource("/schema/studentC.xsd");
        File schemaFile = new File(URLDecoder.decode(schemaUrl.getFile(),"UTF-8"));
        Utils.validateSchema(schemaFile,studentXml);
        // 转换为集成格式
        URL xslUrl = getClass().getResource("/xsl/formatStudent.xsl");
        studentXml = Utils.transform(URLDecoder.decode(xslUrl.getFile(),"UTF-8"),studentXml);

        // 选课
        String selectingXml = Utils.selectingToXml(courseSelectingVO);
        // 验证
        schemaUrl = getClass().getResource("/schema/choiceC.xsd");
        schemaFile = new File(URLDecoder.decode(schemaUrl.getFile(),"UTF-8"));
        Utils.validateSchema(schemaFile,selectingXml);
        // 转换为集成格式
        xslUrl = getClass().getResource("/xsl/formatClassChoice.xsl");
        selectingXml = Utils.transform(URLDecoder.decode(xslUrl.getFile(),"UTF-8"),selectingXml);

        // 拼接
        String toSend = studentXml+selectingXml;

        // 使用RestTemplate向集成服务器发送请求
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/xml;charset=UTF-8");
        headers.setContentType(type);
        HttpEntity<String> httpEntity = new HttpEntity<>(toSend,headers);

        String res = "";
        // 是a的课
        if(courseSelectingVO.cno.startsWith("BA")){
            // res是集成服务器的ResponseBody，是xml字符串
            // TODO 这里是集成服务器url
            res = restTemplate.postForObject(Utils.serverIntegrator + "/a/courseSelecting/addCourseSelecting",httpEntity,String.class);
        }
        // 是b的课
        else if(courseSelectingVO.cno.length() == 5){
            // res是集成服务器的ResponseBody，是xml字符串
            // TODO 这里是集成服务器url
            res = restTemplate.postForObject(Utils.serverIntegrator + "/b/courseSelecting/addCourseSelecting",httpEntity,String.class);
        }
        if (res.equals("true")) {
            CourseSelecting courseSelecting = new CourseSelecting();
            BeanUtils.copyProperties(courseSelectingVO, courseSelecting);
            try {
                courseSelectingMapper.addCourseSelecting(courseSelecting);
            }catch (Exception e){
                return false;
            }
            return true;
        }
        else
            return false;
    }

    public void deleteCourseSelecting(SelectCourseVO selectCourseVO) throws Exception {
        CourseSelecting courseSelecting = new CourseSelecting();
        BeanUtils.copyProperties(selectCourseVO, courseSelecting);
        // 是本院的课
        if(selectCourseVO.cno.startsWith("CS")){
            try {
                courseSelectingMapper.deleteCourseSelecting(courseSelecting);
            }catch (Exception e){
                return;
            }
            return;
        }

        // 不是本院的课，生成xml并转为集成格式
        // 学生
//        String studentXml = Utils.studentToXml(student);
//        // 验证
//        URL schemaUrl = getClass().getResource("/schema/studentC.xsd");
//        File schemaFile = new File(URLDecoder.decode(schemaUrl.getFile(),"UTF-8"));
//        Utils.validateSchema(schemaFile,studentXml);
//        // 转换为集成格式
//        URL xslUrl = getClass().getResource("/xsl/formatStudent.xsl");
//        studentXml = Utils.transform(URLDecoder.decode(xslUrl.getFile(),"UTF-8"),studentXml);

        // 选课
        CourseSelectingVO courseSelectingVO = new CourseSelectingVO();
        BeanUtils.copyProperties(selectCourseVO, courseSelectingVO);
        String selectingXml = Utils.selectingToXml(courseSelectingVO);
        // 验证
        URL schemaUrl = getClass().getResource("/schema/choiceC.xsd");
        File schemaFile = new File(URLDecoder.decode(schemaUrl.getFile(),"UTF-8"));
        Utils.validateSchema(schemaFile, selectingXml);
        // 转换为集成格式
        URL xslUrl = getClass().getResource("/xsl/formatClassChoice.xsl");
        selectingXml = Utils.transform(URLDecoder.decode(xslUrl.getFile(),"UTF-8"),selectingXml);

        // 拼接
        String toSend = selectingXml;

        // 使用RestTemplate向集成服务器发送请求
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/xml;charset=UTF-8");
        headers.setContentType(type);
        HttpEntity<String> httpEntity = new HttpEntity<>(toSend,headers);

        String res = "";
        // 是a的课
        if(courseSelectingVO.cno.startsWith("BA")){
            // res是集成服务器的ResponseBody，是xml字符串
            // TODO 这里是集成服务器url
            res = restTemplate.postForObject(Utils.serverIntegrator + "/a/courseSelecting/deleteCourseSelecting",httpEntity,String.class);
        }
        // 是b的课
        else if(courseSelectingVO.cno.length() == 5){
            // res是集成服务器的ResponseBody，是xml字符串
            // TODO 这里是集成服务器url
            res = restTemplate.postForObject(Utils.serverIntegrator + "/b/courseSelecting/deleteCourseSelecting",httpEntity,String.class);
        }
        if (res.equals("true")) {
            try {
                courseSelectingMapper.deleteCourseSelecting(courseSelecting);
            }catch (Exception e){
                return;
            }
            return;
        }
    }

    public void updateGrade(CourseSelectingVO courseSelectingVO){
        CourseSelecting courseSelecting = new CourseSelecting();
        BeanUtils.copyProperties(courseSelectingVO, courseSelecting);
        courseSelectingMapper.updateGrade(courseSelecting);
    }

    public String addCourseSelectingXml(String content) throws Exception {
        // 分割学生和选课
        int splitIndex = content.indexOf("</students>")+"</students>".length();
        String studentXml = content.substring(0,splitIndex);
        String choiceXml = content.substring(splitIndex);
        // 验证
        URL schemaUrl = getClass().getResource("/schema/studentC.xsd");
        File schemaFile = new File(URLDecoder.decode(schemaUrl.getFile(),"UTF-8"));
        Utils.validateSchema(schemaFile,studentXml);
        // 验证
        schemaUrl = getClass().getResource("/schema/choiceC.xsd");
        schemaFile = new File(URLDecoder.decode(schemaUrl.getFile(),"UTF-8"));
        Utils.validateSchema(schemaFile,choiceXml);

        // 将学生xml转换成student对象
        Student student = Utils.xmlToStudents(studentXml);
        CourseSelecting courseSelecting = Utils.xmlToSelecting(choiceXml);

        // 看该学生有没有权限选择该课程
        Course courseToSelect = courseMapper.getCourseByCno(courseSelecting.cno);
        if (courseToSelect == null ) return "false"; // 没有该课程Id对应的课程
        // 学生有权限
//        if (courseToSelect.permission <= student.permission){
//
//            return "true";
//        }
        try {
            courseSelectingMapper.addCourseSelecting(courseSelecting);
        }catch (Exception e){
            return "false";
        }
        return "true";
    }

    public String deleteCourseSelectingXml(String content) throws Exception {
        // 分割学生和选课
//        int splitIndex = content.indexOf("</students>")+"</students>".length();
//        String studentXml = content.substring(0,splitIndex);
        String choiceXml = content;
        // 验证
//        schemaUrl = getClass().getResource("/schema/studentC.xsd");
        // File schemaFile = new File(URLDecoder.decode(schemaUrl.getFile(),"UTF-8"));
        // Utils.validateSchema(schemaFile,studentXml);
        // 验证
        URL schemaUrl = getClass().getResource("/schema/choiceC.xsd");
        File schemaFile = new File(URLDecoder.decode(schemaUrl.getFile(),"UTF-8"));
        Utils.validateSchema(schemaFile,choiceXml);

        // 将学生xml转换成student对象
        // Student student = Utils.xmlToStudents(studentXml);
        CourseSelecting courseSelecting = Utils.xmlToSelecting(choiceXml);

        // 看该学生有没有权限选择该课程
        Course courseToSelect = courseMapper.getCourseByCno(courseSelecting.cno);
        if (courseToSelect == null ) return "false"; // 没有该课程Id对应的课程
        // 学生有权限
//        if (courseToSelect.permission <= student.permission){
//
//            return "true";
//        }
        try {
            courseSelectingMapper.deleteCourseSelecting(courseSelecting);
        }catch (Exception e){
            return "false";
        }
        return "true";
    }
}
