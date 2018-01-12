-- MySQL dump 10.13  Distrib 5.7.20, for Linux (x86_64)
--
-- Host: localhost    Database: EmployeeDB
-- ------------------------------------------------------
-- Server version	5.7.20-0ubuntu0.16.04.1

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
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(20) NOT NULL DEFAULT '',
  `name` varchar(20) NOT NULL DEFAULT '',
  `birth_year` int(20) NOT NULL DEFAULT '0',
  `home_town` varchar(20) NOT NULL DEFAULT '',
  `salary_factor` float(10,2) NOT NULL,
  `salary` float(10,2) NOT NULL,
  `faculty_department` varchar(30) NOT NULL DEFAULT '',
  `degree_position` varchar(20) NOT NULL DEFAULT '',
  `quantity_days` int(11) NOT NULL DEFAULT '0',
  `username` varchar(20) NOT NULL,
  `password` char(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (8,'Lecturer','Le van A',1992,'Quang Nam',3.00,7590.00,'Civil Engineering','Master',100,'manhlinh','827CCB0EEA8A706C4C34A16891F84E7B'),(11,'Officer','Tran Dinh Manh Linh',1989,'Hue',3.00,4090.00,'Academic Affairs','Admin',30,'tranmanhlinh','827CCB0EEA8A706C4C34A16891F84E7B'),(13,'Officer','le thi hang',1991,'hue',4.00,3470.00,'Academic Affairs','Regular employee',5,'hang','827CCB0EEA8A706C4C34A16891F84E7B'),(16,'Officer','Le Van b',1993,'quang nam',3.00,4090.00,'Academic Affairs','Head of department',30,'user2','827CCB0EEA8A706C4C34A16891F84E7B'),(17,'Officer','Tran Van d',1994,'Da nang',4.00,4820.00,'Inspection','Regular employee',50,'user3','827CCB0EEA8A706C4C34A16891F84E7B'),(23,'Officer','usertest',1991,'Quang Nam',3.00,3490.00,'Academic Affairs','Regular employee',30,'rinsama12','827CCB0EEA8A706C4C34A16891F84E7B');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-12 15:03:00
