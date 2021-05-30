<?xml version="1.0" encoding="utf-8" ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
                xmlns:jw="http://jw.nju.edu.cn/schema"
                xmlns:a="http://a.nju.edu.cn/schema">
    <xsl:output method="xml" version="1.0" encoding="utf-8"/>

    <!--	匹配到根元素时-->
    <xsl:template match="jw:classes">
        <classes xmlns="http://a.nju.edu.cn/schema">
            <!--    对于每一个课程-->
            <xsl:for-each select="jw:class">
                <class>
                    <课程编号>
                        <xsl:value-of select="jw:id"/>
                    </课程编号>
                    <课程名称>
                        <xsl:value-of select="jw:name"/>
                    </课程名称>
                    <学分>
                        <xsl:value-of select="jw:score"/>
                    </学分>
                    <授课老师>
                        <xsl:value-of select="jw:teacher"/>
                    </授课老师>
                    <授课地点>
                        <xsl:value-of select="jw:location"/>
                    </授课地点>
                </class>
            </xsl:for-each>
        </classes>
    </xsl:template>
</xsl:stylesheet>