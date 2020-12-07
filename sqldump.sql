-- MySQL dump 10.13  Distrib 8.0.22, for osx10.15 (x86_64)
--
-- Host: 127.0.0.1    Database: deviousMUD
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Chest`
--

DROP TABLE IF EXISTS `Chest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Chest` (
  `chest_id` int NOT NULL AUTO_INCREMENT,
  `chest_name` text NOT NULL,
  `chest_description` text NOT NULL,
  `chest_item` int NOT NULL,
  PRIMARY KEY (`chest_id`),
  KEY `Chest_Item_item_id_fk` (`chest_item`),
  CONSTRAINT `Chest_Item_item_id_fk` FOREIGN KEY (`chest_item`) REFERENCES `Item` (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Chest`
--

LOCK TABLES `Chest` WRITE;
/*!40000 ALTER TABLE `Chest` DISABLE KEYS */;
/*!40000 ALTER TABLE `Chest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Item`
--

DROP TABLE IF EXISTS `Item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Item` (
  `item_id` int NOT NULL AUTO_INCREMENT,
  `item_name` text NOT NULL,
  `item_description` int NOT NULL,
  `item_attack_value` int NOT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Item`
--

LOCK TABLES `Item` WRITE;
/*!40000 ALTER TABLE `Item` DISABLE KEYS */;
/*!40000 ALTER TABLE `Item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `NPC`
--

DROP TABLE IF EXISTS `NPC`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `NPC` (
  `npc_id` int NOT NULL AUTO_INCREMENT,
  `npc_name` text NOT NULL,
  `npc_description` text NOT NULL,
  `npc_is_friendly` tinyint(1) NOT NULL DEFAULT '1',
  `npc_health` int NOT NULL DEFAULT '100',
  `npc_armour` int NOT NULL DEFAULT '10',
  `npc_gold_carried` int NOT NULL DEFAULT '100',
  `npc_item_carried` int NOT NULL,
  PRIMARY KEY (`npc_id`),
  KEY `NPC_Item_item_id_fk` (`npc_item_carried`),
  CONSTRAINT `NPC_Item_item_id_fk` FOREIGN KEY (`npc_item_carried`) REFERENCES `Item` (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `NPC`
--

LOCK TABLES `NPC` WRITE;
/*!40000 ALTER TABLE `NPC` DISABLE KEYS */;
/*!40000 ALTER TABLE `NPC` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Room`
--

DROP TABLE IF EXISTS `Room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Room` (
  `room_id` int NOT NULL AUTO_INCREMENT,
  `room_name` text NOT NULL,
  `room_description` text NOT NULL,
  `room_no_of_npcs` int NOT NULL DEFAULT '0',
  `room_no_of_chests` int NOT NULL DEFAULT '0',
  `room_is_boss` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Room`
--

LOCK TABLES `Room` WRITE;
/*!40000 ALTER TABLE `Room` DISABLE KEYS */;
/*!40000 ALTER TABLE `Room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Score`
--

DROP TABLE IF EXISTS `Score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Score` (
  `score_id` int NOT NULL AUTO_INCREMENT,
  `player_name` text NOT NULL,
  `gold_score` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`score_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Score`
--

LOCK TABLES `Score` WRITE;
/*!40000 ALTER TABLE `Score` DISABLE KEYS */;
/*!40000 ALTER TABLE `Score` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-07 17:05:03
