package com.data_integration.c.utils;

import com.data_integration.c.PO.Course;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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
     * course转c课程xml
     * @param courseList
     * @return
     * @throws Exception
     */
    public static String coursesToXml(List<Course> courseList) throws Exception {
        Document document = getDocument(null);
        Element root = document.createElement("classes");
        root.setAttribute("xmlns", "http://c.nju.edu.cn/schema");
        document.appendChild(root);
        for (Course course:courseList){
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
        return toFormatedXML(document);
    }

    /**
     * C格式的课程xml转对象
     * @param content
     * @return
     * @throws Exception
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
}
