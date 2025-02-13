/*
SQLyog Community v13.1.7 (64 bit)
MySQL - 8.0.23 : Database - fitness_tracking_system
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`fitness_tracking_system` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `fitness_tracking_system`;

/*Table structure for table `fitness_activities` */

DROP TABLE IF EXISTS `fitness_activities`;

CREATE TABLE `fitness_activities` (
  `activity_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `activity_type` varchar(50) DEFAULT NULL,
  `activity_duration` int DEFAULT NULL,
  `calories_burned` double(7,2) DEFAULT NULL,
  `heart_rate` int DEFAULT NULL,
  `activity_date` datetime DEFAULT NULL,
  `is_flag` int DEFAULT '0',
  PRIMARY KEY (`activity_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `fitness_activities_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `fitness_activities` */

insert  into `fitness_activities`(`activity_id`,`user_id`,`activity_type`,`activity_duration`,`calories_burned`,`heart_rate`,`activity_date`,`is_flag`) values 
(2,1,'跑步',30,190.03,58,'2025-02-12 02:00:00',0),
(3,1,'游泳',60,1900.00,89,'2025-02-10 01:00:00',0),
(4,1,'爬山',20,0.02,4,'2025-02-12 06:00:00',0),
(8,1,'骑车',12,1234.00,88,'2025-02-08 08:00:00',0),
(9,1,'俯卧撑',5,1200.00,88,'2025-02-12 14:00:00',0),
(10,2,'跑步',30,1200.00,90,'2025-02-12 12:45:00',0),
(11,1,'跑步',20,200.00,88,'2025-02-13 06:04:00',0);

/*Table structure for table `fitness_goals` */

DROP TABLE IF EXISTS `fitness_goals`;

CREATE TABLE `fitness_goals` (
  `goal_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `goal_type` varchar(50) DEFAULT NULL,
  `goal_value` double(10,2) DEFAULT NULL,
  `actual_value` double(10,2) DEFAULT '0.00',
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `progress` double DEFAULT NULL,
  `is_notification` int DEFAULT '0',
  `is_reminder` int DEFAULT '0',
  `is_flag` int DEFAULT '0',
  PRIMARY KEY (`goal_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `fitness_goals_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `fitness_goals` */

insert  into `fitness_goals`(`goal_id`,`user_id`,`goal_type`,`goal_value`,`actual_value`,`start_date`,`end_date`,`progress`,`is_notification`,`is_reminder`,`is_flag`) values 
(33,2,'步数',999.00,999.00,'2025-02-12 16:08:00','2025-02-12 17:08:00',100,2,1,0),
(34,1,'步数',1000.00,1000.00,'2025-02-13 14:06:00','2025-02-13 15:06:00',100,2,1,0);

/*Table structure for table `notifications` */

DROP TABLE IF EXISTS `notifications`;

CREATE TABLE `notifications` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `message` text,
  `type` varchar(255) DEFAULT NULL,
  `notification_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `notifications` */

insert  into `notifications`(`id`,`user_id`,`message`,`type`,`notification_time`) values 
(84,2,'您已进入健身目标时间！','提醒','2025-02-12 16:09:02'),
(85,2,'您已接近健身目标，加油！','接近目标','2025-02-12 16:10:19'),
(86,2,'恭喜您已完成健身目标！','目标完成','2025-02-12 16:12:01'),
(87,1,'您已进入健身目标时间！','提醒','2025-02-13 14:06:10'),
(88,1,'您已接近健身目标，加油！','接近目标','2025-02-13 14:06:28'),
(89,1,'恭喜您已完成健身目标！','目标完成','2025-02-13 14:06:45');

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `weight` decimal(5,2) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `fitness_goal` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `users` */

insert  into `users`(`user_id`,`username`,`password`,`email`,`weight`,`age`,`fitness_goal`) values 
(1,'username','e10adc3949ba59abbe56e057f20f883e','2535029951@qq.com',50.00,22,'1234567'),
(2,'jindan_123','e10adc3949ba59abbe56e057f20f883e','3060074716@qq.com',50.00,22,'无');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
