
-----------------------------------------------------------------
-- ��һ���� ��drop table if exists
-----------------------------------------------------------------
---- ɾ��account
declare
      num number;
begin
    select count(1) into num from user_tables where table_name = upper('account') ;
    if num > 0 then
        execute immediate 'drop table account' ;
    end if;
end;
/
-- ɾ��student
declare
      num number;
begin
    select count(1) into num from user_tables where table_name = upper('student') ;
    if num > 0 then
        execute immediate 'drop table student' ;
    end if;
end;
/

---- ɾ��course
declare
      num number;
begin
    select count(1) into num from user_tables where table_name = upper('course') ;
    if num > 0 then
        execute immediate 'drop table course' ;
    end if;
end;
/
---- ɾ��electionѡ�α�
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
-- �ڶ����� ������
-----------------------------------------------------------------

create table student(
    sid varchar2(9) primary key,
    sname varchar2(10) not null,
    gender varchar2(2) default '��' not null ,
    department varchar2(16) not null,
    password varchar2(6) default null
);
-- ����ѧ������
insert into student values('181250207', '������', '��', '�������', '122816');
-- �����˻���
create table account(
    aname varchar2(12) primary key,
    password varchar2(12) not null ,
    power_grade number(2) default 1,
    guest_id varchar2(9),
    constraint guest_id foreign key (guest_id) references student(sid)
);

-- �����˻�����
insert into account values('181250207', '123456', 3, '181250207');
insert into account values('110', '123456', 6, null);
insert into account values('911', '123456', 5, null);

-- �����γ̱�
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

-- ����γ�����
insert into course values('03586', '���ݼ���', 4, 2, '����', '��ѧ¥202', '0',3);
insert into course values('66666', '����˿���', 4, 2, 'taozs', '���ĸ��˻�����', '1',3);

-- ����ѧ�� - ѡ����Ϣ �ı�
create table election(
    course_id varchar2(5) not null,
    student_id varchar2(9) not null,
    score varchar2(3) default null,
    constraint PK_election primary key (course_id, student_id)
) ;
insert into election values('03586', '181250207', '91');
insert into election values('66666', '181250207', '96');


commit;