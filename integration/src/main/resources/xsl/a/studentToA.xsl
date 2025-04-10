<?xml version="1.0" encoding="utf-8" ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
                xmlns:jw="http://jw.nju.edu.cn/schema"
                xmlns:a="http://a.nju.edu.cn/schema">
    <xsl:output method="xml" version="1.0" encoding="utf-8"/>

    <!--	匹配到根元素时-->
    <xsl:template match="jw:students">
        <students xmlns="http://a.nju.edu.cn/schema" xmlns:a="http://a.nju.edu.cn/schema">
            <!--    对于每一个课程-->
            <xsl:for-each select="jw:student">
                <student>
                    <stunum>
                        <xsl:value-of select="jw:id"/>
                    </stunum>
                    <stuname>
                        <xsl:value-of select="jw:name"/>
                    </stuname>
                    <sex>
                        <xsl:value-of select="jw:sex"/>
                    </sex>
                    <department>
                        <xsl:value-of select="jw:major"/>
                    </department>
<!--                    <permission>-->
<!--                        <xsl:value-of select="jw:permission"/>-->
<!--                    </permission>-->
                </student>
            </xsl:for-each>
        </students>
    </xsl:template>
</xsl:stylesheet>