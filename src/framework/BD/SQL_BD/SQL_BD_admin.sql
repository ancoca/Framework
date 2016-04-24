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
INSERT INTO `admin` VALUES ('12812435D','felipe','joan','maria.jpeg',1,'josep','ferrero','maria@gmail.com','128124354','03/01/2096',-79,791.24,'20/07/1910',105,1281.61,252.24,28),('1542470K','joan','daniel','sara.jpeg',0,'jaume','conesa','daniel@gmail.com','015424700','26/01/1919',97,958.71,'23/03/1913',103,1554.57,431.71,24),('17734904H','josep','jaume','daniel.jpeg',0,'jaume','doria','sara@gmail.com','177349049','28/02/1984',32,487.45,'20/02/1977',39,1190.53,258.95,67),('20565387Y','joan','josep','walter.jpeg',0,'joan','aliaga','felipe@gmail.com','859749195','13/01/2016',0,419.82,'19/02/2032',-15,633.75,454.32,81),('21530850K','daniel','pepa','maria.jpeg',0,'jaume','bataller','daniel@gmail.com','215308501','11/09/2014',1,412.88,'08/11/2033',-17,1942.57,474.88,46),('27448023F','josep','vicent','walter.jpeg',1,'jaume','doria','felipe@gmail.com','274480239','13/04/1912',104,448.66,'03/10/1983',32,832.68,274.66,28),('32863475V','sara','pepa','maria.jpeg',0,'felipe','cucart','pepa@gmail.com','328634757','02/03/1952',64,793.74,'29/04/1960',55,792.34,479.24,79),('33623147E','felipe','felipe','vicent.jpeg',1,'felipe','bataller','jaume@gmail.com','022843603','28/02/2066',-49,813.1,'11/09/1930',85,1468.32,373.1,30),('3448105Z','maria','daniel','walter.jpeg',1,'joan','camarena','walter@gmail.com','034481058','21/10/1934',81,209.72,'18/07/2068',-52,803.42,437.22,65),('39500464B','walter','sara','vicent.jpeg',1,'sara','bataller','sara@gmail.com','395004644','09/09/1919',96,545.56,'08/03/2016',0,856.27,497.56,96),('42125789R','jaume','joan','josep.jpeg',1,'daniel','ferrero','josep@gmail.com','421257893','15/03/2079',-62,542.3,'05/06/1986',29,1506.5,364.3,66),('4768884H','maria','joan','joan.jpeg',0,'josep','doria','felipe@gmail.com','047688845','08/10/193',1822,910.15,'02/06/1937',78,1820.1,487.65,65),('52240961A','vicent','sara','maria.jpeg',1,'maria','aliaga','felipe@gmail.com','215621132','12/05/190',1825,727.65,'01/09/1932',83,1128.9,284.65,56),('60349551N','daniel','vicent','pepa.jpeg',0,'felipe','camarena','vicent@gmail.com','603495519','19/08/1927',88,821.51,'07/05/1916',99,1244.75,301.01,51),('73007860H','felipe','vicent','sara.jpeg',1,'pepa','bataller','daniel@gmail.com','730078609','02/02/2062',-45,683.85,'16/04/1934',82,1402.86,225.35,97),('86972749C','joan','pepa','vicent.jpeg',0,'daniel','aliaga','sara@gmail.com','869727497','08/08/1917',98,-57.79,'06/06/2094',-78,824.77,320.21,24),('91823924N','pepa','joan','pepa.jpeg',1,'vicent','bataller','daniel@gmail.com','918239242','25/10/1924',91,523.04,'22/08/1960',55,615.38,228.54,39),('92973990D','walter','joan','jaume.jpeg',0,'daniel','doria','pepa@gmail.com','361329992','11/08/1984',31,9388.53,'23/01/197',1819,1059.68,264.03,59),('93400507Z','pepa','pepa','jaume.jpeg',0,'josep','conesa','joan@gmail.com','934005079','23/05/1952',63,71.13,'01/01/2082',-65,1954.37,375.13,42),('9400757J','daniel','daniel','vicent.jpeg',1,'pepa','aliaga','jaume@gmail.com','590992458','18/10/2029',-13,472.84,'01/11/1998',17,632.76,351.34,73);
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

-- Dump completed on 2016-04-24 12:50:03
