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
  `chest_description` longtext,
  `chest_item` int DEFAULT NULL,
  `chest_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`chest_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Chest`
--

LOCK TABLES `Chest` WRITE;
/*!40000 ALTER TABLE `Chest` DISABLE KEYS */;
INSERT INTO `Chest` (`chest_id`, `chest_description`, `chest_item`, `chest_name`) VALUES (1,'Covered in cobwebs and full of holes, the wood looks rotted from damp, but it looks like you can pull back the lid to search it…\n\n',1,'Wooden Barrel'),(2,'They look as if they’ve been here a very long time, but has an official east land shipping stamp on the side, the lid is loose\n and the contents are almost visible, a quick search would reveal what this is hiding…',2,'Pile of broken shipping crates'),(3,'Reinforced with forged iron bands, it looks heavy duty but the lock has been busted open, it looks like somehow has tried to hide\n it beneath a pile of old rubble, what it contains may be worth checking out…',3,'Wooden Chest');
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
  `item_description` longtext NOT NULL,
  `item_melee_damage` int NOT NULL,
  `item_magic_damage` int NOT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Item`
--

LOCK TABLES `Item` WRITE;
/*!40000 ALTER TABLE `Item` DISABLE KEYS */;
INSERT INTO `Item` (`item_id`, `item_name`, `item_description`, `item_melee_damage`, `item_magic_damage`) VALUES (1,'Black Scythe','A long wooden handle leads to a matte black blade that sits atop it. The handle is pristine apart from 2 burn marks in the \nmiddle, you presume this is where you should hold it. You equip the Black Scythe\n',6,0),(2,'Rusted Flail','A spiked metal ball swings at the end of a long, rusted chain. The wooden handle is covered with leather, the reason for this you \ncan only presume is to kill more comfortably. You equip the Rusted Flail\n',3,0),(3,'Magical Fists','The container has nothing inside. At first you are shocked but before you can think longer you feel a tingle in your hands. You \nclench your fists to discover a new found strength inside you. You equip your Fists',4,0),(4,'Machete','A fairly large broad blade, could probably wield this one handed without difficulty. Nothing special about this, a standard \nleather hilt bolted together over the tang. Looks like it\'d be better suited for chopping meat or fruit than combat.. oh well... beggars can\'t \nbe choosers...',5,0),(5,'Katana','A long curved blade with a squared cross-guard, the grip is long like it should be wielded with two hands. The craftsmanship on \nthis blade is remarkable, the balance is impeccable. Though the blade looks to be made from extremely durable material, it feels incredibly \nlight. Better be careful with this...',7,0),(6,'Fire Bolt Spell','An old scruffy piece of paper rolled into a scroll, it is covered in runic symbols, you somehow make sense of it, a spell for a \nlow powered fire bolt, you feel powers surging deep within you and a tingling sensation in your fingertips',0,5),(7,'Fire Blast Spell','An old scruffy piece of paper rolled into a scroll, it is covered in runic symbols, you somehow make sense of it, a spell for a \nlow powered fire bolt, you feel powers surging deep within you and a tingling sensation in your fingertips',0,6),(8,'Fire Ball Spell','An old scruffy piece of paper rolled into a scroll, it is covered in runic symbols, you somehow make sense of it, a spell for a \nhigh powered fireball, you feel powers surging deep within you and a tingling sensation in your fingertips... better be careful with this... \ndon\'t want to set the whole dungeon alight',0,4),(9,'Long Wooden Staff','An old wooden staff with a leather handle in the middle sits untouched. The staff is battered and bruised at the end, signalling \nto you that it\'s been very well used. The middle of the staff remains, not pristine but relatively untouched',2,0),(10,'Aging Spell','An old scruffy piece of paper rolled into a scroll, it is covered in runic symbols, you somehow make sense of it, a spell for to \nrapidly age your opponent to death, you feel powers surging deep within you and a tingling sensation in your fingertips',0,7);
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
  `npc_armour` int DEFAULT NULL,
  `npc_description` longtext,
  `npc_gold_carried` int DEFAULT NULL,
  `npc_health` int DEFAULT NULL,
  `npc_is_friendly` bit(1) DEFAULT NULL,
  `npc_item_carried` int DEFAULT NULL,
  `npc_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`npc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `NPC`
--

LOCK TABLES `NPC` WRITE;
/*!40000 ALTER TABLE `NPC` DISABLE KEYS */;
INSERT INTO `NPC` (`npc_id`, `npc_armour`, `npc_description`, `npc_gold_carried`, `npc_health`, `npc_is_friendly`, `npc_item_carried`, `npc_name`) VALUES (1,0,'An old man with a long blue robe, he has a grey beard that goes as far down as his knees, and a blue pointy hat that sits atop \nhis head. He has small round glasses that sit on the edge of his nose.\n',3,4,_binary '',6,'Scruffy Wizard'),(2,1,'A short figure stands with a long brown robe, and their head ducked. You can\'t make out gender they are, you can\'t even make out \nif they are human. A small brown rope ties the robe together, their face is shrouded in darkness',5,6,_binary '',10,'Hooded Figure'),(3,2,'Nothing much strikes you about this figure apart from how incredibly old she is. You didn\'t think it was possible for a creature \nto be so old. You wonder how they\'ve managed to make it this far.',1,8,_binary '',3,'Old Witch'),(4,1,'A short unpleasant looking thing covered in warts with a large nose and large bat like ears, carrying a small dagger and looking \nat you menacingly... better not approach unless you\'re ready to fight for your gold..',9,5,_binary '\0',3,'Goblin'),(5,3,'A short but heavily built humanoid with a long beard, carrying two battleaxes and wearing some finely crafted armour. You were \nsure Dwarves were usually friendly but this Dwarf has an evil presence about him... approach with care...',12,7,_binary '\0',7,'Demonic Dwarf'),(6,1,'Though smaller and shorter than you, this cat like creature stand proud wielding a shortbow and some skill-fully crafted armour. \nShe looks very agile and not to be messed with... though looks like she may be protecting something of value... approach with caution..',4,4,_binary '\0',3,'Tabaxi Female'),(7,0,'Ah a pile of bones! Ah its moving! Ah its coming right at you!! A large animated skeleton wielding an old rusty sword and shield comes \nhurtling toward you..',7,6,_binary '\0',5,'Skeleton'),(8,4,'A tall knight in shining armour stands with his sword and shield at the ready. They stand incredibly still, you can\'t quite \ntell if there\'s actually someone in the armour. If it wasn\'t for the knights head rotating to follow you around the room you still wouldn\'t \nknow\n',9,5,_binary '',5,'Knight'),(9,0,'In the corner stands a very tall, but very slender creature. It\'s entire body is covered in a thick, scruffy black fur. This fur \nstop you seeing any detail in their face, hands or any other part of the body. All you can hear is a heavy breathing from their general \ndirection.\n',6,12,_binary '\0',3,'Black Furry Creature');
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
  `room_description` longtext,
  `room_is_boss` bit(1) DEFAULT NULL,
  `room_name` varchar(255) DEFAULT NULL,
  `room_no_of_chests` int DEFAULT NULL,
  `room_no_of_npcs` int DEFAULT NULL,
  PRIMARY KEY (`room_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Room`
--

LOCK TABLES `Room` WRITE;
/*!40000 ALTER TABLE `Room` DISABLE KEYS */;
INSERT INTO `Room` (`room_id`, `room_description`, `room_is_boss`, `room_name`, `room_no_of_chests`, `room_no_of_npcs`) VALUES (1,'Beyond the narrow opening lies a large, clammy room. It\'s covered in puddles of water, dead vermin and crawling insects. You can just \nmake out the figures and shapes that lie ahead, a collection of broken wooden barrels in one corner with one intact (Wooden barrel 1). A large \nscrawny humanoid figure in another corner in what appears to be wizard robes and wizard hat (Scruffy Wizard). Nearer the centre of the room \namongst the piles of puddles and rubble lies a pile of skeletal remains (Skeleton). There is a door to the north, and another to the west... \nwhat would you like to do?',_binary '\0','Wizard Room',3,2),(2,'This room appears to be a dimly lit cave with lit makeshift torches, someone... or something has been here recently. Broken crates and \npieces of broken wood are scattered on the floor, forgotten and mutilated by time itself. A few shelters are dotted around the room made \nfrom logs and tanned animal hide. Dead ahead of you is a Sleeping Goblin (Goblin) near a lit bonfire with a spit-roast made from sticks with \nwhat looks like a rat cooking on it. In one corner lies what appears to be a wooden barrel covered in cobwebs and rubble (Wooden barrel 2). \nThere is a door to the east and another to the west... \nwhat would you like to do?\n',_binary '\0','Goblin Room',3,2),(3,'Beyond the entrance to this dark cave lies a large, filthy room. It\'s covered in rubble, large bones and dead vermin. This room slopes\n downwards into a foggy abyss though you can just make out what it contains from holes in the roof of the cave where light is seeping in. There \nis a large body of water with a little island in the centre with a stack of old broken crates (Pile of broken shipping crates 1). On the cusp \nof the water, a short figure stands with a long brown robe, and their head ducked (short hooded figure). There is only one exit door to your \nNorth. \nWhat would you like to do?',_binary '\0','Hooded Room',3,1),(4,'You enter a humid area. Several cages hold skeletal remains of various animals. Next to the cages are odd machines. What happened in \nthis place? There are various passages leading into this room but most have collapsed. Behind a pile of cages and near the odd machines, you \nsee a lot of shipping crates stacked up neatly (Pile of broken shipping crates 2), though nearby is an old hunched over figure pacing and \ncackling to itself (Old Witch). There is two exits that appear to be functional, one to the North-East and another to the South-East. \nWhat would you like to do?\n',_binary '\0','Witch Room',3,2),(5,'You are now inside a broken room. It\'s covered in dirt, dirt and dead vermin. A torch allows you to see remnants of sacks, crates and \ncaskets, there are what look like bedrolls dotted around the room, a collection of somethings have been using this place as a camp it seems, \nare they still here? Or did something worse come along... At the centre of the bedrolls is a Wooden Chest (Wooden Chest 1) reinforced with \niron bands and over in the corner leaning against the wall is a Dwarf of sorts... though something seems not right about him (Demonic Dwarf). \nA single exit lies to the east near the Dwarf. \nWhat would you like to do?',_binary '\0','Dwarf Room',3,2),(6,'This room is putrid and dense, full of plants and shrubs and life. How did it all grow here with only small cracks in the roof of the \ncave for light to seep through? Atop a large boulder in the centre of the room is a large reinforced wooden chest (Wooden Chest 2), nearby a \nbipedal humanoid figure... covered in fur? It looks like the Tabaxi (Tabaxi) cat people you\'ve read about but thought they were of myth. It \nappears to be blocking the path to the wooden chest as though it were protecting it. There are three exits, one on each wall, to the North, \nWest and East. \nWhat would you like to do?',_binary '\0','Tabaxi Room',3,2),(7,'You enter a large crypt, with scratch marks all over the entrance, the floor is littered with piles of bones. There are shackles \nand chains attached to the walls with Skeletons attached... those poor unfortunate souls... You slowly march to the centre of the crypt... who \nknows what lies ahead. Only one entrance to your north, An ominous metal door. Suddenly... a dripping from above you, every ounce of your \nbeing tells you not to look up but you do anyway. The ceiling stretches up too high for you to see past where the light can reach. Then \nsuddenly two specs of light are reflected back at you.. they\'re eyes... huge eyes.. the eyes of... something big... \nWhat would you like to do?\n',_binary '','Boss Room',3,2);
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Score`
--

LOCK TABLES `Score` WRITE;
/*!40000 ALTER TABLE `Score` DISABLE KEYS */;
INSERT INTO `Score` (`score_id`, `player_name`, `gold_score`) VALUES (3,'Player 1',300),(4,'Player 2',150),(5,'Player 3',600),(6,'Player 4',200);
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

-- Dump completed on 2020-12-09 15:13:38
