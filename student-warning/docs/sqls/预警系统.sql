/*
SQLyog Ultimate v12.5.0 (64 bit)
MySQL - 5.7.36 : Database - education
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`warning_system` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `warning_system`;

/*Table structure for table `class` */

DROP TABLE IF EXISTS `class`;

CREATE TABLE `class` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `college` varchar(32) DEFAULT NULL COMMENT '学院',
  `grade` varchar(32) DEFAULT NULL COMMENT '年级',
  `subject` varchar(32) DEFAULT NULL COMMENT '专业',
  `class_no` varchar(32) DEFAULT NULL COMMENT '班级',
  `subject_dir` varchar(32) DEFAULT NULL COMMENT '专业方向',
  `counselor_id` int(11) DEFAULT NULL COMMENT '教师id 对应teacher表',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='班级信息';

/*Data for the table `""class` */

insert  into `class`(`ID`,`college`,`grade`,`subject`,`class_no`,`subject_dir`,`counselor_id`) values
(1,'计算机工程学院','2021','软件工程','1',NULL,1),
(2,'计算机工程学院','2021','软件工程','2',NULL,1),
(4,'计算机工程学院','2021','软件工程','3','',3),
(5,'计算机工程学院','2021','信息管理','1','',NULL),
(6,'计算机工程学院','2021','信息管理','2','',NULL);

/*Table structure for table `classroom` */

DROP TABLE IF EXISTS `classroom`;

CREATE TABLE `classroom` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(32) DEFAULT NULL COMMENT '教室名',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='教室信息';

/*Data for the table `classroom` */

insert  into `classroom`(`ID`,`name`) values
(1,'A01');

/*Table structure for table `course` */

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `college` varchar(32) DEFAULT NULL COMMENT '开课学院',
  `code` varchar(32) DEFAULT NULL COMMENT '课程编码',
  `name` varchar(32) DEFAULT NULL COMMENT '中文名称',
  `name_eng` varchar(32) DEFAULT NULL COMMENT '英文名称',
  `type` varchar(32) DEFAULT NULL COMMENT '课程类别 0选修 1必修',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='课表信息';

/*Data for the table `course` */

insert  into `course`(`ID`,`college`,`code`,`name`,`name_eng`,`type`) values
(1,'','c1','C++','C++','1'),
(2,'','c2','Java编程','Java','1'),
(3,'','c3','安卓','Android','0');

/*Table structure for table `open_course` */

DROP TABLE IF EXISTS `open_course`;

CREATE TABLE `open_course` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `term` varchar(32) DEFAULT NULL COMMENT '学期',
  `college` varchar(32) DEFAULT NULL COMMENT '开课学院',
  `class_id` int(11) DEFAULT NULL COMMENT '班级id',
  `course_id` int(11) DEFAULT NULL COMMENT '课程id',
  `score` decimal(32,10) DEFAULT NULL COMMENT '学分',
  `hour` int(11) DEFAULT NULL COMMENT '学时',
  `number` int(11) DEFAULT NULL COMMENT '人数',
  `week_start` int(11) DEFAULT NULL COMMENT '开始周',
  `week_end` int(11) DEFAULT NULL COMMENT '结束周',
  `week_time` varchar(32) DEFAULT NULL COMMENT '周次 一周多节的按逗号隔开，如 1,3表示周一和周三',
  `node_start` int(11) DEFAULT NULL COMMENT '开始节',
  `node_end` int(11) DEFAULT NULL COMMENT '结束节',
  `teacher_id` int(11) DEFAULT NULL COMMENT '教师id',
  `classroom_id` int(11) DEFAULT NULL COMMENT '教室id',
  `choose_start` datetime DEFAULT NULL COMMENT '选课开始时间',
  `choose_end` datetime DEFAULT NULL COMMENT '选课结束时间',
  `choose_num` int(11) DEFAULT NULL COMMENT '当前选课人数',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='开课信息';

/*Data for the table `open_course` */

insert  into `open_course`(`ID`,`term`,`college`,`class_id`,`course_id`,`score`,`hour`,`number`,`week_start`,`week_end`,`week_time`,`node_start`,`node_end`,`teacher_id`,`classroom_id`,`choose_start`,`choose_end`,`choose_num`) values
(1,'202101',NULL,1,2,2.0000000000,50,50,1,19,'1',1,2,1,1,NULL,NULL,NULL);

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `stu_code` varchar(32) DEFAULT NULL COMMENT '学号',
  `stu_name` varchar(32) DEFAULT NULL COMMENT '姓名',
  `stu_name_old` varchar(32) DEFAULT NULL COMMENT '曾用名',
  `stu_name_py` varchar(32) DEFAULT NULL COMMENT '姓名拼音',
  `sex` int(11) DEFAULT NULL COMMENT '性别',
  `certificat_type` varchar(32) DEFAULT NULL COMMENT '证件类型',
  `certificate_no` varchar(32) DEFAULT NULL COMMENT '证件号码',
  `nationality` varchar(32) DEFAULT NULL COMMENT '国籍',
  `hometown` varchar(32) DEFAULT NULL COMMENT '籍贯',
  `nation` varchar(32) DEFAULT NULL COMMENT '民族',
  `marriage` int(11) DEFAULT NULL COMMENT '婚姻',
  `birthplace` varchar(32) DEFAULT NULL COMMENT '生源地',
  `birthday` date DEFAULT NULL COMMENT '出生日期',
  `political_status` varchar(32) DEFAULT NULL COMMENT '政治面貌',
  `phone` varchar(32) DEFAULT NULL COMMENT '手机号',
  `email` varchar(32) DEFAULT NULL COMMENT 'Email',
  `qq` varchar(32) DEFAULT NULL COMMENT 'QQ',
  `weChat` varchar(32) DEFAULT NULL COMMENT '微信',
  `home_phone` varchar(32) DEFAULT NULL COMMENT '家庭电话',
  `home_address` varchar(32) DEFAULT NULL COMMENT '家庭地址',
  `postCode` varchar(32) DEFAULT NULL COMMENT '邮政编码',
  `skill` varchar(32) DEFAULT NULL COMMENT '特长',
  `sickness_history` varchar(32) DEFAULT NULL COMMENT '既往病史',
  `class_id` int(11) DEFAULT NULL COMMENT '班级id',
  `candidate_no` varchar(32) DEFAULT NULL COMMENT '考生号',
  `admission_time` date DEFAULT NULL COMMENT '入学时间',
  `admission_methord` varchar(32) DEFAULT NULL COMMENT '入学方式',
  `stu_status` varchar(32) DEFAULT NULL COMMENT '学籍状态',
  `training_level` varchar(32) DEFAULT NULL COMMENT '培养层次',
  `educational_system` varchar(32) DEFAULT NULL COMMENT '学制',
  `rewards` varchar(32) DEFAULT NULL COMMENT '奖惩情况',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='学生信息';

/*Data for the table `student` */

insert  into `student`(`ID`,`stu_code`,`stu_name`,`stu_name_old`,`stu_name_py`,`sex`,`certificat_type`,`certificate_no`,`nationality`,`hometown`,`nation`,`marriage`,`birthplace`,`birthday`,`political_status`,`phone`,`email`,`qq`,`weChat`,`home_phone`,`home_address`,`postCode`,`skill`,`sickness_history`,`class_id`,`candidate_no`,`admission_time`,`admission_methord`,`stu_status`,`training_level`,`educational_system`,`rewards`) values
(1,'202101','张三',NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,'在学',NULL,NULL,NULL),
(2,'202102','李四',NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,4,NULL,NULL,NULL,'在学',NULL,NULL,NULL),
(3,'202103','李丽',NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,NULL,'在学',NULL,NULL,NULL);

/*Table structure for table `student_course` */

DROP TABLE IF EXISTS `student_course`;

CREATE TABLE `student_course` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `student_id` int(11) DEFAULT NULL COMMENT '学生id',
  `open_course_id` int(11) DEFAULT NULL COMMENT '开课课程id',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='选课信息';

/*Data for the table `student_course` */

/*Table structure for table `teacher` */

DROP TABLE IF EXISTS `teacher`;

CREATE TABLE `teacher` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `code` varchar(20) DEFAULT NULL COMMENT '教师工号',
  `name` varchar(32) DEFAULT NULL COMMENT '姓名',
  `relate_class_ids` varchar(255) DEFAULT NULL COMMENT '关联班级id，逗号分隔',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`ID`),
  KEY `userId` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='教师信息';

/*Data for the table `teacher` */

insert  into `teacher`(`ID`,`code`,`name`,`relate_class_ids`,`user_id`) values
(1,'t01','教师1','1,2',2),
(3,'t02','王玲','4',3);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(64) NOT NULL COMMENT '用户名',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `role` varchar(32) DEFAULT NULL COMMENT '角色 1教师 2管理员',
  PRIMARY KEY (`ID`),
  KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='用户信息';

/*Data for the table `sys_user` */

insert  into `sys_user`(`ID`,`username`,`password`,`role`) values 
(1,'admin','123456','2'),
(2,'t01','123','1'),
(3,'t02','123','1');

CREATE TABLE `student_evaluate` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `student_id` INT(11)  DEFAULT NULL COMMENT '学生ID',
  `teacher_id` INT(11)  DEFAULT NULL COMMENT '教师ID',
  `open_course_id` INT(11)  DEFAULT NULL COMMENT '课程ID',
  `evalution` VARCHAR(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '评价信息',
  `student_name` varchar(52) DEFAULT NULL COMMENT '学生姓名',
  `teacher_name` varchar(52) DEFAULT NULL COMMENT '教师姓名',
  `course_name` varchar(52) DEFAULT NULL COMMENT '课程名称',
  PRIMARY KEY (`ID`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4  COMMENT='学生评价表';

CREATE TABLE `score_evaluate` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `student_id` INT(11)  DEFAULT NULL COMMENT '学生ID',
  `teacher_id` INT(11)  DEFAULT NULL COMMENT '教师ID',
  `open_course_id` INT(11)  DEFAULT NULL COMMENT '课程ID',
  `score` DECIMAL(5,2) DEFAULT NULL COMMENT '分数',
  `student_name` varchar(52) DEFAULT NULL COMMENT '学生姓名',
  `teacher_name` varchar(52) DEFAULT NULL COMMENT '教师姓名',
  `course_name` varchar(52) DEFAULT NULL COMMENT '课程名称',
  PRIMARY KEY (`ID`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4  COMMENT='学生成绩表';



CREATE TABLE `task_evaluate` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `student_id` INT(11)  DEFAULT NULL COMMENT '学生ID',
  `teacher_id` INT(11)  DEFAULT NULL COMMENT '教师ID',
  `count` INT  DEFAULT NULL COMMENT '缺少作业次数',
  `student_name` varchar(52) DEFAULT NULL COMMENT '学生姓名',
  `teacher_name` varchar(52) DEFAULT NULL COMMENT '教师姓名',
  `course_name` varchar(52) DEFAULT NULL COMMENT '课程名称',
  PRIMARY KEY (`ID`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4  COMMENT='学生作业表';


CREATE TABLE `participation_evaluate` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `student_id` INT(11)  DEFAULT NULL COMMENT '学生ID',
  `teacher_id` INT(11)  DEFAULT NULL COMMENT '教师ID',
  `open_course_id` INT(11)  DEFAULT NULL COMMENT '课程ID',
  `count` INT  DEFAULT NULL COMMENT '缺勤次数',
  `student_name` varchar(52) DEFAULT NULL COMMENT '学生姓名',
  `teacher_name` varchar(52) DEFAULT NULL COMMENT '教师姓名',
  `course_name` varchar(52) DEFAULT NULL COMMENT '课程名称',
  PRIMARY KEY (`ID`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4  COMMENT='学生缺勤次数表';


/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
