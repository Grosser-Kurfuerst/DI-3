package com.data_integration.a.utils;

import com.data_integration.a.PO.Course;
import com.data_integration.a.PO.CourseSelecting;
import com.data_integration.a.PO.Student;
import com.data_integration.a.VO.SelectCourseVO;
import org.dom4j.io.DocumentResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    /**
     * course转a课程xml
     * @param courseList
     * @return
     * @throws Exception
     */
    public static String coursesToXml(List<Course> courseList) throws Exception {
        Document document = getDocument(null);
        Element root = document.createElement("classes");
        root.setAttribute("xmlns", "http://a.nju.edu.cn/schema");
        document.appendChild(root);
        for (Course course:courseList){
            Element classElement = document.createElement("class");
            root.appendChild(classElement);

            Element cnoElement = document.createElement("coursenum");
            cnoElement.setTextContent(course.coursenum);
            classElement.appendChild(cnoElement);

            Element cnmElement = document.createElement("coursename");
            cnmElement.setTextContent(course.coursename);
            classElement.appendChild(cnmElement);

            Element cptElement = document.createElement("credit");
            cptElement.setTextContent(""+course.credit);
            classElement.appendChild(cptElement);

            Element tecElement = document.createElement("teacher");
            tecElement.setTextContent(course.teacher);
            classElement.appendChild(tecElement);

            Element plaElement = document.createElement("place");
            plaElement.setTextContent(course.place);
            classElement.appendChild(plaElement);
        }
        return toFormatedXML(document);
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
     * 利用 xsl 将 xmlPath 对应的 xml 文件 转换成 targetPath 对应的xml 文件
     */
    public static String transform(String xslPath, String content) throws Exception {

        // ① 获取转换器工厂
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        // ② 获取转换器对象实例
        Transformer transformer = transformerFactory.newTransformer(new StreamSource(xslPath));
        //③ 进行转换
        DocumentResult result = new DocumentResult();
        transformer.transform(new StreamSource(new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8))), result);
        org.dom4j.Document transformedDoc = result.getDocument();
        return transformedDoc.asXML();
    }
    /**
     * 验证Schema
     */
    public static void validateSchema(File schemaFile, String content) throws Exception{
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        Schema schema = factory.newSchema(schemaFile);
        Validator validator = schema.newValidator();
        validator.validate(new StreamSource(new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8))));
    }
    /**
     * A格式的学生转xml
     */
    public static String studentToXml(Student student) throws Exception {
        Document document = getDocument(null);
        Element root = document.createElement("students");
        root.setAttribute("xmlns", "http://a.nju.edu.cn/schema");
        document.appendChild(root);
        Element studentElement = document.createElement("student");
        root.appendChild(studentElement);

        Element snoElement = document.createElement("stunum");
        snoElement.setTextContent(student.stunum);
        studentElement.appendChild(snoElement);

        Element snmElement = document.createElement("stuname");
        snmElement.setTextContent(student.stuname);
        studentElement.appendChild(snmElement);

        Element sexElement = document.createElement("sex");
        sexElement.setTextContent(student.sex);
        studentElement.appendChild(sexElement);

        Element sdeElement = document.createElement("department");
        sdeElement.setTextContent(student.department);
        studentElement.appendChild(sdeElement);

        Element permissionElement = document.createElement("permission");
        permissionElement.setTextContent(""+student.permission);
        studentElement.appendChild(permissionElement);

        return toFormatedXML(document);
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
     * A格式的课程xml转对象
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
                String nodeTextValue = child.getFirstChild().getNodeValue();
                switch (nodeName){
                    case "coursenum":
                        course.coursenum = nodeTextValue;
                        break;
                    case "coursename":
                        course.coursename = nodeTextValue;
                        break;
                    case "credit":
                        course.credit = Integer.parseInt(nodeTextValue);
                        break;
                    case "teacher":
                        course.teacher = nodeTextValue;
                        break;
                    case "place":
                        course.place = nodeTextValue;
                        break;
                    default:
                        break;
                }
            }
            classNode = classNode.getNextSibling();
        }
        return courseList;
    }
    /**
     * C格式的选课转xml
     */
    public static String selectingToXml(SelectCourseVO courseSelectingVO) throws Exception {
        Document document = getDocument(null);
        Element root = document.createElement("choices");
        root.setAttribute("xmlns", "http://a.nju.edu.cn/schema");
        document.appendChild(root);
        Element choiceElement = document.createElement("choice");
        root.appendChild(choiceElement);

        Element snoElement = document.createElement("studentnum");
        snoElement.setTextContent(courseSelectingVO.studentnum);
        choiceElement.appendChild(snoElement);

        Element cnoElement = document.createElement("coursenum");
        cnoElement.setTextContent(courseSelectingVO.coursenum);
        choiceElement.appendChild(cnoElement);

        Element grdElement = document.createElement("record");
        grdElement.setTextContent(courseSelectingVO.record==null?null:""+courseSelectingVO.record);
        choiceElement.appendChild(grdElement);

        return toFormatedXML(document);
    }
    /**
     * A格式的学生xml转对象
     */
    public static Student xmlToStudents(String content) throws Exception {
        Document document = getDocument(content);
        Node studentsNode = document.getFirstChild();
        Node studentNode = studentsNode.getFirstChild();

        Student student = new Student();
        // 遍历student下元素 坑在于元素名用getNodeName() 值要getFirstChild().getNodeValue()
        for (Node child = studentNode.getFirstChild(); child != null; child = child.getNextSibling()) {
            String nodeName = child.getNodeName();
            // 因为转换到集成格式后会丢失课时信息，所以课时的getFirstChild()为null
            String nodeTextValue = null;
            if (child.getFirstChild() != null)
                nodeTextValue = child.getFirstChild().getNodeValue();
            switch (nodeName) {
                case "stunum":
                    student.stunum = nodeTextValue;
                    break;
                case "stuname":
                    student.stuname = nodeTextValue;
                    break;
                case "sex":
                    student.sex = nodeTextValue;
                    break;
                case "department":
                    student.department = nodeTextValue;
                    break;
                case "permission":
                    student.permission = Integer.parseInt(nodeTextValue);
                    break;
                default:
                    break;
            }
        }
        return student;
    }
    /**
     * A格式的选课xml转对象
     */
    public static CourseSelecting xmlToSelecting(String content) throws Exception{
        Document document = getDocument(content);
        Node electionsNode = document.getFirstChild();
        Node electionNode = electionsNode.getFirstChild();

        CourseSelecting courseSelecting = new CourseSelecting();

        // 遍历choice下元素 坑在于元素名用getNodeName() 值要getFirstChild().getNodeValue()
        for (Node child = electionNode.getFirstChild(); child != null; child = child.getNextSibling()) {
            String nodeName = child.getNodeName();
            // 因为转换到集成格式后会丢失课时信息，所以课时的getFirstChild()为null
            String nodeTextValue = null;
            if (child.getFirstChild()!=null)
                nodeTextValue = child.getFirstChild().getNodeValue();
            switch (nodeName){
                case "coursenum":
                    courseSelecting.coursenum = nodeTextValue;
                    break;
                case "studentnum":
                    courseSelecting.studentnum = nodeTextValue;
                    break;
                case "Grd":
                    courseSelecting.record = nodeTextValue==null?null:Integer.parseInt(nodeTextValue);
                    break;
                default:
                    break;
            }
        }

        return courseSelecting;
    }
}

