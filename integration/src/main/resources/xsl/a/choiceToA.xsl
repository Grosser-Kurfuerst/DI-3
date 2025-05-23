<?xml version="1.0" encoding="utf-8" ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
                xmlns:jw="http://jw.nju.edu.cn/schema"
                xmlns:a="http://a.nju.edu.cn/schema">
    <xsl:output method="xml" version="1.0" encoding="utf-8"/>

    <!--	匹配到根元素时-->
    <xsl:template match="jw:choices">
        <choices xmlns="http://a.nju.edu.cn/schema">
            <!--    对于每一个选课-->
            <xsl:for-each select="jw:choice">
                <choice>
                    <studentnum>
                        <xsl:value-of select="jw:sid"/>
                    </studentnum>
                    <coursenum>
                        <xsl:value-of select="jw:cid"/>
                    </coursenum>
                    <record>
                        <xsl:value-of select="jw:score"/>
                    </record>
                </choice>
            </xsl:for-each>
        </choices>
    </xsl:template>
</xsl:stylesheet>