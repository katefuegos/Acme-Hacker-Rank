﻿use `Acme-Hacker-Rank`;

-- MySQL dump 10.13  Distrib 5.5.29, for Win64 (x86)
--
-- Host: localhost    Database: Acme-Hacker-Rank
-- ------------------------------------------------------
-- Server version	5.5.29

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
-- Table structure for table `actor`
--

DROP TABLE IF EXISTS `actor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `vatnumber` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `surnames` varchar(255) DEFAULT NULL,
  `credit_card` int(11) NOT NULL,
  `user_account` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_fl7pq8veyyxgdk1s4awu0c7mo` (`credit_card`),
  UNIQUE KEY `UK_i7xei45auwq1f6vu25985riuh` (`user_account`),
  CONSTRAINT `FK_i7xei45auwq1f6vu25985riuh` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`),
  CONSTRAINT `FK_fl7pq8veyyxgdk1s4awu0c7mo` FOREIGN KEY (`credit_card`) REFERENCES `credit_card` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor`
--

LOCK TABLES `actor` WRITE;
/*!40000 ALTER TABLE `actor` DISABLE KEYS */;
/*!40000 ALTER TABLE `actor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `administrator` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `vatnumber` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `surnames` varchar(255) DEFAULT NULL,
  `credit_card` int(11) NOT NULL,
  `user_account` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_b2e562x98pje1n9vu0deulcev` (`credit_card`),
  UNIQUE KEY `UK_7ohwsa2usmvu0yxb44je2lge` (`user_account`),
  CONSTRAINT `FK_7ohwsa2usmvu0yxb44je2lge` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`),
  CONSTRAINT `FK_b2e562x98pje1n9vu0deulcev` FOREIGN KEY (`credit_card`) REFERENCES `credit_card` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` VALUES (57,0,'ES99999999R','C/Calle, 54','elena@gmail.com','Admin','+34(456)123456789','https://cdn.pixabay.com/photo/2017/06/26/02/47/people-2442565_960_720.jpg','admin',58,52);
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `application`
--

DROP TABLE IF EXISTS `application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `application` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `link_answer` varchar(255) DEFAULT NULL,
  `publication_moment` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `submission_moment` datetime DEFAULT NULL,
  `text_answer` varchar(255) DEFAULT NULL,
  `curricula` int(11) NOT NULL,
  `hacker` int(11) NOT NULL,
  `position` int(11) NOT NULL,
  `problem` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ID1` (`status`),
  KEY `FK_hsw5exxa4qe3jxi8xdhnn0dl5` (`curricula`),
  KEY `FK_3hgwemcpn15ns7bi2upsq613y` (`hacker`),
  KEY `FK_cqpb54jon3yjef814oj6g6o4n` (`position`),
  KEY `FK_7gn6fojv7rim6rxyglc6n9mt2` (`problem`),
  CONSTRAINT `FK_7gn6fojv7rim6rxyglc6n9mt2` FOREIGN KEY (`problem`) REFERENCES `problem` (`id`),
  CONSTRAINT `FK_3hgwemcpn15ns7bi2upsq613y` FOREIGN KEY (`hacker`) REFERENCES `hacker` (`id`),
  CONSTRAINT `FK_cqpb54jon3yjef814oj6g6o4n` FOREIGN KEY (`position`) REFERENCES `position` (`id`),
  CONSTRAINT `FK_hsw5exxa4qe3jxi8xdhnn0dl5` FOREIGN KEY (`curricula`) REFERENCES `curricula` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application`
--

LOCK TABLES `application` WRITE;
/*!40000 ALTER TABLE `application` DISABLE KEYS */;
INSERT INTO `application` VALUES (96,0,NULL,'2018-03-23 12:00:00','PENDING',NULL,NULL,84,59,70,74),(97,0,'http://www.linkAnswer2.com','2018-05-11 12:00:00','SUBMITTED','2019-02-12 12:00:00','text2',84,59,70,75);
/*!40000 ALTER TABLE `application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `vatnumber` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `surnames` varchar(255) DEFAULT NULL,
  `credit_card` int(11) NOT NULL,
  `user_account` int(11) NOT NULL,
  `comercial_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_cp2qc9fdm9995xdhrrd06n86c` (`credit_card`),
  UNIQUE KEY `UK_pno7oguspp7fxv0y2twgplt0s` (`user_account`),
  CONSTRAINT `FK_pno7oguspp7fxv0y2twgplt0s` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`),
  CONSTRAINT `FK_cp2qc9fdm9995xdhrrd06n86c` FOREIGN KEY (`credit_card`) REFERENCES `credit_card` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES (65,0,'ES99999999R','C/Calle, 54','francisco@gmail.com','Francisco','+34(456)123456789','https://cdn.pixabay.com/photo/2017/06/26/02/47/people-2442565_960_720.jpg','Benedicto',66,55,'C3V3RYs SL'),(67,0,'ES99999999R','C/Calle, 54','maria@gmail.com','Maria','+34(456)123456789','https://cdn.pixabay.com/photo/2017/06/26/02/47/people-2442565_960_720.jpg','Del olmo',68,56,'Hoolie');
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `configuration`
--

DROP TABLE IF EXISTS `configuration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `configuration` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `banner` varchar(255) DEFAULT NULL,
  `country_code` int(11) NOT NULL,
  `finder_cache_time` int(11) NOT NULL,
  `finder_max_results` int(11) NOT NULL,
  `system_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuration`
--

LOCK TABLES `configuration` WRITE;
/*!40000 ALTER TABLE `configuration` DISABLE KEYS */;
INSERT INTO `configuration` VALUES (69,0,'https://i.imgur.com/7b8lu4b.png',34,1,10,'Acme-Hacker-Rank');
/*!40000 ALTER TABLE `configuration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `configuration_welcome_message`
--

DROP TABLE IF EXISTS `configuration_welcome_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `configuration_welcome_message` (
  `configuration` int(11) NOT NULL,
  `welcome_message` varchar(255) DEFAULT NULL,
  `welcome_message_key` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`configuration`,`welcome_message_key`),
  CONSTRAINT `FK_elnslrt6ishgh9y89wbg4vybr` FOREIGN KEY (`configuration`) REFERENCES `configuration` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuration_welcome_message`
--

LOCK TABLES `configuration_welcome_message` WRITE;
/*!40000 ALTER TABLE `configuration_welcome_message` DISABLE KEYS */;
INSERT INTO `configuration_welcome_message` VALUES (69,'Welcome to Acme hacker Rank! We\'re IT hacker\'s favourite job\n						marketplace!\n					','EN'),(69,'¡Bienvenidos a Acme Hacker Rank! ¡Somos el mercado de\n						trabajo favorito de los\n						profesionales de las TICs!\n					','ES');
/*!40000 ALTER TABLE `configuration_welcome_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `credit_card`
--

DROP TABLE IF EXISTS `credit_card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `credit_card` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `cvvcode` int(11) NOT NULL,
  `brand_name` varchar(255) DEFAULT NULL,
  `expiration_month` int(11) NOT NULL,
  `expiration_year` int(11) NOT NULL,
  `holder_name` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `credit_card`
--

LOCK TABLES `credit_card` WRITE;
/*!40000 ALTER TABLE `credit_card` DISABLE KEYS */;
INSERT INTO `credit_card` VALUES (58,0,110,'VISA',9,2021,'holderName1','4172711227177926'),(60,0,308,'VISA',4,2019,'holderName2','4950731573628957'),(62,0,561,'VISA',6,2019,'holderName2','4521808304130881'),(66,0,760,'VISA',10,2019,'holderName4','4994669620632598'),(68,0,316,'VISA',9,2019,'holderName5','4835411050700741');
/*!40000 ALTER TABLE `credit_card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curricula`
--

DROP TABLE IF EXISTS `curricula`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curricula` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `copy` bit(1) NOT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `github_profile` varchar(255) DEFAULT NULL,
  `linkedinprofile` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `statement` varchar(255) DEFAULT NULL,
  `hacker` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_cmpmwnbib50w71nx0wjlp8x79` (`hacker`),
  CONSTRAINT `FK_cmpmwnbib50w71nx0wjlp8x79` FOREIGN KEY (`hacker`) REFERENCES `hacker` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curricula`
--

LOCK TABLES `curricula` WRITE;
/*!40000 ALTER TABLE `curricula` DISABLE KEYS */;
INSERT INTO `curricula` VALUES (82,0,'\0','fullname1','http://www.github1.com','http://www.linkedin1.com','666666666','statement1',59),(83,0,'\0','fullname2','http://www.github2.com','http://www.linkedin2.com','655555555','statement2',61),(84,0,'','fullname1','http://www.github1.com','http://www.linkedin1.com','666666666','statement1',59);
/*!40000 ALTER TABLE `curricula` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `education_data`
--

DROP TABLE IF EXISTS `education_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `education_data` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `degree` varchar(255) DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `institution` varchar(255) DEFAULT NULL,
  `mark` int(11) NOT NULL,
  `start_date` datetime DEFAULT NULL,
  `curricula` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_b1949439gwkx4pjayup8gn2d6` (`curricula`),
  CONSTRAINT `FK_b1949439gwkx4pjayup8gn2d6` FOREIGN KEY (`curricula`) REFERENCES `curricula` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `education_data`
--

LOCK TABLES `education_data` WRITE;
/*!40000 ALTER TABLE `education_data` DISABLE KEYS */;
INSERT INTO `education_data` VALUES (93,0,'degree1','2019-03-23 12:00:00','institution1',23,'2018-03-23 12:00:00',82),(94,0,'degree2','2019-03-21 12:00:00','institution2',32,'2018-05-22 12:00:00',83),(95,0,'degree1','2019-03-23 12:00:00','institution1',23,'2018-03-23 12:00:00',84);
/*!40000 ALTER TABLE `education_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `finder`
--

DROP TABLE IF EXISTS `finder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `finder` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `deadline_max` datetime DEFAULT NULL,
  `deadline_min` datetime DEFAULT NULL,
  `keyword` varchar(255) DEFAULT NULL,
  `last_update` datetime DEFAULT NULL,
  `min_salary` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `finder`
--

LOCK TABLES `finder` WRITE;
/*!40000 ALTER TABLE `finder` DISABLE KEYS */;
INSERT INTO `finder` VALUES (63,0,'2029-09-09 09:13:00','2018-09-09 09:13:00','','2019-04-04 09:13:00',100),(64,0,'2029-09-09 09:13:00','2018-09-09 09:13:00','','2019-04-04 09:13:00',100);
/*!40000 ALTER TABLE `finder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `finder_positions`
--

DROP TABLE IF EXISTS `finder_positions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `finder_positions` (
  `finder` int(11) NOT NULL,
  `positions` int(11) NOT NULL,
  KEY `FK_3d46gil0nks2dhgg7cyhv2m39` (`positions`),
  KEY `FK_l0b3qg4nly59oeqhe8ig5yssc` (`finder`),
  CONSTRAINT `FK_l0b3qg4nly59oeqhe8ig5yssc` FOREIGN KEY (`finder`) REFERENCES `finder` (`id`),
  CONSTRAINT `FK_3d46gil0nks2dhgg7cyhv2m39` FOREIGN KEY (`positions`) REFERENCES `position` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `finder_positions`
--

LOCK TABLES `finder_positions` WRITE;
/*!40000 ALTER TABLE `finder_positions` DISABLE KEYS */;
INSERT INTO `finder_positions` VALUES (63,70),(63,71),(63,72),(64,70);
/*!40000 ALTER TABLE `finder_positions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hacker`
--

DROP TABLE IF EXISTS `hacker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hacker` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `vatnumber` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `surnames` varchar(255) DEFAULT NULL,
  `credit_card` int(11) NOT NULL,
  `user_account` int(11) NOT NULL,
  `finder` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_tb3fnr0u8r6bad61eco2pldkq` (`credit_card`),
  UNIQUE KEY `UK_pechhr6iex4b7l2rymmx1xi38` (`user_account`),
  KEY `FK_eohjws41xqofou03dl7t7aku4` (`finder`),
  CONSTRAINT `FK_pechhr6iex4b7l2rymmx1xi38` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`),
  CONSTRAINT `FK_eohjws41xqofou03dl7t7aku4` FOREIGN KEY (`finder`) REFERENCES `finder` (`id`),
  CONSTRAINT `FK_tb3fnr0u8r6bad61eco2pldkq` FOREIGN KEY (`credit_card`) REFERENCES `credit_card` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hacker`
--

LOCK TABLES `hacker` WRITE;
/*!40000 ALTER TABLE `hacker` DISABLE KEYS */;
INSERT INTO `hacker` VALUES (59,1,'ES99999999R','C/Calle, 52','h4ck3r2000@gmail.com','7H3 H4CH3R M4N','+34(456)123456789','https://cdn.pixabay.com/photo/2017/06/26/02/47/people-2442565_960_720.jpg','Joseda',60,53,64),(61,1,'ES99999999R','C/Calle, 53','adam@gmail.com','Adam','+34(456)123456789','https://cdn.pixabay.com/photo/2017/06/26/02/47/people-2442565_960_720.jpg','Cienfuegos',62,54,63);
/*!40000 ALTER TABLE `hacker` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequences`
--

DROP TABLE IF EXISTS `hibernate_sequences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) DEFAULT NULL,
  `sequence_next_hi_value` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequences`
--

LOCK TABLES `hibernate_sequences` WRITE;
/*!40000 ALTER TABLE `hibernate_sequences` DISABLE KEYS */;
INSERT INTO `hibernate_sequences` VALUES ('domain_entity',1);
/*!40000 ALTER TABLE `hibernate_sequences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `miscellaneous_data`
--

DROP TABLE IF EXISTS `miscellaneous_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `miscellaneous_data` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `attachments` varchar(255) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `curricula` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_h48spqfrohqicpoi2y2kmdvhb` (`curricula`),
  CONSTRAINT `FK_h48spqfrohqicpoi2y2kmdvhb` FOREIGN KEY (`curricula`) REFERENCES `curricula` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `miscellaneous_data`
--

LOCK TABLES `miscellaneous_data` WRITE;
/*!40000 ALTER TABLE `miscellaneous_data` DISABLE KEYS */;
INSERT INTO `miscellaneous_data` VALUES (88,0,'http://www.attachment1.com','text1',82),(89,0,'http://www.attachment2.com','text2',82),(90,0,'http://www.attachment3.com','text3',83),(91,0,'http://www.attachment1.com','text1',84),(92,0,'http://www.attachment2.com','text2',84);
/*!40000 ALTER TABLE `miscellaneous_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notification` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `body` varchar(255) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `actor` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
INSERT INTO `notification` VALUES (98,0,'Se ha detectado una brecha de seguridad que afecta a los hackers / We have detected a security breach who affects hackers','2019-01-23 12:00:00','Brecha de seguridad hackers / Security breach hackers',59),(99,0,'Se ha detectado una brecha de seguridad que afecta a todos los usuarios / We have detected a security breach who affects all users','2019-01-24 13:00:00','Brecha de seguridad / Security breach',59),(100,0,'Se ha detectado una brecha de seguridad que afecta a las empresas / We have detected a security breach who affects companies','2019-01-23 12:00:00','Brecha de seguridad empresas / Security breach companies',65),(101,0,'Se ha detectado una brecha de seguridad que afecta a todos los usuarios / We have detected a security breach who affects all users','2019-01-24 13:00:00','Brecha de seguridad / Security breach',65),(102,0,'Se ha detectado una brecha de seguridad que afecta a todos los usuarios / We have detected a security breach who affects all users','2019-01-24 13:00:00','Brecha de seguridad / Security breach',57);
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `position`
--

DROP TABLE IF EXISTS `position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `position` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `cancelled` bit(1) DEFAULT NULL,
  `deadline` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `draftmode` bit(1) NOT NULL,
  `profile` varchar(255) DEFAULT NULL,
  `salary` double DEFAULT NULL,
  `skills` varchar(255) DEFAULT NULL,
  `tecnologies` varchar(255) DEFAULT NULL,
  `ticker` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `company` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_15390c4j2aeju6ha0iwvi6mc5` (`ticker`),
  KEY `ID1` (`draftmode`),
  KEY `ID2` (`cancelled`),
  KEY `FK_7qlfn4nye234rrm4w83nms1g8` (`company`),
  CONSTRAINT `FK_7qlfn4nye234rrm4w83nms1g8` FOREIGN KEY (`company`) REFERENCES `company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `position`
--

LOCK TABLES `position` WRITE;
/*!40000 ALTER TABLE `position` DISABLE KEYS */;
INSERT INTO `position` VALUES (70,0,'\0','2019-09-09 09:13:00','description1','\0','profile1',1230,'skills1','tecnologies1','C3V3-8547','title1',65),(71,0,'\0','2019-05-12 10:00:00','description2','','profile2',1450,'skills2','tecnologies2','C3V3-8987','title2',65),(72,0,'\0','2019-01-23 12:00:00','description3','\0','profile3',2587.5,'skills3','tecnologies3','HOOL-5245','title3',67),(73,0,'\0','2019-05-12 10:00:00','description4','','profile4',4450,'skills4','tecnologies4','C3V3-4444','title4',65);
/*!40000 ALTER TABLE `position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `position_data`
--

DROP TABLE IF EXISTS `position_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `position_data` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `curricula` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_50pf203n1ob4iihr5n18eeoxf` (`curricula`),
  CONSTRAINT `FK_50pf203n1ob4iihr5n18eeoxf` FOREIGN KEY (`curricula`) REFERENCES `curricula` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `position_data`
--

LOCK TABLES `position_data` WRITE;
/*!40000 ALTER TABLE `position_data` DISABLE KEYS */;
INSERT INTO `position_data` VALUES (85,0,'description1','2019-10-23 12:00:00','2018-01-23 12:00:00','title1',82),(86,0,'description2','2019-10-25 12:00:00','2018-03-23 12:00:00','title2',83),(87,0,'description1','2019-10-23 12:00:00','2018-01-23 12:00:00','title1',84);
/*!40000 ALTER TABLE `position_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `problem`
--

DROP TABLE IF EXISTS `problem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `problem` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `attachments` varchar(255) DEFAULT NULL,
  `draftmode` bit(1) NOT NULL,
  `hint` varchar(255) DEFAULT NULL,
  `statement` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `position` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ID1` (`draftmode`),
  KEY `FK_ehy58i7iq25u9d829b76s1891` (`position`),
  CONSTRAINT `FK_ehy58i7iq25u9d829b76s1891` FOREIGN KEY (`position`) REFERENCES `position` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `problem`
--

LOCK TABLES `problem` WRITE;
/*!40000 ALTER TABLE `problem` DISABLE KEYS */;
INSERT INTO `problem` VALUES (74,0,'http://www.attachment1.com','\0','hint1','statement1','title1',70),(75,0,'http://www.attachment2.com','\0','hint2','statement2','title2',70),(76,0,'http://www.attachment3.com','','hint3','statement3','title3',70),(77,0,'http://www.attachment4.com','','hint4','statement4','title4',71),(78,0,'http://www.attachment5.com','\0','hint5','statement5','title5',72),(79,0,'http://www.attachment6.com','\0','hint6','statement6','title6',72),(80,0,'http://www.attachment7.com','\0','hint6','statement7','title7',73),(81,0,'http://www.attachment8.com','\0','hint8','statement8','title8',73);
/*!40000 ALTER TABLE `problem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_account`
--

DROP TABLE IF EXISTS `user_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_account` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_castjbvpeeus0r8lbpehiu0e4` (`username`),
  KEY `ID1` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account`
--

LOCK TABLES `user_account` WRITE;
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;
INSERT INTO `user_account` VALUES (52,0,'','21232f297a57a5a743894a0e4a801fc3','admin'),(53,0,'','2ba2a8ac968a7a2b0a7baa7f2fef18d2','hacker1'),(54,0,'','91af68b69a50a98dbc0800942540342c','hacker2'),(55,0,'','df655f976f7c9d3263815bd981225cd9','company1'),(56,0,'','d196a28097115067fefd73d25b0c0be8','company2');
/*!40000 ALTER TABLE `user_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_account_authorities`
--

DROP TABLE IF EXISTS `user_account_authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_account_authorities` (
  `user_account` int(11) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  KEY `FK_pao8cwh93fpccb0bx6ilq6gsl` (`user_account`),
  CONSTRAINT `FK_pao8cwh93fpccb0bx6ilq6gsl` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account_authorities`
--

LOCK TABLES `user_account_authorities` WRITE;
/*!40000 ALTER TABLE `user_account_authorities` DISABLE KEYS */;
INSERT INTO `user_account_authorities` VALUES (52,'ADMIN'),(53,'HACKER'),(54,'HACKER'),(55,'COMPANY'),(56,'COMPANY');
/*!40000 ALTER TABLE `user_account_authorities` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-25 18:59:14
