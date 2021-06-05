package com.data_integration.integration.controller.test;

import com.data_integration.integration.utils.Utils;
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
        Utils.validateSchema(schemaFile,content);
        // 使用resources中的xsl转换
        URL xslUrl = getClass().getResource("/xsl/format/formatClassChoice.xsl");
        return Utils.transform(URLDecoder.decode(xslUrl.getFile(),"UTF-8"),content);
    }
}
