<?xml version="1.0" encoding="utf-8" ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
                xmlns:jw="http://jw.nju.edu.cn/schema"
                xmlns:a="http://a.nju.edu.cn/schema"
                xmlns:b="http://b.nju.edu.cn/schema"
                xmlns:c="http://c.nju.edu.cn/schema">
    <xsl:output method="xml" version="1.0" encoding="utf-8"/>

    <!--    如果是a-->
    <xsl:template match="a:students">
        <students xmlns="http://jw.nju.edu.cn/schema" xmlns:jw="http://jw.nju.edu.cn/schema">
            <!--    对于每一个选课-->
            <xsl:for-each select="a:student">
                <student>
                    <id>
                        <xsl:value-of select="a:stunum"/>
                    </id>
                    <name>
                        <xsl:value-of select="a:stuname"/>
                    </name>
                    <sex>
                        <xsl:value-of select="a:sex"/>
                    </sex>
                    <major>
                        <xsl:value-of select="a:department"/>
                    </major>
                </student>
            </xsl:for-each>
        </students>
    </xsl:template>

    <!--    如果是b-->
    <xsl:template match="b:students">
        <students xmlns="http://jw.nju.edu.cn/schema" xmlns:jw="http://jw.nju.edu.cn/schema">
            <!--    对于每一个选课-->
            <xsl:for-each select="b:student">
                <student>
                    <id>
                        <xsl:value-of select="b:学号"/>
                    </id>
                    <name>
                        <xsl:value-of select="b:名称"/>
                    </name>
                    <sex>
                        <xsl:value-of select="b:性别"/>
                    </sex>
                    <major>
                        <xsl:value-of select="b:专业"/>
                    </major>
                </student>
            </xsl:for-each>
        </students>
    </xsl:template>


    <!--    如果是c-->
    <xsl:template match="c:students">
        <students xmlns="http://jw.nju.edu.cn/schema" xmlns:jw="http://jw.nju.edu.cn/schema">
            <!--    对于每一个选课-->
            <xsl:for-each select="c:student">
                <student>
                    <id>
                        <xsl:value-of select="c:Sno"/>
                    </id>
                    <name>
                        <xsl:value-of select="c:Snm"/>
                    </name>
                    <sex>
                        <xsl:value-of select="c:Sex"/>
                    </sex>
                    <major>
                        <xsl:value-of select="c:Sde"/>
                    </major>
                </student>
            </xsl:for-each>
        </students>
    </xsl:template>



</xsl:stylesheet>