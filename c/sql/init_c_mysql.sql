SET FOREIGN_KEY_CHECKS = 0;

-- 管理人员账户
drop table if exists `account`;
create table `account`(
    `acc` varchar(12) not null ,
    `passwd` varchar(12) not null ,
    `create_date` timestamp default current_timestamp,
    `permission` integer default 1 not null ,
    primary key (`acc`)
);
insert into `account`(`acc`,`passwd`,`permission`) values ('a','1',2);
insert into `account`(`acc`,`passwd`) values ('b','1');

-- 学生账户
drop table if exists `student`;
create table `student`(
    `sno` char(9) not null,
    `snm` varchar(10) NOT NULL ,
    `sex` varchar(1) not null ,
    `sde` varchar(6) not null ,
    `pwd` char(6) not null ,
    `permission` integer default 1 not null ,
    primary key (`sno`)
);
insert into `student`(`sno`,`snm`,`sex`,`sde`,`pwd`,`permission`) values ('123456789','Van','M','SE','123456',4);
insert into `student`(`sno`,`snm`,`sex`,`sde`,`pwd`) values ('123456788','Dark','F','SE','123456');

/* Ctm是课时，Cpt是学分，Tec是教师，Pla是地点 */
drop table if exists `course`;
create table `course`(
    `cno` char(4) not null ,
    `cnm` varchar(10) not null ,
    `ctm` integer not null ,
    `cpt` integer not null ,
    `tec` varchar(20) not null ,
    `pla` varchar(18) not null ,
    `share` char(1) not null ,
    `permission` integer default 0 not null ,
    primary key (`Cno`)
);
insert into `course`(`cno`,`cnm`,`ctm`,`cpt`,`tec`,`pla`,`share`,`permission`) values ('1234','OS',4,3,'gjd','222','Y',4);
insert into `course`(`cno`,`cnm`,`ctm`,`cpt`,`tec`,`pla`,`share`) values ('1233','Math',3,2,'ss','110','N');

drop table if exists `course_selecting`;
create table `course_selecting`(
    `cno` char(4) not null ,
    `sno` char(9) not null ,
    `grd` integer default null,
    primary key (`cno`,`sno`),
    foreign key (`cno`) references `course`(`cno`),
    foreign key (`sno`) references `student`(`sno`)
);
insert into `course_selecting`(`cno`,`sno`,`grd`) values ('1234','123456789',90);
insert into `course_selecting`(`cno`,`sno`) values ('1233','123456789');
insert into `course_selecting`(`cno`,`sno`,`grd`) values ('1233','123456788',80);

SET FOREIGN_KEY_CHECKS = 1;