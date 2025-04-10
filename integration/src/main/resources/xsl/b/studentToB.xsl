<?xml version="1.0" encoding="utf-8" ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
                xmlns:jw="http://jw.nju.edu.cn/schema"
                xmlns:b="http://b.nju.edu.cn/schema">
    <xsl:output method="xml" version="1.0" encoding="utf-8"/>

    <!--	匹配到根元素时-->
    <xsl:template match="jw:students">
        <students xmlns="http://b.nju.edu.cn/schema" xmlns:b="http://b.nju.edu.cn/schema">
            <!--    对于每一个课程-->
            <xsl:for-each select="jw:student">
                <student>
                    <学号>
                        <xsl:value-of select="jw:id"/>
                    </学号>
                    <名称>
                        <xsl:value-of select="jw:name"/>
                    </名称>
                    <性别>
                        <xsl:value-of select="jw:sex"/>
                    </性别>
                    <专业>
                        <xsl:value-of select="jw:major"/>
                    </专业>
<!--                    <权限>-->
<!--                        <xsl:value-of select="jw:permission"/>-->
<!--                    </权限>-->
                </student>
            </xsl:for-each>
        </students>
    </xsl:template>
</xsl:stylesheet>