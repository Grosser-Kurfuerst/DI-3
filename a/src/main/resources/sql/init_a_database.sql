
--  注意data_integration_a和创建的 数据库名一致，不然就全部替换成你自定义名字的数据库名字
USE [data_integration_a]

-- 删除外键约束 不然不能drop account
ALTER TABLE DBO.student DROP CONSTRAINT FK_student_account

-- 账户表 要在前面，student 引用了外键
IF OBJECT_ID('dbo.account', 'U') IS NOT NULL
DROP TABLE dbo.account
GO
create table account
(
    account    varchar(10) not null
        constraint PK_account
            primary key,
    password   varchar(6)  not null,
    permission varchar(4)
)
go

INSERT INTO data_integration_a.dbo.account (account, password, permission) VALUES (N'001a', N'123', N'4   ');
INSERT INTO data_integration_a.dbo.account (account, password, permission) VALUES (N'002a', N'1234', N'2   ');
INSERT INTO data_integration_a.dbo.account (account, password, permission) VALUES (N'003a', N'123', N'2   ');
-- 学生表
-- Create a new table called 'student' in schema 'dbo'
-- Drop the table if it already exists
IF OBJECT_ID('dbo.student', 'U') IS NOT NULL
DROP TABLE dbo.student
GO
create table student
(
    stunum     varchar(12) not null
        constraint PK_student
            primary key,
    stuname    varchar(10),
    sex        varchar(2),
    department varchar(10),
    pwd        varchar(6),
    account    varchar(10)
        constraint FK_student_account
            references account,
    permission varchar(4)
)
go

INSERT INTO data_integration_a.dbo.student (stunum, stuname, sex, department, pwd, account, permission) VALUES (N'0001a', N'wkxg', N'F', N'se', N'123', N'001a', N'4');
INSERT INTO data_integration_a.dbo.student (stunum, stuname, sex, department, pwd, account, permission) VALUES (N'0002a', N'lhy', N'M', N'rw', N'123', N'002a', N'3');

-- 选课表
IF OBJECT_ID('dbo.courseselection', 'U') IS NOT NULL
DROP TABLE dbo.courseselection
GO
create table courseselection
(
    coursenum  varchar(8)  not null,
    studentnum varchar(12) not null,
    record     int,
    constraint courseselection_pk
        primary key nonclustered (coursenum, studentnum)
)
go

INSERT INTO data_integration_a.dbo.courseselection (coursenum, studentnum, record) VALUES (N'0001a', N'0001a', 90);
INSERT INTO data_integration_a.dbo.courseselection (coursenum, studentnum, record) VALUES (N'0002a', N'0002a', 0);
INSERT INTO data_integration_a.dbo.courseselection (coursenum, studentnum, record) VALUES (N'0002a', N'0001a', 10);
-- 课程表
IF OBJECT_ID('dbo.course', 'U') IS NOT NULL
DROP TABLE dbo.course
GO
create table course
(
    coursenum  varchar(8) not null
        constraint PK_course
            primary key,
    coursename varchar(10),
    credit     varchar(2),
    teacher    varchar(10),
    place      varchar(20),
    share      nchar,
    permission varchar(4)
)
go

INSERT INTO data_integration_a.dbo.course (coursenum, coursename, credit, teacher, place, share, permission) VALUES (N'0001a', N'mdxtsj', N'3', N'zh', N'ctm', N'N', N'3');
INSERT INTO data_integration_a.dbo.course (coursenum, coursename, credit, teacher, place, share, permission) VALUES (N'0002a', N'mdljzh', N'3', N'zh', N'ctm', N'Y', N'3');

