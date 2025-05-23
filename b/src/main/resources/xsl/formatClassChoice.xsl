<?xml version="1.0" encoding="utf-8" ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
                xmlns:jw="http://jw.nju.edu.cn/schema"
                xmlns:a="http://a.nju.edu.cn/schema"
                xmlns:b="http://b.nju.edu.cn/schema"
                xmlns:c="http://c.nju.edu.cn/schema">
    <xsl:output method="xml" version="1.0" encoding="utf-8"/>

    <!--    如果是a-->
    <xsl:template match="a:choices">
        <choices xmlns="http://jw.nju.edu.cn/schema" xmlns:jw="http://jw.nju.edu.cn/schema">
            <!--    对于每一个选课-->
            <xsl:for-each select="a:choice">
                <choice>
                    <sid>
                        <xsl:value-of select="a:studentnum"/>
                    </sid>
                    <cid>
                        <xsl:value-of select="a:coursenum"/>
                    </cid>
                    <score>
                        <xsl:value-of select="a:record"/>
                    </score>
                </choice>
            </xsl:for-each>
        </choices>
    </xsl:template>


    <!--	如果是b-->
    <xsl:template match="b:choices">
        <choices xmlns="http://jw.nju.edu.cn/schema" xmlns:jw="http://jw.nju.edu.cn/schema">
            <!--    对于每一个选课-->
            <xsl:for-each select="b:choice">
                <choice>
                    <sid>
                        <xsl:value-of select="b:学生编号"/>
                    </sid>
                    <cid>
                        <xsl:value-of select="b:课程编号"/>
                    </cid>
                    <score>
                        <xsl:value-of select="b:得分"/>
                    </score>
                </choice>
            </xsl:for-each>
        </choices>
    </xsl:template>


    <!--    如果是c-->
    <xsl:template match="c:choices">
        <choices xmlns="http://jw.nju.edu.cn/schema" xmlns:jw="http://jw.nju.edu.cn/schema">
            <!--    对于每一个选课-->
            <xsl:for-each select="c:choice">
                <choice>
                    <sid>
                        <xsl:value-of select="c:Sno"/>
                    </sid>
                    <cid>
                        <xsl:value-of select="c:Cno"/>
                    </cid>
                    <score>
                        <xsl:value-of select="c:Grd"/>
                    </score>
                </choice>
            </xsl:for-each>
        </choices>
    </xsl:template>
</xsl:stylesheet>