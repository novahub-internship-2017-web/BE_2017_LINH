-- MySQL dump 10.13  Distrib 5.7.21, for Linux (x86_64)
--
-- Host: localhost    Database: BooksManagement
-- ------------------------------------------------------
-- Server version	5.7.21-0ubuntu0.16.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `books` (
  `book_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL DEFAULT 'Unknown',
  `author` varchar(25) NOT NULL DEFAULT 'Unknown',
  `createdAt` datetime DEFAULT NULL,
  `updatedAt` datetime DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `imagePath` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`book_id`),
  KEY `fk_user_id` (`user_id`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (7,'Catcher In The Rye','J. D. Salinger','2018-02-23 17:36:15','2018-03-02 10:55:49',3,'Lorem ipsum dolor sit amet, consectetur adipiscing elit.taciti sociosqu ad.','/resources/img/book_7_41hZbJx4T0L._SX379_BO1,204,203,200_.jpg'),(11,'Universe in the nutshell','Steven Hawking','2018-02-27 15:50:26','2018-03-06 16:32:24',3,'','/resources/img/book_11_41hZbJx4T0L._SX379_BO1,204,203,200_.jpg'),(15,'The Lord of the Rings','J. R. R. Tolkien','2018-02-28 17:31:44','2018-02-28 17:31:44',3,NULL,'/resources/img/book_15_Screenshot from 2017-12-13 18-02-13.png'),(25,'The Hunger Games','Suzanne Collins','2018-03-02 11:20:35','2018-03-02 11:20:35',3,'','/resources/img/genericBookCover.jpg'),(38,'fadfa','adfafa','2018-03-18 08:45:21','2018-03-18 08:45:21',28,NULL,'/resources/img/genericBookCover.jpg'),(39,'Detective Conan','Gosho Aoyama','2018-03-18 08:56:32','2018-03-18 08:56:32',28,NULL,'/resources/img/genericBookCover.jpg'),(40,'fadf','fadfa','2018-03-18 09:12:04','2018-03-18 09:12:04',28,NULL,'/resources/img/genericBookCover.jpg'),(43,'book 3','author 3','2018-03-18 11:15:03','2018-03-18 11:15:03',28,NULL,'/resources/img/genericBookCover.jpg'),(44,'book 4','author 4','2018-03-18 11:19:35','2018-03-18 11:22:14',28,'something','/resources/img/genericBookCover.jpg'),(45,'book 5','author 5','2018-03-18 11:22:42','2018-03-18 11:22:42',28,NULL,'/resources/img/genericBookCover.jpg'),(46,'book 6','author 6','2018-03-18 11:23:08','2018-03-18 11:23:08',28,NULL,'/resources/img/genericBookCover.jpg');
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (47);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(20) DEFAULT NULL,
  `lastName` varchar(20) DEFAULT NULL,
  `email` varchar(40) DEFAULT NULL,
  `password` char(60) DEFAULT NULL,
  `role` varchar(20) DEFAULT 'USER',
  `enabled` bit(1) DEFAULT b'1',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (3,'tran dinh manh','linh','manhlinh.sama@gmail.com','$2a$10$zOiMA0swLcgyEC6LHGOxzOoEIESIDmUopKixSsy1ix01XOnrCYM9q','ADMIN',''),(16,'Nguyet','Tran','trannguyet@gmail.com','$2a$10$PL1oRX2OLaA//j6CQ/9eA.1Tp5tSd/QManN6L9qyc5uTtjUq3HLwu','USER',''),(17,'Hang','Tran','hang@gmail.com','$2a$10$AaSvoLE7GVbktXJVVkUl2ewBwhS2jQ9ux4uq9OCsl9GAkvYhChhye','USER',''),(20,'linh','tran','linh123@gmail.com','$2a$10$OqRnpwuhLlMktlPasYCW/ODF4jRy3bY.TJQjtzygS1ZMnDzstf1oq','USER',''),(21,'tran dinh','linh','manhlinh.sama123@gmail.com','$2a$10$XDRrefdw5syQltBsYOoppONQ5l2PuURr.HowHnbJbnbAcupA3u8u6','USER',''),(28,'Lam ','Le Quang','lam@gmail.com','$2a$10$9JCcYF9uSrYy/FAPnAqeoOD37iaT12IlwUq8ZbC2mszgUAaBfFW6O','USER',''),(29,'Le Quang','Lam','quanglam@gmail.com','$2a$10$qkWzN0SfxKH41djhyk21FOTIJDfSc0V42YUmHkKrvjoMZ53SAp.wW','USER',''),(32,'abc','def','abc@gmail.com','$2a$10$r.7e56PlxFUYhkZhzsp/q.XouIJ7omas/Slhk/vLaqbjw1p2kDEeq','USER','');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-18 11:29:43
