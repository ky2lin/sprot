/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.31-log : Database - sport
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`sport` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `sport`;

/*Table structure for table `easyuser` */

DROP TABLE IF EXISTS `easyuser`;

CREATE TABLE `easyuser` (
  `id` int(24) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `state` tinyint(255) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `easyuser` */

insert  into `easyuser`(`id`,`username`,`password`,`email`,`role`,`state`) values (1,'admin','12345699','123@qq.com','超级管理员',1),(2,'zhangsan','aaaaaa','zhangsan@qq.com','普通管理员',1),(3,'admin001','popop','aaaa@qq.com','普通用户',1),(4,'这是一个用户','9999912','bbbb@163.com','普通用户',0),(7,'admin003','q123456','aaabb@qq.com','普通用户',1),(8,'admin005','123456','asdasd@qq.com','普通用户',0),(9,'admin007','123456','opopos@qq.com','普通用户',0),(10,'yuan','aaa000','yuan@163.com','超级管理员',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
