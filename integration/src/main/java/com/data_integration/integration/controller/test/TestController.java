package com.data_integration.integration.controller.test;

import org.dom4j.io.DocumentResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.XMLConstants;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping("/test")
    public String test(@RequestBody String content) throws Exception {
        // content是院系服务器发来的xml字符串
        System.out.println(content);
        // 使用resources中的xsd文件验证
        URL schemaUrl = getClass().getResource("/schema/b/choiceB.xsd");
        File schemaFile = new File(URLDecoder.decode(schemaUrl.getFile(),"UTF-8"));
        validateSchema(schemaFile,content);
        // 使用resources中的xsl转换
        URL xslUrl = getClass().getResource("/xsl/format/formatClassChoice.xsl");
        return transform(URLDecoder.decode(xslUrl.getFile(),"UTF-8"),content);
    }

    /**
     * 验证Schema
     */
    public static void validateSchema(File schemaFile,String content) throws Exception{
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
        //③ 进行转换
        DocumentResult result = new DocumentResult();
        transformer.transform(new StreamSource(new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8))), result);
        org.dom4j.Document transformedDoc = result.getDocument();
        return transformedDoc.asXML();
    }
}
