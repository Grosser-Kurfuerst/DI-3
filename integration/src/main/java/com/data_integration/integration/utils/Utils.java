package com.data_integration.integration.utils;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.dom4j.io.DocumentResult;

import javax.xml.XMLConstants;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class Utils {
    static long tempFileNo = 0;
//    public static String serverA = "http://10.60.254.43:9361";
//    public static String serverB = "http://10.60.254.39:9362";
//    public static String serverC = "http:// 10.60.254.38:9363";
    public static String serverA = "http://192.168.1.11:9361";
    public static String serverB = "http://192.168.1.11:9362";
    public static String serverC = "http://192.168.1.11:9363";
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

    public static File resource2File(String resource) throws IOException {
        InputStream inputStream = Utils.class.getResourceAsStream(resource);
        return stream2file(inputStream);
    }

    public static File stream2file (InputStream in) throws IOException {
        File tempFile = File.createTempFile("tempFile" + tempFileNo++, ".xsd");
        tempFile.deleteOnExit();
        try (FileOutputStream out = new FileOutputStream(tempFile)) {
            IOUtils.copy(in, out);
        }
        return tempFile;
    }
}
