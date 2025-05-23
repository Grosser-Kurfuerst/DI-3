<?xml version="1.0" encoding="utf-8" ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
                xmlns:jw="http://jw.nju.edu.cn/schema"
                xmlns:c="http://c.nju.edu.cn/schema">
    <xsl:output method="xml" version="1.0" encoding="utf-8"/>

    <!--	匹配到根元素时-->
    <xsl:template match="jw:classes">
        <classes xmlns="http://c.nju.edu.cn/schema">
            <!--    对于每一个课程-->
            <xsl:for-each select="jw:class">
                <class>
                    <Cno>
                        <xsl:value-of select="jw:id"/>
                    </Cno>
                    <Cnm>
                        <xsl:value-of select="jw:name"/>
                    </Cnm>
                    <Ctm>
                    </Ctm>
                    <Cpt>
                        <xsl:value-of select="jw:score"/>
                    </Cpt>
                    <Tec>
                        <xsl:value-of select="jw:teacher"/>
                    </Tec>
                    <Pla>
                        <xsl:value-of select="jw:location"/>
                    </Pla>
                </class>
            </xsl:for-each>
        </classes>
    </xsl:template>
</xsl:stylesheet>