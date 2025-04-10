package com.data_integration.c.utils;

import com.data_integration.c.PO.Course;
import com.data_integration.c.PO.CourseSelecting;
import com.data_integration.c.PO.Student;
import com.data_integration.c.VO.CourseSelectingVO;
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
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Utils {
//    public static String serverIntegrator = "http://10.60.254.41:9365";
    public static String serverIntegrator = "http://192.168.1.11:9365";
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
     * 验证Schema
     */
    public static void validateSchema(File schemaFile, String content) throws Exception {
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        Schema schema = factory.newSchema(schemaFile);
        Validator validator = schema.newValidator();
        validator.validate(new StreamSource(new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8))));
    }

    /**
     * 利用 xsl 将 xmlPath 对应的 xml 文件 转换成 targetPath 对应的xml 文件
     */
    public static String transform(String xslPath, String content) throws Exception {

        // ① 获取转换器工厂
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        // ② 获取转换器对象实例
        Transformer transformer = transformerFactory.newTransformer(new StreamSource(xslPath));
//        InputStream inputStream = Utils.class.getResourceAsStream(xslPath);
//        Transformer transformer = transformerFactory.newTransformer(new StreamSource(inputStream));
        //③ 进行转换
        DocumentResult result = new DocumentResult();
        transformer.transform(new StreamSource(new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8))), result);
        org.dom4j.Document transformedDoc = result.getDocument();
        return transformedDoc.asXML();
    }

    /**
     * course转c课程xml
     *
     * @param courseList
     * @return
     * @throws Exception
     */
    public static String coursesToXml(List<Course> courseList) throws Exception {
        Document document = getDocument(null);
        Element root = document.createElement("classes");
        root.setAttribute("xmlns", "http://c.nju.edu.cn/schema");
        document.appendChild(root);
        for (Course course : courseList) {
            Element classElement = document.createElement("class");
            root.appendChild(classElement);

            Element cnoElement = document.createElement("Cno");
            cnoElement.setTextContent(course.cno);
            classElement.appendChild(cnoElement);

            Element cnmElement = document.createElement("Cnm");
            cnmElement.setTextContent(course.cnm);
            classElement.appendChild(cnmElement);

            Element ctmElement = document.createElement("Ctm");
            ctmElement.setTextContent("" + course.ctm);
            classElement.appendChild(ctmElement);

            Element cptElement = document.createElement("Cpt");
            cptElement.setTextContent("" + course.cpt);
            classElement.appendChild(cptElement);

            Element tecElement = document.createElement("Tec");
            tecElement.setTextContent(course.tec);
            classElement.appendChild(tecElement);

            Element plaElement = document.createElement("Pla");
            plaElement.setTextContent(course.pla);
            classElement.appendChild(plaElement);
        }
        return toFormatedXML(document);
    }

    /**
     * C格式的课程xml转对象
     *
     * @param content
     * @return
     * @throws Exception
     */
    public static List<Course> xmlToCourses(String content) throws Exception {
        List<Course> courseList = new ArrayList<>();
        Document document = getDocument(content);
        Node classesNode = document.getFirstChild();
        Node classNode = classesNode.getFirstChild();
        // 遍历classes
        while (classNode != null) {
            Course course = new Course();
            courseList.add(course);
            // 遍历class下元素 坑在于元素名用getNodeName() 值要getFirstChild().getNodeValue()
            for (Node child = classNode.getFirstChild(); child != null; child = child.getNextSibling()) {
                String nodeName = child.getNodeName();
                // 因为转换到集成格式后会丢失课时信息，所以课时的getFirstChild()为null
                String nodeTextValue = null;
                if (child.getFirstChild() != null)
                    nodeTextValue = child.getFirstChild().getNodeValue();
                switch (nodeName) {
                    case "Cno":
                        course.cno = nodeTextValue;
                        break;
                    case "Cnm":
                        course.cnm = nodeTextValue;
                        break;
                    case "Cpt":
                        course.cpt = Integer.parseInt(nodeTextValue);
                        break;
                    case "Tec":
                        course.tec = nodeTextValue;
                        break;
                    case "Pla":
                        course.pla = nodeTextValue;
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
     * C格式的学生转xml
     */
    public static String studentToXml(Student student) throws Exception {
        Document document = getDocument(null);
        Element root = document.createElement("students");
        root.setAttribute("xmlns", "http://c.nju.edu.cn/schema");
        document.appendChild(root);
        Element studentElement = document.createElement("student");
        root.appendChild(studentElement);

        Element snoElement = document.createElement("Sno");
        snoElement.setTextContent(student.sno);
        studentElement.appendChild(snoElement);

        Element snmElement = document.createElement("Snm");
        snmElement.setTextContent(student.snm);
        studentElement.appendChild(snmElement);

        Element sexElement = document.createElement("Sex");
        sexElement.setTextContent(student.sex);
        studentElement.appendChild(sexElement);

        Element sdeElement = document.createElement("Sde");
        sdeElement.setTextContent(student.sde);
        studentElement.appendChild(sdeElement);

//        Element permissionElement = document.createElement("permission");
//        permissionElement.setTextContent("" + student.permission);
//        studentElement.appendChild(permissionElement);

        return toFormatedXML(document);
    }

    /**
     * C格式的学生xml转对象
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
                case "Sno":
                    student.sno = nodeTextValue;
                    break;
                case "Snm":
                    student.snm = nodeTextValue;
                    break;
                case "Sex":
                    student.sex = nodeTextValue;
                    break;
                case "Sde":
                    student.sde = nodeTextValue;
                    break;
//                case "permission":
//                    student.permission = Integer.parseInt(nodeTextValue);
//                    break;
                default:
                    break;
            }
        }
        return student;
    }

    /**
     * C格式的选课转xml
     */
    public static String selectingToXml(CourseSelectingVO courseSelectingVO) throws Exception {
        Document document = getDocument(null);
        Element root = document.createElement("choices");
        root.setAttribute("xmlns", "http://c.nju.edu.cn/schema");
        document.appendChild(root);
        Element choiceElement = document.createElement("choice");
        root.appendChild(choiceElement);

        Element snoElement = document.createElement("Sno");
        snoElement.setTextContent(courseSelectingVO.sno);
        choiceElement.appendChild(snoElement);

        Element cnoElement = document.createElement("Cno");
        cnoElement.setTextContent(courseSelectingVO.cno);
        choiceElement.appendChild(cnoElement);

        Element grdElement = document.createElement("Grd");
        grdElement.setTextContent(courseSelectingVO.grd == null ? null : "" + courseSelectingVO.grd);
        choiceElement.appendChild(grdElement);

        return toFormatedXML(document);
    }

    /**
     * B格式的选课xml转对象
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
                    case "Sno":
                        courseSelecting.sno = nodeTextValue;
                        break;
                    case "Cno":
                        courseSelecting.cno = nodeTextValue;
                        break;
                    case "Grd":
                        courseSelecting.grd = nodeTextValue==null?null:Integer.parseInt(nodeTextValue);
                        break;
                    default:
                        break;
                }
            }

        return courseSelecting;
    }
}
