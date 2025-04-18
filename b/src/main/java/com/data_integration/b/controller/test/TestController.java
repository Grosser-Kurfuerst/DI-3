package com.data_integration.b.controller.test;

import com.data_integration.b.pojo.election.Election;
import com.data_integration.b.utils.Utils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/test")
public class TestController {

    RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/sendXml")
    public String sendXml() throws Exception {
        // 创建一个对象
        Election election = new Election();
        election.setCourseId("b1234");
        election.setStudentId("b12345678");
        election.setScore("90");

        // ① 创建 Document
        Document document = getDocument(null);
        // ② 创建根元素：choices
        Element root = document.createElement("choices");
        // ③ 往 document、根元素 中填充学生的数据

        // 设置属性：命名空间
        root.setAttribute("xmlns", "http://b.nju.edu.cn/schema");
        // 设置choice元素
        Element choiceElement = document.createElement("choice");
        // 学号
        Element idElement = document.createElement("学生编号");
        idElement.setTextContent(election.getStudentId());
        choiceElement.appendChild(idElement); // 添加到choice元素中
        // 课程编号
        Element courseIDElement = document.createElement("课程编号");
        courseIDElement.setTextContent(election.getCourseId());
        choiceElement.appendChild(courseIDElement); // 添加到choice元素中
        // 得分
        Element scoreElement = document.createElement("得分");
        scoreElement.setTextContent(election.getScore());
        choiceElement.appendChild(scoreElement); // 添加到choice元素中

        // 形成dom树关系
        root.appendChild(choiceElement);
        document.appendChild(root);

        // 转换为xml字符串
        String content = toFormatedXML(document);

        System.out.println(content);

        // 使用RestTemplate向集成服务器发送请求
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/xml;charset=UTF-8");
        headers.setContentType(type);
        HttpEntity<String> httpEntity = new HttpEntity<>(content,headers);
        // TODO 这里是集成服务器url
        // res是集成服务器的ResponseBody，是xml字符串
        String res = restTemplate.postForObject(Utils.serverIntegrator + "/test/test",httpEntity,String.class);
        System.out.println(res);
        return res;
    }


    /**
     * xml dom转字符串
     */
    public static String toFormatedXML(Document object) throws Exception {
        Document doc = (Document) object;
        TransformerFactory transFactory = TransformerFactory.newInstance();
        Transformer transFormer = transFactory.newTransformer();
        transFormer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        DOMSource domSource = new DOMSource(doc);

        StringWriter sw = new StringWriter();
        StreamResult xmlResult = new StreamResult(sw);

        transFormer.transform(domSource, xmlResult);

        return sw.toString();

    }


    /**
     * 将 XML解析成Document对象并返回
     * 如果传进来的是null，那么新建一个Document对象返回
     *
     * @return XML文档的模型树
     */
    public static Document getDocument(String content) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        // 如果xmlPath是空，直接创建一个新的
        if (content == null) return builder.newDocument();
        // 不是空的话就返回已有的document对象
        return builder.parse(new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8)));
    }
}
