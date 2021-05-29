SET FOREIGN_KEY_CHECKS = 0;

-- 管理人员账户
drop table if exists `account`;
create table `account`(
    `acc` varchar(255) not null ,
    `passwd` varchar(255) not null ,
    `create_date` timestamp default current_timestamp,
    `permission` integer default 1 not null ,
    primary key (`acc`)
);
insert into `account`(`acc`,`passwd`,`permission`) values ('c1','1',2);
insert into `account`(`acc`,`passwd`) values ('c2','1');

-- 学生账户
drop table if exists `student`;
create table `student`(
    `sno` varchar(255) not null,
    `snm` varchar(255) NOT NULL ,
    `sex` varchar(255) not null ,
    `sde` varchar(255) not null ,
    `pwd` varchar(255) not null ,
    `permission` integer default 1 not null ,
    primary key (`sno`)
);
insert into `student`(`sno`,`snm`,`sex`,`sde`,`pwd`,`permission`) values ('00000001c','Van','M','SE','123456',4);
insert into `student`(`sno`,`snm`,`sex`,`sde`,`pwd`) values ('00000002c','Dark','F','SE','123456');

/* Ctm是课时，Cpt是学分，Tec是教师，Pla是地点 */
drop table if exists `course`;
create table `course`(
    `cno` varchar(255) not null ,
    `cnm` varchar(255) not null ,
    `ctm` integer not null ,
    `cpt` integer not null ,
    `tec` varchar(255) not null ,
    `pla` varchar(255) not null ,
    `share` varchar(255) not null ,
    `permission` integer default 0 not null ,
    primary key (`Cno`)
);
insert into `course`(`cno`,`cnm`,`ctm`,`cpt`,`tec`,`pla`,`share`,`permission`) values ('001c','OS',4,3,'gjd','222','Y',4);
insert into `course`(`cno`,`cnm`,`ctm`,`cpt`,`tec`,`pla`,`share`) values ('002c','Math',3,2,'ss','110','N');
insert into `course`(`cno`,`cnm`,`ctm`,`cpt`,`tec`,`pla`,`share`,`permission`) values ('003c','Compiler',2,2,'whf','121','Y',2);

drop table if exists `course_selecting`;
create table `course_selecting`(
    `cno` varchar(255) not null ,
    `sno` varchar(255) not null ,
    `grd` integer default null,
    primary key (`cno`,`sno`),
    foreign key (`cno`) references `course`(`cno`),
    foreign key (`sno`) references `student`(`sno`)
);
insert into `course_selecting`(`cno`,`sno`,`grd`) values ('001c','00000001c',90);
insert into `course_selecting`(`cno`,`sno`) values ('002c','00000001c');
insert into `course_selecting`(`cno`,`sno`,`grd`) values ('001c','00000002c',80);

SET FOREIGN_KEY_CHECKS = 1;