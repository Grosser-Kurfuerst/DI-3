<?xml version="1.0" encoding="utf-8" ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
                xmlns:jw="http://jw.nju.edu.cn/schema"
                xmlns:b="http://b.nju.edu.cn/schema">
    <xsl:output method="xml" version="1.0" encoding="utf-8"/>

    <!--	匹配到根元素时-->
    <xsl:template match="jw:choices">
        <choices xmlns="http://b.nju.edu.cn/schema" xmlns:b="http://b.nju.edu.cn/schema">
            <!--    对于每一个选课-->
            <xsl:for-each select="jw:choice">
                 <choice>
                    <学生编号>
                        <xsl:value-of select="jw:sid"/>
                    </学生编号>
                     <课程编号>
                         <xsl:value-of select="substring(jw:cid, 5)"/>
                     </课程编号>
                     <得分>
                         <xsl:value-of select="jw:score"/>
                     </得分>
                 </choice>
             </xsl:for-each>
        </choices>
    </xsl:template>
</xsl:stylesheet>