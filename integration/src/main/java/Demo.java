import javax.xml.XMLConstants;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileOutputStream;

public class Demo {

    public static void main(String[] args) throws Exception{
        // 用 Schema 验证 B 的三个 xml
        validateSchema("material/schema/b/studentB.xsd", "material/xml/b/studentB.xml");
        validateSchema("material/schema/b/classB.xsd", "material/xml/b/classB.xml");
        validateSchema("material/schema/b/choiceB.xsd", "material/xml/b/choiceB.xml");

        // 将三个B的xml文件转化成标准格式的，分别为 1 2 3 .xml
        transform("material/xsl/format/formatStudent.xsl", "material/xml/b/studentB.xml", "1.xml");
        transform("material/xsl/format/formatClass.xsl", "material/xml/b/classB.xml", "2.xml");
        transform("material/xsl/format/formatClassChoice.xsl", "material/xml/b/choiceB.xml", "3.xml");

        // 验证生成的 1 2 3 xml 文件是不是正确
        validateSchema("material/schema/format/formatStudent.xsd", "1.xml");
        validateSchema("material/schema/format/formatClass.xsd", "2.xml");
        validateSchema("material/schema/format/formatClassChoice.xsd", "3.xml");

        // 没有错误，说明从 B xml 转化到 format xml 方向没有问题

        // 验证 标准格式的三个 Schema
        validateSchema("material/schema/format/formatStudent.xsd", "material/xml/format/formatStudent.xml");
        validateSchema("material/schema/format/formatClass.xsd", "material/xml/format/formatClass.xml");
        validateSchema("material/schema/format/formatClassChoice.xsd", "material/xml/format/formatClassChoice.xml");

        // 将三个标准格式的xml文件转化成 B 格式的，分别为 4 5 6 .xml
        transform("material/xsl/b/studentToB.xsl", "material/xml/format/formatStudent.xml", "4.xml");
        transform("material/xsl/b/classToB.xsl", "material/xml/format/formatClass.xml", "5.xml");
        transform("material/xsl/b/choiceToB.xsl", "material/xml/format/formatClassChoice.xml", "6.xml");

        // 验证生成的 4 5 6 xml 文件是不是正确
        validateSchema("material/schema/b/studentB.xsd", "4.xml");
        validateSchema("material/schema/b/classB.xsd", "5.xml");
        validateSchema("material/schema/b/choiceB.xsd", "6.xml");
    }

    /**
     * 利用 xsl 将 xmlPath 对应的 xml 文件 转换成 targetPath 对应的xml 文件
     * @param xslPath xsl文件的路径
     * @param xmlPath 源 xml 文件的路径
     * @param targetPath 目标 xml 文件的生成路径
     */
    public static void transform(String xslPath, String xmlPath, String targetPath) throws Exception {

        // ① 获取转换器工厂
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        // ② 获取转换器对象实例
        Transformer transformer = transformerFactory.newTransformer(new StreamSource(xslPath));
        //③ 进行转换
        transformer.transform(new StreamSource(xmlPath), new StreamResult(new FileOutputStream( targetPath)));

    }

    /**
     * 验证Schema
     */
    public static void validateSchema(String schemaPath,String xmlPath) throws Exception{
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        Schema schema = factory.newSchema(new File(schemaPath));
        Validator validator = schema.newValidator();
        validator.validate(new StreamSource(xmlPath));
    }
}
