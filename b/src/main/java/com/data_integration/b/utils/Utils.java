package com.data_integration.b.utils;

import com.data_integration.b.pojo.course.Course;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

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
import java.util.ArrayList;
import java.util.List;

public class Utils {
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

    /**
     * course转b课程xml
     */
    public static String coursesToXml(List<Course> courseList) throws Exception {
        Document document = getDocument(null);
        Element root = document.createElement("classes");
        root.setAttribute("xmlns", "http://b.nju.edu.cn/schema");
        document.appendChild(root);
        for (Course course:courseList){
            Element classElement = document.createElement("class");
            root.appendChild(classElement);

            // 课程编号
            Element classIdElement = document.createElement("编号");
            classIdElement.setTextContent(course.getCourseId());
            classElement.appendChild(classIdElement);

            // 课程名
            Element classNameElement = document.createElement("名称");
            classNameElement.setTextContent(course.getCourseName());
            classElement.appendChild(classNameElement);

            // 课时元素
            Element classTimeElement = document.createElement("课时");
            classTimeElement.setTextContent(course.getCourseTime());
            classElement.appendChild(classTimeElement);

            // 学分元素
            Element classScoreElement = document.createElement("学分");
            classScoreElement.setTextContent(course.getScore());
            classElement.appendChild(classScoreElement);

            // 老师元素
            Element teacherElement = document.createElement("老师");
            teacherElement.setTextContent(course.getTeacherName());
            classElement.appendChild(teacherElement);

            // 上课地点元素
            Element placeElement = document.createElement("地点");
            placeElement.setTextContent(course.getTeachingPlace());
            classElement.appendChild(placeElement);
        }

        return toFormatedXML(document);
    }

    /**
     * B格式的课程xml转对象
     */
    public static List<Course> xmlToCourses(String content) throws Exception{
        List<Course> courseList = new ArrayList<>();
        Document document = getDocument(content);
        Node classesNode = document.getFirstChild();
        Node classNode = classesNode.getFirstChild();
        // 遍历classes
        while (classNode!=null){
            Course course = new Course();
            courseList.add(course);
            // 遍历class下元素 坑在于元素名用getNodeName() 值要getFirstChild().getNodeValue()
            for (Node child = classNode.getFirstChild(); child != null; child = child.getNextSibling()) {
                String nodeName = child.getNodeName();
                // 因为转换到集成格式后会丢失课时信息，所以课时的getFirstChild()为null
                String nodeTextValue = null;
                if (child.getFirstChild()!=null)
                    nodeTextValue = child.getFirstChild().getNodeValue();
                switch (nodeName){
                    case "编号":
                        course.setCourseId(nodeTextValue);
                        break;
                    case "名称":
                        course.setCourseName(nodeTextValue);
                        break;
                    case "学分":
                        course.setScore(nodeTextValue);
                        break;
                    case "老师":
                        course.setTeacherName(nodeTextValue);
                        break;
                    case "地点":
                        course.setTeachingPlace(nodeTextValue);
                        break;
                    default:
                        break;
                }
            }
            classNode = classNode.getNextSibling();
        }
        return courseList;
    }
}
