package com.data_integration.a.service.courseSelecting;

import com.data_integration.a.PO.Course;
import com.data_integration.a.PO.CourseSelecting;
import com.data_integration.a.PO.Student;
import com.data_integration.a.VO.CourseSelectingVO;
import com.data_integration.a.VO.SelectCourseVO;
import com.data_integration.a.mapper.course.CourseMapper;
import com.data_integration.a.mapper.courseSelecting.CourseSelectingMapper;
import com.data_integration.a.mapper.student.StudentMapper;
import com.data_integration.a.service.course.CourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.data_integration.a.utils.Utils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
    @Autowired
    CourseService courseService;


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

    public boolean addCourseSelecting(SelectCourseVO selectCourseVO)throws Exception{
        Student student = studentMapper.getStudentBySno(selectCourseVO.studentnum);

        // 是本院的课
        if(selectCourseVO.coursenum.charAt(selectCourseVO.coursenum.length()-1) == selectCourseVO.studentnum.charAt(selectCourseVO.studentnum.length()-1)){
            Course course = courseMapper.getCourseByCno(selectCourseVO.coursenum);
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
        // 不是本院的课，生成xml并转为集成格式
        // 学生
        String studentXml = Utils.studentToXml(student);
        // 验证
        URL schemaUrl = getClass().getResource("/schema/studentA.xsd");
        File schemaFile = new File(URLDecoder.decode(schemaUrl.getFile(),"UTF-8"));
        Utils.validateSchema(schemaFile,studentXml);
        // 转换为集成格式
        URL xslUrl = getClass().getResource("/xsl/formatStudent.xsl");
        studentXml = Utils.transform(URLDecoder.decode(xslUrl.getFile(),"UTF-8"),studentXml);

        // 选课
        String selectingXml = Utils.selectingToXml(selectCourseVO);
        // 验证
        schemaUrl = getClass().getResource("/schema/choiceA.xsd");
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
        // 是c的课
        if(selectCourseVO.coursenum.charAt(selectCourseVO.coursenum.length()-1)=='c'){
            // res是集成服务器的ResponseBody，是xml字符串
            // TODO 这里是集成服务器url
            res = restTemplate.postForObject("http://localhost:9000/c/courseSelecting/addCourseSelecting",httpEntity,String.class);
        }
        // 是b的课
        else if(selectCourseVO.coursenum.charAt(selectCourseVO.coursenum.length()-1)=='b'){
            // res是集成服务器的ResponseBody，是xml字符串
            // TODO 这里是集成服务器url
            res = restTemplate.postForObject("http://localhost:9000/b/courseSelecting/addCourseSelecting",httpEntity,String.class);
        }
        if (res.equals("true")){
            CourseSelecting courseSelecting = new CourseSelecting();
            BeanUtils.copyProperties(selectCourseVO,courseSelecting);
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

    public String addCourseSelectingXml(String content) throws Exception{
        // 分割学生和选课
        int splitIndex = content.indexOf("</students>")+"</students>".length();
        String studentXml = content.substring(0,splitIndex);
        String choiceXml = content.substring(splitIndex);
        // 验证
        URL schemaUrl = getClass().getResource("/schema/studentA.xsd");
        File schemaFile = new File(URLDecoder.decode(schemaUrl.getFile(),"UTF-8"));
        Utils.validateSchema(schemaFile,studentXml);
        // 验证
        schemaUrl = getClass().getResource("/schema/choiceA.xsd");
        schemaFile = new File(URLDecoder.decode(schemaUrl.getFile(),"UTF-8"));
        Utils.validateSchema(schemaFile,choiceXml);

        // 将学生xml转换成student对象
        List<Student> studentList = Utils.xmlToStudents(studentXml);
        List<CourseSelecting> electionList = Utils.xmlToElections(choiceXml);

        // 看该学生有没有权限选择该课程
        Course courseToSelect = courseMapper.getCourseByCno(electionList.get(0).getCoursenum());
        if (courseToSelect == null ) return "false"; // 没有该课程Id对应的课程
        if (courseToSelect.getPermission() <= studentList.get(0).getPermission()){
            try {
                courseSelectingMapper.addCourseSelecting(electionList.get(0));
            }catch (Exception e){
                return "false";
            }
            return "true";
        }
        return "false";
    }
}
