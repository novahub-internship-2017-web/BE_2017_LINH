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
  PRIMARY KEY (`book_id`),
  KEY `fk_user_id` (`user_id`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (7,'Catcher In The Rye','J. D. Salinger','2018-02-23 17:36:15','2018-03-02 10:55:49',3,'Lorem ipsum dolor sit amet, consectetur adipiscing elit.taciti sociosqu ad.'),(11,'Universe in the nutshell','Steven Hawking','2018-02-27 15:50:26','2018-02-28 17:01:06',3,NULL),(15,'The Lord of the Rings','J. R. R. Tolkien','2018-02-28 17:31:44','2018-02-28 17:31:44',3,NULL),(25,'The Hunger Games','Suzanne Collins','2018-03-02 11:20:35','2018-03-02 11:20:35',3,'');
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
INSERT INTO `hibernate_sequence` VALUES (30);
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
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (3,'tran dinh manh','linh','manhlinh.sama@gmail.com','$2a$10$/xu9OdYW8k1tsffd1fzO6.H5SVc0cvIXDTITeCDVhJRZrAeAYlgNq','ADMIN',''),(16,'Nguyet','Tran','trannguyet@gmail.com','$2a$10$PL1oRX2OLaA//j6CQ/9eA.1Tp5tSd/QManN6L9qyc5uTtjUq3HLwu','USER',''),(17,'Hang','Tran','hang@gmail.com','$2a$10$AaSvoLE7GVbktXJVVkUl2ewBwhS2jQ9ux4uq9OCsl9GAkvYhChhye','USER',''),(20,'linh','tran','linh123@gmail.com','$2a$10$OqRnpwuhLlMktlPasYCW/ODF4jRy3bY.TJQjtzygS1ZMnDzstf1oq','USER',''),(21,'tran dinh','linh','manhlinh.sama123@gmail.com','$2a$10$XDRrefdw5syQltBsYOoppONQ5l2PuURr.HowHnbJbnbAcupA3u8u6','USER',''),(28,'Lam ','Tran','lam@gmail.com','$2a$10$bE2UnPu6iHjtc4QWk5gsS.YWnUk1aWIaLMWLSkZRWbjhOt6kmz0vi','USER',''),(29,'Le Quang','Lam','quanglam@gmail.com','$2a$10$qkWzN0SfxKH41djhyk21FOTIJDfSc0V42YUmHkKrvjoMZ53SAp.wW','USER','');
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

-- Dump completed on 2018-03-06  9:08:48
