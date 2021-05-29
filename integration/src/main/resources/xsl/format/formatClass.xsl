<?xml version="1.0" encoding="utf-8" ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
                xmlns:a="http://a.nju.edu.cn/schema"
                xmlns:b="http://b.nju.edu.cn/schema"
                xmlns:c="http://c.nju.edu.cn/schema">
    <xsl:output method="xml" version="1.0" encoding="utf-8"/>

    <!--	如果是a-->
    <xsl:template match="a:classes">
        <classes xmlns="http://jw.nju.edu.cn/schema">
            <!--    对于每一个选课-->
            <xsl:for-each select="a:class">
                <class>
                    <id>
                        <xsl:value-of select="a:课程编号"/>
                    </id>
                    <name>
                        <xsl:value-of select="a:课程名称"/>
                    </name>
                    <time>
                        <xsl:value-of select="a:课时"/>
                    </time>
                    <score>
                        <xsl:value-of select="a:学分"/>
                    </score>
                    <teacher>
                        <xsl:value-of select="a:授课老师"/>
                    </teacher>
                    <location>
                        <xsl:value-of select="a:授课地点"/>
                    </location>
                </class>
            </xsl:for-each>
        </classes>
    </xsl:template>

    <!--	如果是b-->
    <xsl:template match="b:classes">
        <classes xmlns="http://jw.nju.edu.cn/schema">
            <!--    对于每一个选课-->
            <xsl:for-each select="b:class">
                <class>
                    <id>
                        <xsl:value-of select="b:编号"/>
                    </id>
                    <name>
                        <xsl:value-of select="b:名称"/>
                    </name>
                    <time>
                        <xsl:value-of select="b:课时"/>
                    </time>
                    <score>
                        <xsl:value-of select="b:学分"/>
                    </score>
                    <teacher>
                        <xsl:value-of select="b:老师"/>
                    </teacher>
                    <location>
                        <xsl:value-of select="b:地点"/>
                    </location>
                </class>
            </xsl:for-each>
        </classes>
    </xsl:template>

    <!--	如果是c-->
    <xsl:template match="c:classes">
        <classes xmlns="http://jw.nju.edu.cn/schema" xmlns:jw="http://jw.nju.edu.cn/schema">
            <!--    对于每一个选课-->
            <xsl:for-each select="c:class">
                <class>
                    <id>
                        <xsl:value-of select="c:Cno"/>
                    </id>
                    <name>
                        <xsl:value-of select="c:Cnm"/>
                    </name>
                    <time>
                        <xsl:value-of select="c:Ctm"/>
                    </time>
                    <score>
                        <xsl:value-of select="c:Cpt"/>
                    </score>
                    <teacher>
                        <xsl:value-of select="c:Tec"/>
                    </teacher>
                    <location>
                        <xsl:value-of select="c:Pla"/>
                    </location>
                </class>
            </xsl:for-each>
        </classes>
    </xsl:template>
</xsl:stylesheet>