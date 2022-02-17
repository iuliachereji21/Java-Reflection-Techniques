-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ptassignment3
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(256) NOT NULL,
  `address` varchar(256) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `email` varchar(256) NOT NULL,
  `dateOfBirth` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (1,'Chereji Iulia','Com. Baciu, Aleea Iasomiei, nr.1, ap. 5, Jud. Cluj, Romania','0755228943','iuliachereji@gmail.com','2000-03-21'),(2,'Lup Lucia','Cluj-Napoca, Str. Paltinis, nr. 10, Jud. Cluj, Romania','0756456678','luplucia@gmail.com','2000-10-13'),(3,'Adela Rus','Cluj-Napoca, Str. Dunarii, nr.40','0726350019','adelarus@yahoo.com','');
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `id` int NOT NULL,
  `idClient` int NOT NULL,
  `idProduct` int NOT NULL,
  `quantity` int NOT NULL,
  `price` float NOT NULL,
  `dateAndTime` varchar(256) NOT NULL,
  KEY `clientForeignKey_idx` (`idClient`),
  KEY `productForeignKey_idx` (`idProduct`),
  CONSTRAINT `clientForeignKey` FOREIGN KEY (`idClient`) REFERENCES `client` (`id`),
  CONSTRAINT `productForeignKey` FOREIGN KEY (`idProduct`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (1,1,1,2,25.98,'2021-04-14 14:20:38'),(2,2,2,10,301.7,'2021-04-14 14:21:19'),(2,2,4,1,70,'2021-04-14 14:21:19'),(2,2,5,2,67.38,'2021-04-14 14:21:19'),(3,2,3,30,899.7,'2021-04-14 20:32:19'),(4,2,4,10,700,'2021-04-14 20:33:46'),(5,2,7,9,539.91,'2021-04-14 20:34:17'),(6,1,3,10,299.9,'2021-04-17 09:33:12'),(7,1,3,1,29.99,'2021-04-17 10:36:47'),(8,1,1,10,129.9,'2021-04-17 11:15:33'),(8,1,3,2,59.98,'2021-04-17 11:15:33'),(8,1,16,1,2.16,'2021-04-17 11:15:33'),(9,2,14,3,0.3,'2021-04-17 11:19:23'),(9,2,12,1,13.99,'2021-04-17 11:19:23'),(9,2,15,2,13.98,'2021-04-17 11:19:23'),(10,3,11,2,127.98,'2021-04-17 11:26:10'),(11,3,1,3,38.97,'2021-04-17 13:35:38'),(12,1,10,113,74.58,'2021-04-17 18:08:01');
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(256) NOT NULL,
  `price` float NOT NULL,
  `stock` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Mug',12.99,87),(2,'Pillow',30.17,27),(3,'Phone Case',29.99,107),(4,'Hat',70,40),(5,'Clock',33.69,20),(6,'Watch',120.55,14),(7,'Ring',59.99,1),(8,'Pen',2.13,100),(9,'Photo Album',50,7),(10,'Sticker',0.66,887),(11,'Doll',63.99,24),(12,'Brush',13.99,32),(13,'Candle',10,60),(14,'Paper',0.1,2997),(15,'Scissors',6.99,298),(16,'Ruller',2.16,49);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'ptassignment3'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-17 18:14:35
