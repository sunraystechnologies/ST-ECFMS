/*
SQLyog Ultimate v9.02 
MySQL - 5.0.24-community-nt : Database - ocfms
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ocfms` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `ocfms`;

/*Table structure for table `st_complain` */

DROP TABLE IF EXISTS `st_complain`;

CREATE TABLE `st_complain` (
  `ID` int(10) NOT NULL,
  `C_ID` varchar(10) NOT NULL default '',
  `TYPE_OF_COMPLAIN` varchar(10) NOT NULL,
  `DATE_OF_COMPLAIN` varchar(10) NOT NULL,
  `DETAIL` varchar(50) NOT NULL,
  `POLICE_STATION_NAME` varchar(20) NOT NULL default '',
  `IMAGE` varchar(10) default NULL,
  `DOCUMENT` varchar(10) default NULL,
  `POLICE_ST_ID` int(20) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `POLICE_STATION_ID` (`POLICE_ST_ID`),
  CONSTRAINT `FK_st_complain` FOREIGN KEY (`POLICE_ST_ID`) REFERENCES `st_police_station` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `st_complain` */

/*Table structure for table `st_crime_report` */

DROP TABLE IF EXISTS `st_crime_report`;

CREATE TABLE `st_crime_report` (
  `ID` int(10) NOT NULL,
  `CR_ID` varchar(10) NOT NULL default '',
  `TYPE_OF_CRIME` varchar(10) NOT NULL,
  `DATE_OF_CRIME` varchar(10) NOT NULL,
  `TIME` varchar(10) NOT NULL,
  `REPORT_DATE` varchar(10) NOT NULL,
  `PLACE` varchar(10) NOT NULL,
  `POLICE_STATION_NAME` varchar(10) NOT NULL,
  `DETAIL` varchar(50) NOT NULL,
  `PICTURE` varchar(10) default NULL,
  `DOCUMENT` varchar(10) default NULL,
  `POLICE_ST_ID` int(20) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `POLICE_ST_ID` (`POLICE_ST_ID`),
  CONSTRAINT `st_crime_report_ibfk_1` FOREIGN KEY (`POLICE_ST_ID`) REFERENCES `st_police_station` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `st_crime_report` */

/*Table structure for table `st_feedback` */

DROP TABLE IF EXISTS `st_feedback`;

CREATE TABLE `st_feedback` (
  `ID` int(10) NOT NULL,
  `NAME` varchar(20) NOT NULL,
  `EMAIL_ID` varchar(20) NOT NULL,
  `FEEDBACK` varchar(50) NOT NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `st_feedback` */

/*Table structure for table `st_hot_news` */

DROP TABLE IF EXISTS `st_hot_news`;

CREATE TABLE `st_hot_news` (
  `ID` int(10) NOT NULL,
  `NEWS` varchar(50) NOT NULL,
  `DECLARED_DATE` varchar(10) NOT NULL,
  `TIME` varchar(10) NOT NULL,
  `AUTHORIZED_PERSON` varchar(20) NOT NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `st_hot_news` */

/*Table structure for table `st_mail` */

DROP TABLE IF EXISTS `st_mail`;

CREATE TABLE `st_mail` (
  `ID` int(10) NOT NULL,
  `SENDER` varchar(20) NOT NULL,
  `DETAIL` varchar(50) NOT NULL,
  `ATTACHMENT` varchar(20) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `st_mail` */

/*Table structure for table `st_missing_person` */

DROP TABLE IF EXISTS `st_missing_person`;

CREATE TABLE `st_missing_person` (
  `ID` int(10) NOT NULL,
  `POLICE_ST_ID` int(10) default NULL,
  `NAME` varchar(20) NOT NULL,
  `AGE` varchar(10) NOT NULL,
  `GENDER` varchar(10) NOT NULL,
  `HEIGHT` varchar(10) NOT NULL,
  `DATE_OF_MISSING` varchar(10) NOT NULL,
  `DATE_OF_REPORTING` varchar(10) NOT NULL,
  `COMPLEXION` varchar(20) NOT NULL,
  `HAIR` varchar(10) NOT NULL,
  `MARK_OF_IDENTIFICATION` varchar(20) NOT NULL,
  `AREA_OF_MISSING` varchar(10) NOT NULL,
  `REPORT_ID` varchar(10) NOT NULL,
  `PHOTO` varchar(10) NOT NULL,
  PRIMARY KEY  (`ID`),
  KEY `POLICE_ST_ID` (`POLICE_ST_ID`),
  CONSTRAINT `FK_st_missing_person` FOREIGN KEY (`POLICE_ST_ID`) REFERENCES `st_police_station` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `st_missing_person` */

/*Table structure for table `st_most_wanted` */

DROP TABLE IF EXISTS `st_most_wanted`;

CREATE TABLE `st_most_wanted` (
  `ID` int(10) NOT NULL,
  `NAME_OF_CRIMINAL` varchar(50) default NULL,
  `TYPE_OF_CRIME` varchar(50) default NULL,
  `COMPLEXION` varchar(50) default NULL,
  `MARK_OF_IDENTIFICATION` varchar(50) default NULL,
  `AGE` varchar(10) default NULL,
  `GENDER` varchar(1) default NULL,
  `HEIGHT` varchar(10) default NULL,
  `STATUS` varchar(20) default NULL,
  `PHOTO` varchar(10) default NULL,
  `POLICE_ST_ID` int(20) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `POLICE_ST_ID` (`POLICE_ST_ID`),
  CONSTRAINT `st_most_wanted_ibfk_1` FOREIGN KEY (`POLICE_ST_ID`) REFERENCES `st_police_station` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `st_most_wanted` */

/*Table structure for table `st_police` */

DROP TABLE IF EXISTS `st_police`;

CREATE TABLE `st_police` (
  `ID` int(11) NOT NULL,
  `NAME` varchar(50) NOT NULL,
  `ADDRESS` varchar(50) NOT NULL,
  `GENDER` varchar(1) NOT NULL,
  `EMAIL_ADDRESS` varchar(20) NOT NULL,
  `POLICE_STATION_NAME` varchar(50) NOT NULL,
  `POLICE_STATION_CODE` varchar(50) NOT NULL,
  `SECURITY_QUESTION` varchar(50) default NULL,
  `ANSWER` varchar(50) NOT NULL,
  `MOBILE_NO` bigint(10) NOT NULL,
  `USER_ID` bigint(20) NOT NULL,
  `POLICE_ST_ID` int(20) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `POLICE_USER_FK` (`USER_ID`),
  KEY `FK_st_police` (`POLICE_ST_ID`),
  CONSTRAINT `FK_st_police` FOREIGN KEY (`POLICE_ST_ID`) REFERENCES `st_police_station` (`ID`),
  CONSTRAINT `POLICE_USER_FK` FOREIGN KEY (`USER_ID`) REFERENCES `st_user` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `st_police` */

/*Table structure for table `st_police_station` */

DROP TABLE IF EXISTS `st_police_station`;

CREATE TABLE `st_police_station` (
  `ID` int(10) NOT NULL,
  `NAME_OF_POLICE_STATION` varchar(50) NOT NULL default '',
  `CODE_OF_POLICE_STATION` varchar(50) NOT NULL default '',
  `AREA_COVERED` varchar(50) NOT NULL default '',
  `CONTACT_NO` int(15) NOT NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `st_police_station` */

/*Table structure for table `st_role` */

DROP TABLE IF EXISTS `st_role`;

CREATE TABLE `st_role` (
  `ID` int(10) NOT NULL,
  `NAME` varchar(50) default NULL,
  `DESCRIPTION` varchar(200) default NULL,
  `CREATED_BY` varchar(50) default NULL,
  `MODIFIED_BY` varchar(50) default NULL,
  `CREATED_DATETIME` timestamp NULL default CURRENT_TIMESTAMP,
  `MODIFIED_DATETIME` timestamp NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `st_role` */

/*Table structure for table `st_user` */

DROP TABLE IF EXISTS `st_user`;

CREATE TABLE `st_user` (
  `ID` bigint(20) NOT NULL,
  `FIRST_NAME` varchar(50) NOT NULL,
  `LAST_NAME` varchar(50) NOT NULL,
  `LOGIN` varchar(20) NOT NULL,
  `PASSWORD` varchar(20) NOT NULL,
  `DOB` datetime NOT NULL,
  `MOBILE_NO` varchar(10) NOT NULL,
  `ROLE_ID` int(10) default NULL,
  `UNSUCCESSFUL_LOGIN` int(11) NOT NULL,
  `GENDER` varchar(1) NOT NULL,
  `LAST_LOGIN` datetime NOT NULL,
  `USER_LOCK` varchar(1) NOT NULL,
  `REGISTERED_IP` varchar(15) NOT NULL,
  `LAST_LOGIN_IP` varchar(15) NOT NULL,
  `CREATED_BY` varchar(50) NOT NULL,
  `MODIFIED_BY` varchar(50) NOT NULL,
  `CREATED_DATETIME` datetime default NULL,
  `MODIFIED_DATETIME` datetime default NULL,
  PRIMARY KEY  (`ID`),
  KEY `ROLE_ID` (`ROLE_ID`),
  KEY `NewIndex1` (`LOGIN`),
  CONSTRAINT `FK_st_user` FOREIGN KEY (`ROLE_ID`) REFERENCES `st_role` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `st_user` */

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `ID` bigint(20) NOT NULL,
  `COLLEGE_ID` bigint(20) default NULL,
  `COLLEGE_NAME` varchar(255) default NULL,
  `FIRST_NAME` varchar(255) default NULL,
  `LAST_NAME` varchar(255) default NULL,
  `DATE_OF_BIRTH` datetime default NULL,
  `MOBILE_NO` varchar(255) default NULL,
  `EMAIL` varchar(255) default NULL,
  `CREATED_BY` varchar(255) default NULL,
  `MODIFIED_BY` varchar(255) default NULL,
  `CREATED_DATETIME` datetime default NULL,
  `MODIFIED_DATETIME` datetime default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `student` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
