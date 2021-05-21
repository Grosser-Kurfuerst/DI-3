
-----------------------------------------------------------------
-- 第一部分 ：drop table if exists
-----------------------------------------------------------------
---- 删除account
declare
      num number;
begin
    select count(1) into num from user_tables where table_name = upper('account') ;
    if num > 0 then
        execute immediate 'drop table account' ;
    end if;
end;
/
-- 删除student
declare
      num number;
begin
    select count(1) into num from user_tables where table_name = upper('student') ;
    if num > 0 then
        execute immediate 'drop table student' ;
    end if;
end;
/

---- 删除course
declare
      num number;
begin
    select count(1) into num from user_tables where table_name = upper('course') ;
    if num > 0 then
        execute immediate 'drop table course' ;
    end if;
end;
/
---- 删除election选课表
declare
      num number;
begin
    select count(1) into num from user_tables where table_name = upper('election') ;
    if num > 0 then
        execute immediate 'drop table election' ;
    end if;
end;
/

-----------------------------------------------------------------
-- 第二部分 ：建表
-----------------------------------------------------------------

create table student(
    sid varchar2(9) primary key,
    sname varchar2(10) not null,
    gender varchar2(2) default '男' not null ,
    department varchar2(16) not null,
    password varchar2(6) default null
);
-- 插入学生数据
insert into student values('181250207', '周润兴', '男', '软件工程', '122816');
-- 创建账户表
create table account(
    aname varchar2(12) primary key,
    password varchar2(12) not null ,
    power_grade number(2) default 1,
    guest_id varchar2(9),
    constraint guest_id foreign key (guest_id) references student(sid)
);

-- 插入账户数据
insert into account values('181250207', '123456', 3, '181250207');
insert into account values('110', '123456', 6, null);
insert into account values('911', '123456', 5, null);

-- 创建课程表
create table course(
    course_id varchar2(5) primary key,
    course_name varchar2(16),
    course_time varchar2(2),
    score varchar2(1),
    teacher_name varchar2(10),
    teaching_place varchar2(20),
    share_flag char(1),
    power_grade number(2) default 0
);

-- 插入课程数据
insert into course values('03586', '数据集成', 4, 2, '刘峰', '教学楼202', '0',3);
insert into course values('66666', '服务端开发', 4, 2, 'taozs', '姜的个人会议室', '1',3);

-- 创建学生 - 选课信息 的表
create table election(
    course_id varchar2(5) not null,
    student_id varchar2(9) not null,
    score varchar2(3) default null,
    constraint PK_election primary key (course_id, student_id)
) ;
insert into election values('03586', '181250207', '91');
insert into election values('66666', '181250207', '96');


commit;