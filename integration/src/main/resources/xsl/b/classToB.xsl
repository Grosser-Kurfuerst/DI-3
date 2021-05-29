<?xml version="1.0" encoding="utf-8" ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
                xmlns:jw="http://jw.nju.edu.cn/schema"
                xmlns:b="http://b.nju.edu.cn/schema">
    <xsl:output method="xml" version="1.0" encoding="utf-8"/>

    <!--	匹配到根元素时-->
    <xsl:template match="jw:classes">
        <classes xmlns="http://b.nju.edu.cn/schema">
            <!--    对于每一个课程-->
            <xsl:for-each select="jw:class">
                <class>
                    <编号>
                        <xsl:value-of select="jw:id"/>
                    </编号>
                    <名称>
                        <xsl:value-of select="jw:name"/>
                    </名称>
                    <课时>
                        <xsl:value-of select="jw:time"/>
                    </课时>
                    <学分>
                        <xsl:value-of select="jw:score"/>
                    </学分>
                    <老师>
                        <xsl:value-of select="jw:teacher"/>
                    </老师>
                    <地点>
                        <xsl:value-of select="jw:location"/>
                    </地点>
                </class>
            </xsl:for-each>
        </classes>
    </xsl:template>
</xsl:stylesheet>