package com.data_integration.b.serviceImpl.election;


import com.data_integration.b.dao.election.ElectionDao;
import com.data_integration.b.pojo.account.Account;
import com.data_integration.b.pojo.course.Course;
import com.data_integration.b.pojo.election.Election;
import com.data_integration.b.pojo.student.Student;
import com.data_integration.b.service.admin.AdminService;
import com.data_integration.b.service.course.CourseService;
import com.data_integration.b.service.election.ElectionService;
import com.data_integration.b.service.student.StudentService;
import com.data_integration.b.utils.Utils;
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

@Service
public class ElectionServiceImpl implements ElectionService {

    @Autowired
    ElectionDao electionDao;

    @Autowired
    StudentService studentService;

    @Autowired
    AdminService accountService;

    @Autowired
    CourseService courseService;

    @Autowired
    RestTemplate restTemplate;

    /**
     * 获取某学生所选择的课程信息
     */
    @Override
    public List<Election> getElectionsBySid(String sid) {
        return electionDao.getElectionsBySid(sid);
    }


    /**
     * 添加选课记录
     */
    public int addElectionBySidCidScore(String cid, String sid, String score) {
        Election election = electionDao.getElectionByCidSid(cid, sid); // 看之前存不存在
        if (election == null) return electionDao.addElectionBySidCidScore(cid, sid, score);
        else return 0;
    }

    /**
     * 删除选课记录
     */
    @Override
    public int deleteElectionBySidCid(String cid, String sid) {
        return electionDao.deleteElectionBySidCid(cid, sid);
    }


    /**
     * 获取某门课程的选课信息
     * @param cid 课程编号
     */
    @Override
    public List<Election> getElectionsByCid(String cid) {
        return electionDao.getElectionsByCid(cid);
    }

    /**
     * 获取所有的选课信息
     */
    @Override
    public List<Election> getElections() {
        return electionDao.getElections();
    }


    /**
     * 更新课程成绩
     */
    @Override
    public int updateGrade(String cid, String sid, int score) {
        return electionDao.updateGrade(cid, sid, score);
    }


    /**
     * 增加选课
     */
    @Override
    public boolean addCourseSelecting(Election election) throws Exception {
        Account account = accountService.getAccountBySid(election.getStudentId());
        int permission = account.getPower_grade();
        Student student = studentService.getStudentBySid(election.getStudentId());
        // 是本院的课
        if(election.getCourseId().charAt(election.getCourseId().length()-1) == student.getSid().charAt(student.getSid().length()-1)){
            // 学生账户的权限大于课程的权限才可以添加选课记录
            Course course = courseService.getCourseByCid(election.getCourseId());
            if (course == null) return false;
            int coursePowerGrade = course.getPowerGrade(); // 课程的权限
            if (permission >= coursePowerGrade) {
                // 可以选课
                addElectionBySidCidScore(election.getCourseId(), election.getStudentId(), election.getScore());
                return true;
            }
            return false;
        }

        // 不是本院的课，生成xml并转为集成格式
        // 学生
        String studentXml = Utils.studentToXml(student, permission);
        // 验证
        URL schemaUrl = getClass().getResource("/schema/studentB.xsd");
        File schemaFile = new File(URLDecoder.decode(schemaUrl.getFile(),"UTF-8"));
        Utils.validateSchema(schemaFile,studentXml);
        // 转换为集成格式
        URL xslUrl = getClass().getResource("/xsl/formatStudent.xsl");
        studentXml = Utils.transform(URLDecoder.decode(xslUrl.getFile(),"UTF-8"),studentXml);

        // 选课
        String selectingXml = Utils.selectingToXml(election);
        // 验证
        schemaUrl = getClass().getResource("/schema/choiceB.xsd");
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
        if(election.getCourseId().charAt(election.getCourseId().length()-1)=='a'){
            // res是集成服务器的ResponseBody，是xml字符串
            // TODO 这里是集成服务器url
            res = restTemplate.postForObject("http://localhost:9000/a/courseSelecting/addCourseSelecting",httpEntity,String.class);
        }
        // 是c的课
        else if(election.getCourseId().charAt(election.getCourseId().length()-1)=='c'){
            // res是集成服务器的ResponseBody，是xml字符串
            // TODO 这里是集成服务器url
            res = restTemplate.postForObject("http://localhost:9000/c/courseSelecting/addCourseSelecting",httpEntity,String.class);
        }
        if (res.equals("true"))
            return true;
        else
            return false;
    }


    @Override
    public String addCourseSelectingXml(String content) throws Exception {
        // 分割学生和选课
        int splitIndex = content.indexOf("</students>")+"</students>".length();
        String studentXml = content.substring(0,splitIndex);
        String choiceXml = content.substring(splitIndex);
        // 验证
        URL schemaUrl = getClass().getResource("/schema/studentB.xsd");
        File schemaFile = new File(URLDecoder.decode(schemaUrl.getFile(),"UTF-8"));
        Utils.validateSchema(schemaFile,studentXml);
        // 验证
        schemaUrl = getClass().getResource("/schema/choiceB.xsd");
        schemaFile = new File(URLDecoder.decode(schemaUrl.getFile(),"UTF-8"));
        Utils.validateSchema(schemaFile,choiceXml);

        // 将学生xml转换成student对象
        List<Student> studentList = Utils.xmlToStudents(studentXml);
        List<Election> electionList = Utils.xmlToElections(choiceXml);


        // TODO 选课存取数据库逻辑

        return "true";
    }
}
