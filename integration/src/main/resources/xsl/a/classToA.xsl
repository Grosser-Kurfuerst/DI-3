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
                    <coursenum>
                        <xsl:value-of select="jw:id"/>
                    </coursenum>
                    <coursename>
                        <xsl:value-of select="jw:name"/>
                    </coursename>
                    <credit>
                        <xsl:value-of select="jw:score"/>
                    </credit>
                    <teacher>
                        <xsl:value-of select="jw:teacher"/>
                    </teacher>
                    <place>
                        <xsl:value-of select="jw:location"/>
                    </place>
                </class>
            </xsl:for-each>
        </classes>
    </xsl:template>
</xsl:stylesheet>