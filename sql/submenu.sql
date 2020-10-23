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

/*Table structure for table `submenu` */

DROP TABLE IF EXISTS `submenu`;

CREATE TABLE `submenu` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `path` varchar(255) NOT NULL,
  `mid` int(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `mid` (`mid`),
  CONSTRAINT `submenu_ibfk_1` FOREIGN KEY (`mid`) REFERENCES `mainmenu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=204 DEFAULT CHARSET=utf8;

/*Data for the table `submenu` */

insert  into `submenu`(`id`,`title`,`path`,`mid`) values (101,'用户列表','/user',100),(102,'修改权限','/rights',100),(103,'运动模块','/sport',100),(104,'商品模块','/goods',100),(201,'运动科普','/Introduction',200),(202,'卡路里','/calories',200),(203,'营养配餐','/food',200);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
