-----------------------------------------------------------------
-- 第一部分 ：drop table if exists
-----------------------------------------------------------------

-- 删除student
declare
      num number;
begin
    select count(1) into num from user_tables where table_name = upper('student') ;
    if num > 0 then
        execute immediate 'drop table student' ;
    end if;
end;
-- 删除account
declare
      num number;
begin
    select count(1) into num from user_tables where table_name = upper('account') ;
    if num > 0 then
        execute immediate 'drop table account' ;
    end if;
end;
-- 删除student_account_match
declare
      num number;
begin
    select count(1) into num from user_tables where table_name = upper('student_account_match') ;
    if num > 0 then
        execute immediate 'drop table student_account_match' ;
    end if;
end;
-- 删除course
declare
      num number;
begin
    select count(1) into num from user_tables where table_name = upper('course') ;
    if num > 0 then
        execute immediate 'drop table course' ;
    end if;
end;
-- 删除student_course_match
declare
      num number;
begin
    select count(1) into num from user_tables where table_name = upper('student_course_match') ;
    if num > 0 then
        execute immediate 'drop table student_course_match' ;
    end if;
end;


-----------------------------------------------------------------
-- 第二部分 ：建表
-----------------------------------------------------------------

create table student(
    _id number(20) primary key, -- 隐藏的id
    sid varchar2(20) not null, -- 学号
    sname varchar2(40) not null,
    gender char(1) not null default '男',
    department_id varchar2(20) not null,
    account_id number(20)
);
-- 创建student表的自增序列信息
create sequence student_id_sequence
increment by 1
start with 1
nomaxvalue
nocycle
nocache;
-- 建立自增触发器
create trigger student_trigger
before
    insert on student for each row when (new._id is null)
begin
    select student_id_sequence.nextval into:new._id from dual;
end;
-- 插入学生数据
insert into student values(null, '181250207', 'ZRX', '男', '软件学院');


-- 创建账户表
create table account(
    aid number(20) primary key,
    aname varchar2(20),
    password varchar2(32),
    power varchar2(20) default '无权限'
);

-- 创建account表的自增序列信息
create sequence account_aid_sequence
increment by 1
start with 1
nomaxvalue
nocycle
nocache;
-- 建立账户表自增触发器
create trigger account_trigger
before
    insert on account for each row when (new.aid is null)
begin
    select account_aid_sequence.nextval into:new.aid from dual;
end;
-- 插入账户数据
insert into account values(null, '账户181250207', '123456', '有权限');



-- 创建学生-账户对应表
create table student_account_match(
    sid number(20) not null,
    aid number(20) not null,
    unique(sid, aid)
);
insert into student_account_match values(1,1);


-- 创建课程表
create table course(
    course_id number(20) primary key,
    teacher_name varchar2(20),
    score number(2),
    course_time number(2,2),
    course_name varchar2(15),
    teaching_place varchar2(10)
);
-- 创建course表的自增序列信息
create sequence course_id_sequence
increment by 1
start with 1
nomaxvalue
nocycle
nocache;
-- 建立自增触发器
create trigger course_trigger
before
    insert on course for each row when (new.course_id is null)
begin
    select course_id_sequence.nextval into:new.course_id from dual;
end;
-- 插入课程数据
insert into course values(null, '刘峰', '2', '4', '数据集成', '教学楼202');
insert into course values(null, 'taozs', '2', '4', '服务端开发', '姜的个人会议室');


-- 创建学生 - 选课信息 的表
create table student_course_match(
    student_id number(20) not null,
    course_id number(20) not null
) ;
insert into student_course_match value(1,1), (1,2);
