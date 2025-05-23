<?xml version="1.0" encoding="utf-8" ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
                xmlns:jw="http://jw.nju.edu.cn/schema"
                xmlns:c="http://c.nju.edu.cn/schema">
    <xsl:output method="xml" version="1.0" encoding="utf-8"/>

    <!--	匹配到根元素时-->
    <xsl:template match="jw:students">
        <students xmlns="http://c.nju.edu.cn/schema" xmlns:c="http://c.nju.edu.cn/schema">
            <!--    对于每一个课程-->
            <xsl:for-each select="jw:student">
                <student>
                    <Sno>
                        <xsl:value-of select="jw:id"/>
                    </Sno>
                    <Snm>
                        <xsl:value-of select="jw:name"/>
                    </Snm>
                    <Sex>
                        <xsl:value-of select="jw:sex"/>
                    </Sex>
                    <Sde>
                        <xsl:value-of select="jw:major"/>
                    </Sde>
<!--                    <permission>-->
<!--                        <xsl:value-of select="jw:permission"/>-->
<!--                    </permission>-->
                </student>
            </xsl:for-each>
        </students>
    </xsl:template>
</xsl:stylesheet>