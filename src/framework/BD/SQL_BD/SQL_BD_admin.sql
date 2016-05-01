CREATE DATABASE  IF NOT EXISTS `SQL_BD` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci */;
USE `SQL_BD`;
-- MySQL dump 10.13  Distrib 5.5.49, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: SQL_BD
-- ------------------------------------------------------
-- Server version	5.5.49-0ubuntu0.14.04.1

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `DNI` varchar(9) COLLATE utf8_spanish_ci NOT NULL,
  `user` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `pass` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `avatar` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `state` tinyint(1) NOT NULL DEFAULT '0',
  `name` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `surname` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `email` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `mobilephone` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `datebirthday` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `age` int(11) NOT NULL,
  `benefits` float NOT NULL,
  `datecontract` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `old` int(11) NOT NULL,
  `salary` float NOT NULL,
  `incentive` float NOT NULL,
  `activity` int(11) NOT NULL,
  PRIMARY KEY (`DNI`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES ('24811897A','felipe','jaume','daniel.jpeg',1,'vicent','ferrero','josep@gmail.com','248118972','04/06/1936',79,921.8,'16/02/1924',92,1464.29,427.8,68),('35142522V','josep','jaume','vicent.jpeg',1,'pepa','conesa','jaume@gmail.com','351425224','29/10/1939',76,891.25,'12/09/1913',102,935.91,354.75,53),('38561013H','daniel','daniel','josep.jpeg',1,'josep','bataller','daniel@gmail.com','385610132','24/01/2044',-27,159.48,'01/10/2046',-30,1922.5,279.98,59),('4390057R','joan','josep','walter.jpeg',0,'daniel','cucart','sara@gmail.com','043900578','30/06/2095',-79,971.94,'24/11/1918',97,1789.77,460.94,52),('48292627X','user','User-123','/root',1,'Usuario','Administrador','example@example.com','123456789','02/02/1990',26,1055,'02/02/2015',1,1000,1000,100),('53872435H','walter','walter','felipe.jpeg',0,'josep','doria','maria@gmail.com','777852506','16/11/1966',49,758.89,'17/05/1921',94,1704.27,250.39,77);
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-01 17:40:07
