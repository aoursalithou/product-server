CREATE DATABASE  IF NOT EXISTS `product` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `product`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: product
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` int NOT NULL AUTO_INCREMENT,
  `product_series_id` int DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `description` varchar(100) NOT NULL,
  `ref` varchar(100) NOT NULL,
  `lot` varchar(100) NOT NULL,
  `manufacturer` varchar(100) NOT NULL,
  `purchase_date` date NOT NULL,
  `notes` varchar(100) DEFAULT NULL,
  `available` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `product_series_id` (`product_series_id`),
  CONSTRAINT `products_ibfk_1` FOREIGN KEY (`product_series_id`) REFERENCES `products_series` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,NULL,'Plate Inserter','Plate Inserter','SP0202','50096705','SIGNUS','2019-11-05','--',1),(14,NULL,'Trials','7x9x29 mm 0','BR00729-A','936381','SIGNUS','2020-01-27','--',1),(15,NULL,'Trials','8x9x29 mm 0','BR00829-A','936382','SIGNUS','2020-01-27','--',1),(16,NULL,'Trials','9x9x29 mm 0','BR00929-A','936383','SIGNUS','2020-01-27','--',1),(17,NULL,'Trials','10x9x29 mm 0','BR01029-A','936384','SIGNUS','2020-01-27','--',1),(18,NULL,'Trials','11x9x29 mm 0','BR01129-A','936385','SIGNUS','2020-01-27','--',1),(19,NULL,'Trials','12x9x29 mm 0','BR01229-A','936386','SIGNUS','2020-01-27','--',1),(20,NULL,'Trials','13x9x29 mm 0','BR01329-A','936387','SIGNUS','2020-01-27','--',1),(53,NULL,'Mounting Clip','Mounting Clip','JMPS198BFC','0216','Joimax','2019-10-14','--',1),(54,NULL,'Mounting Clip','Mounting Clip','JMPS198BFC','0216','Joimax','2019-10-14','--',1),(55,NULL,'Mounting Clip','Mounting Clip','JMPS198BFC','0216','Joimax','2019-10-14','--',1),(56,NULL,'Mounting Clip','Mounting Clip','JMPS198BFC','0216','Joimax','2019-10-14','--',1),(78,NULL,'Parallel Shaft','Parallel Shaft','JMPS1041ST','471C85','Joimax','2019-10-14','--',1),(79,NULL,'Parallel Shaft','Parallel Shaft','JMPS1041ST','471C85','Joimax','2019-10-14','--',1),(80,NULL,'Parallel Shaft','Parallel Shaft','JMPS1041ST','471C85','Joimax','2019-10-14','--',1),(81,NULL,'Parallel Shaft','Parallel Shaft','JMPS1041ST','471C85','Joimax','2019-10-14','--',1),(85,NULL,'test','test','test','test','test','2023-01-19','test',1),(86,NULL,'test','test','test','test','test','2023-01-11','test',1);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products_series`
--

DROP TABLE IF EXISTS `products_series`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products_series` (
  `id` int NOT NULL AUTO_INCREMENT,
  `product_series_qr_code` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products_series`
--

LOCK TABLES `products_series` WRITE;
/*!40000 ALTER TABLE `products_series` DISABLE KEYS */;
INSERT INTO `products_series` VALUES (99,'678'),(100,'699'),(101,'166'),(102,'78'),(104,'46'),(105,'888'),(106,'2222'),(107,'23'),(108,'34'),(120,'A011'),(121,'A999'),(122,'345A');
/*!40000 ALTER TABLE `products_series` ENABLE KEYS */;
UNLOCK TABLES;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-15 18:33:53
