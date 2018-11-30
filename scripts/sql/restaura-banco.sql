-- MySQL dump 10.13  Distrib 5.7.19, for macos10.12 (x86_64)
--
-- Host: localhost    Database: promocity
-- ------------------------------------------------------
-- Server version	5.7.19

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
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
  KEY `id` (`id`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authorities`
--

LOCK TABLES `authorities` WRITE;
/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
INSERT INTO `authorities` VALUES ('armando','USER',1),('maria','USER',2),('francisco','USER',3),('carlos','USER',7),('vera','USER',8),('lucia','USER',9),('jota','USER',10),('david','USER',11),('tes','USER',12),('jose','USER',13),('ana','USER',14),('armando','ADMIN',15),('armando','STOREOWNER',16),('maria','STOREOWNER',17),('capitu','USER',18),('foca','USER',19),('foca','STOREOWNER',20),('josesilva','USER',21);
/*!40000 ALTER TABLE `authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coupon`
--

DROP TABLE IF EXISTS `coupon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coupon` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `discount` float NOT NULL,
  `qr_code` varchar(255) DEFAULT NULL,
  `activated` bit(1) NOT NULL,
  `consumed` bit(1) NOT NULL,
  `awarded` bit(1) NOT NULL,
  `limited_to_use` int(11) NOT NULL,
  `used` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupon`
--

LOCK TABLES `coupon` WRITE;
/*!40000 ALTER TABLE `coupon` DISABLE KEYS */;
INSERT INTO `coupon` VALUES (1,'O cupom teste do Juarez',80,'1022112233','','\0','',4,1),(2,'Cupom 2 da promocao 2',20,'20221122332','\0','\0','\0',4,0),(3,'Loucura BlackFriday do Boteco',75,'$2a$10$K4fc9zKfndqPsyrx/kbUj.VV4/0VpnXVvUpvaSKEY/b66QlwC/7zy','\0','\0','\0',4,0),(4,'Cupom da Maria',10,'$2a$10$Ac6O5Agym7oxjI2rFPVhkeR6R.rD/d34wdgvsevyiEFtrSu57rGXy','\0','\0','\0',4,0),(5,'Biquini pela metade do preço até o final de 2018',50,'$2a$10$73sAKZgrc.n6SzCpBD7nTeu1rivEqXmo4qPWIaMv8sGUjN5.PJjva','\0','\0','\0',4,0),(6,'Choppe quase de graça até o final do ano de 2018',50,'$2a$10$EnvoLyCdNncdixIQyEj2Pem5eIp6d9I9dNDmA4vk35Mif8AKkjzTG','\0','\0','\0',4,0),(7,'Cupom do Juarez',55,'$2a$10$q2wr3mbOf9fPfalfAkoY8uccNKC4FChDzzriqCAlLJJH9MsKizb6a','\0','\0','\0',4,0),(8,'Cupom do Great',10,'$2a$10$1jlfiMp5ex1UE9I.vZd40OlrkjzP7JUBKxs0u1F1wDlpruQzJGp3u','\0','\0','\0',4,0),(9,'Cupom da Química',20,'$2a$10$cK/sjM8y9oKhsQGhWKetteayvdPQ/QVggr9At53WTZec1I6KH1sLi','\0','\0','\0',4,0),(10,'Cupom da Duda',20,'$2a$10$gjmBcQxjh/QOwZBSCPCx4.vjQSBsPnRwN40csYY4A/SogQm9.IfE.','\0','\0','\0',4,0);
/*!40000 ALTER TABLE `coupon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coupon_users`
--

DROP TABLE IF EXISTS `coupon_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coupon_users` (
  `coupon_id` bigint(20) NOT NULL,
  `users_id` bigint(20) NOT NULL,
  KEY `FKsx7gwt8iu5nxwg6ut6nb4xga4` (`users_id`),
  KEY `FK9osek8je11eko8a17et4bddod` (`coupon_id`),
  CONSTRAINT `FK9osek8je11eko8a17et4bddod` FOREIGN KEY (`coupon_id`) REFERENCES `coupon` (`id`),
  CONSTRAINT `FKsx7gwt8iu5nxwg6ut6nb4xga4` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupon_users`
--

LOCK TABLES `coupon_users` WRITE;
/*!40000 ALTER TABLE `coupon_users` DISABLE KEYS */;
/*!40000 ALTER TABLE `coupon_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customers` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `added_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `email` varchar(255) NOT NULL,
  `firstname` varchar(40) NOT NULL,
  `lastname` varchar(40) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_rfbvkrffamfql7cjmen8v976v` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `my_stores`
--

DROP TABLE IF EXISTS `my_stores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `my_stores` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3bexvrd7jqwscshcimd33r2vx` (`user_id`),
  CONSTRAINT `FK3bexvrd7jqwscshcimd33r2vx` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `my_stores`
--

LOCK TABLES `my_stores` WRITE;
/*!40000 ALTER TABLE `my_stores` DISABLE KEYS */;
INSERT INTO `my_stores` VALUES (1,2),(2,17);
/*!40000 ALTER TABLE `my_stores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `my_stores_store_list`
--

DROP TABLE IF EXISTS `my_stores_store_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `my_stores_store_list` (
  `my_stores_id` bigint(20) NOT NULL,
  `store_list_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_hqsmj92qvrsu20nvjv42gjwgr` (`store_list_id`),
  KEY `FK1fbji8bf4ic83ems7dvxa7mcr` (`my_stores_id`),
  CONSTRAINT `FK1fbji8bf4ic83ems7dvxa7mcr` FOREIGN KEY (`my_stores_id`) REFERENCES `my_stores` (`id`),
  CONSTRAINT `FK2ycadl6ruglvsydm1n0ld463r` FOREIGN KEY (`store_list_id`) REFERENCES `store` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `my_stores_store_list`
--

LOCK TABLES `my_stores_store_list` WRITE;
/*!40000 ALTER TABLE `my_stores_store_list` DISABLE KEYS */;
INSERT INTO `my_stores_store_list` VALUES (1,12),(1,13),(2,14);
/*!40000 ALTER TABLE `my_stores_store_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `my_tracking`
--

DROP TABLE IF EXISTS `my_tracking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `my_tracking` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqghnho8hu0ugawgen47nwj3ms` (`user_id`),
  CONSTRAINT `FKqghnho8hu0ugawgen47nwj3ms` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `my_tracking`
--

LOCK TABLES `my_tracking` WRITE;
/*!40000 ALTER TABLE `my_tracking` DISABLE KEYS */;
INSERT INTO `my_tracking` VALUES (1,1),(4,1),(7,1),(10,1),(13,1),(2,2),(5,2),(8,2),(11,2),(14,2),(3,3),(6,3),(9,3),(12,3),(15,3);
/*!40000 ALTER TABLE `my_tracking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `my_tracking_tracking_list`
--

DROP TABLE IF EXISTS `my_tracking_tracking_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `my_tracking_tracking_list` (
  `my_tracking_id` bigint(20) NOT NULL,
  `tracking_list_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_fkvcbm06htyhu342d24gcd8so` (`tracking_list_id`),
  KEY `FKjvbux8ppj31wokw1i7mclm28g` (`my_tracking_id`),
  CONSTRAINT `FKbelwpul2c7qvpixqq92n2r6b7` FOREIGN KEY (`tracking_list_id`) REFERENCES `track` (`id`),
  CONSTRAINT `FKjvbux8ppj31wokw1i7mclm28g` FOREIGN KEY (`my_tracking_id`) REFERENCES `my_tracking` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `my_tracking_tracking_list`
--

LOCK TABLES `my_tracking_tracking_list` WRITE;
/*!40000 ALTER TABLE `my_tracking_tracking_list` DISABLE KEYS */;
INSERT INTO `my_tracking_tracking_list` VALUES (1,1),(2,2),(3,3),(4,4),(5,5),(6,6),(7,7),(8,8),(9,9),(10,10),(11,11),(12,12),(13,13),(14,14),(15,15);
/*!40000 ALTER TABLE `my_tracking_tracking_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promotion`
--

DROP TABLE IF EXISTS `promotion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `promotion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `from_date` datetime DEFAULT NULL,
  `to_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promotion`
--

LOCK TABLES `promotion` WRITE;
/*!40000 ALTER TABLE `promotion` DISABLE KEYS */;
INSERT INTO `promotion` VALUES (1,'Promocao 1','2018-11-22 00:00:00','2018-11-23 00:00:00'),(2,'Promocao 2','2018-11-23 00:00:00','2018-11-24 00:00:00'),(3,'BlackFriday','2018-11-23 00:00:00','2018-11-23 00:00:00'),(4,'Promoção da Maria','2018-12-01 00:00:00','2018-12-31 00:00:00'),(5,'Moda praia de final de ano teste','2018-12-01 00:00:00','2018-12-31 00:00:00'),(6,'Promoção Maria Moda Praia 2018','2018-12-01 00:00:00','2018-12-31 00:00:00'),(7,'Chopp pela metade do preço até o final do ano de 2018','2018-12-01 00:00:00','2018-12-31 00:00:00'),(8,'PromoTeste','2018-12-01 00:00:00','2018-12-31 00:00:00'),(9,'PromoGREAT','2018-12-01 00:00:00','2018-12-31 00:00:00'),(10,'PromoQuímica 2018','2018-12-01 00:00:00','2018-12-31 00:00:00'),(11,'Duda na UFC','2018-12-01 00:00:00','2018-12-31 00:00:00');
/*!40000 ALTER TABLE `promotion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promotion_coupons`
--

DROP TABLE IF EXISTS `promotion_coupons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `promotion_coupons` (
  `promotion_id` bigint(20) NOT NULL,
  `coupons_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_kl698wh1vgxrnxu8xxthjd839` (`coupons_id`),
  KEY `FKmeta70hdsrem0dsyvauc4b4iu` (`promotion_id`),
  CONSTRAINT `FKmeta70hdsrem0dsyvauc4b4iu` FOREIGN KEY (`promotion_id`) REFERENCES `promotion` (`id`),
  CONSTRAINT `FKtnamnuubbu9r6ydkaamt2h83p` FOREIGN KEY (`coupons_id`) REFERENCES `coupon` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promotion_coupons`
--

LOCK TABLES `promotion_coupons` WRITE;
/*!40000 ALTER TABLE `promotion_coupons` DISABLE KEYS */;
INSERT INTO `promotion_coupons` VALUES (1,1),(2,2),(3,3),(4,4),(6,5),(7,6),(8,7),(9,8),(10,9),(11,10);
/*!40000 ALTER TABLE `promotion_coupons` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `store`
--

DROP TABLE IF EXISTS `store`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `store` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `radius` double NOT NULL,
  `state` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `store`
--

LOCK TABLES `store` WRITE;
/*!40000 ALTER TABLE `store` DISABLE KEYS */;
INSERT INTO `store` VALUES (1,'Rua Teste 1','Cidade teste1',0,0,'Loja 1',0,'PI'),(2,'Rua Teste','Fortaleza',-3.7260311,-38.5047165,'Boteco Praia',0,'Ceará'),(3,'Rua Teste','Fortaleza',-3.7241736,-38.5042015,'Habibs Praia de Iracema',0,'Ceará'),(4,'Rua Teste','Fortaleza',-3.7244947,-38.5028738,'Café Vida',0,'Ceará'),(5,'Rua Teste','Fortaleza',-3.7261007,-38.5022274,'Koni Street Japanese',0,'Ceará'),(6,'Rua Teste','Fortaleza',-3.7255493,-38.499395,'Acarajé Cia',0,'Ceará'),(7,'Rua Teste','Fortaleza',-3.7255493,-38.499395,'Barraca da Boa',0,'Ceará'),(8,'Rua Teste','Fortaleza',-3.7267484,-38.4984938,'Didi Rei dos Mares',0,'Ceará'),(9,'Rua Teste','Fortaleza',-3.7267484,-38.4995881,'Sabor de Mar',0,'Ceará'),(10,'Rua Teste','Fortaleza',-3.7283864,-38.4974209,'Bistrô Garrafeira',0,'Ceará'),(11,'Rua Teste','Fortaleza',-3.7283864,-38.4974209,'Empório Delitalia',0,'Ceará'),(12,'Rua Teste','Fortaleza',0,0,'Loja da Maria editada',0,'Ceará'),(13,'teste','Teresina',0,0,'Moda Maria',0,'Piauí'),(14,'Avenida Jatiúca','Maceió',0,0,'Foca Bear',0,'Alagoas'),(15,'Campus do Pici','Fortaleza',-3.7465646,-38.5780218,'Great',1,'Ceará'),(16,'Campus do Pici','Fortaleza',-3.7464669,-38.5779165,'Cantina da Química',1,'Ceará'),(17,'Campus do Pici','Fortaleza',-3.7457293,-38.5730363,'Lanchonete Alfa e Ômega',1,'Ceará'),(18,'Campus do Pici','Fortaleza',-3.7420005,-38.5759716,'Cantina da Engenharia de Pesca',1,'Ceará'),(19,'Campus do Pici','Fortaleza',-3.7392888,-38.5713145,'Dudes+Lanches',1,'Ceará');
/*!40000 ALTER TABLE `store` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `store_promotion_list`
--

DROP TABLE IF EXISTS `store_promotion_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `store_promotion_list` (
  `store_id` bigint(20) NOT NULL,
  `promotion_list_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_g9325mc18cl3pkditraj68p32` (`promotion_list_id`),
  KEY `FKjguij8k8bel1kapuml36ko3si` (`store_id`),
  CONSTRAINT `FKjguij8k8bel1kapuml36ko3si` FOREIGN KEY (`store_id`) REFERENCES `store` (`id`),
  CONSTRAINT `FKjryie9fd9stdvuywc1julxt2c` FOREIGN KEY (`promotion_list_id`) REFERENCES `promotion` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `store_promotion_list`
--

LOCK TABLES `store_promotion_list` WRITE;
/*!40000 ALTER TABLE `store_promotion_list` DISABLE KEYS */;
INSERT INTO `store_promotion_list` VALUES (1,1),(1,2),(1,8),(2,3),(12,4),(13,6),(14,7),(15,9),(16,10),(19,11);
/*!40000 ALTER TABLE `store_promotion_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `track`
--

DROP TABLE IF EXISTS `track`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `track` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `day` datetime DEFAULT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `track`
--

LOCK TABLES `track` WRITE;
/*!40000 ALTER TABLE `track` DISABLE KEYS */;
INSERT INTO `track` VALUES (1,'2018-11-29 22:00:25',-3.7392711,-38.6052945),(2,'2018-11-29 22:00:25',-3.7392711,-38.6052945),(3,'2018-11-29 22:00:25',-3.7392711,-38.6052945),(4,'2018-11-29 22:03:14',0,0),(5,'2018-11-29 22:03:15',0,0),(6,'2018-11-29 22:03:15',0,0),(7,'2018-11-29 22:39:58',0,0),(8,'2018-11-29 22:39:59',0,0),(9,'2018-11-29 22:39:59',0,0),(10,'2018-11-29 22:59:07',0,0),(11,'2018-11-29 22:59:07',0,0),(12,'2018-11-29 22:59:07',0,0),(13,'2018-11-30 07:47:18',0,0),(14,'2018-11-30 07:47:18',0,0),(15,'2018-11-30 07:47:19',0,0);
/*!40000 ALTER TABLE `track` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `completename` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('ana','$2a$10$xEyUW9wNFDWcjdvo4orYgeWYWIEUTR0KweCM7jXbfytEDwsuMXyTK',1,15,'ana@gmail.com',0,0,'Ana Maria Braga'),('armando','$2a$10$G21Awy3udH2acuuY3IqTPeJRT5QnuUGCVMydgLLZbjLMjFVJa9O12',1,1,'armando@ufpi.edu.br',0,0,'Armando Soares Sousa'),('capitu','$2a$10$oEaZHj67GPXkR/khMmdgjeMITD7L8dz80IDPtjzKK6kwT2rt2cbGW',1,16,'capitu@gmail.com',0,0,'Maria Capitulina de Amaral'),('carlos','$2a$10$ZxkTB./ZdO92fVoGRkwY4OUEk6uxUTnfB/iRSg1iqzBemMARGVpnu',1,8,'carlos@gmail.com',0,0,'Carlos Nascimento Sampaio'),('david','$2a$10$3gLNDikivHcF0.HcffucOusIgURSRzeTjAkKaUXGHFjIjOItXF1uO',1,12,'dcf@gm.com',0,0,'David Coper Field'),('foca','$2a$10$/.Yfv.iLdBcFg5EiecWgY.gsedEHyM/pyB.KwDkyc8KYNgtucxGcS',1,17,'foca@gmail.cofm',0,0,'Foca da Silva Maramelo'),('Francisco','$2a$10$vKnTJGs54wwOlDXB6QsAxOO3RxK.OSSq8uJLZKjxJ7kd7lg.jhVG2',1,3,'francisco@uol.com.br',0,0,'Francisco da Silva Sauro'),('joao','$2a$10$hRJ0e4Ix3O4GKvryi3h3.urzxFwvOvuxlsbLKKJKPCrx8VJfqeNSO',1,4,'joao@gmail.com',0,0,NULL),('jose','$2a$10$eDKIyxHjiQfQqrjKO9MWnu8spMCmanXWneP72ypm0ShnmxasLfNgO',1,14,'jn@gma.com',0,0,'Jose do Nascimento'),('josesilva','$2a$10$4rvNGVW4ExPT/NEO//U2BeI4UWRKnsUwN0E5MeQR5qXPBCnl54Y3a',1,18,'jose@gmail.com',0,0,'Jose da Silva'),('jota','$2a$10$JNwqetWQWIINWs6QWr0bm.Qe/XTIUNeK2pvYVZdiOc18cAZFzBVv2',1,11,'jotaq@gm.com',0,0,'jota quest'),('lucia','$2a$10$rXgktQhChzxnxelJWGQBEeyczBKGZxGvt7n3ITFuTZQCFDW2U2LVS',1,10,'lbm@gmail.com',0,0,'Lucia Braga de Melo'),('maria','$2a$10$cfYZ4rJn1cl4P2XlYkgZxe7aUa1GHj4ku6Vexi.bxdnxxfldx/7LS',1,2,'maria@gmail.com',0,0,'Maria Joaquina de Amaral Pereira Goes'),('tes','$2a$10$TdQU8ETpFfYdEUDKxx/TmOkbSwPXGwVP2kon1e74b8ieqCy3qjbDW',1,13,'tersaliaamaral@gmail.com',0,0,'Tersalia do Amaral'),('vera','$2a$10$U3BspMHOLefjDFzGmyIYo.Woti72slfkPxEsm57V/HxmHDfTbGpsi',1,9,'vera@gmail.com',0,0,'vera');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_coupon`
--

DROP TABLE IF EXISTS `users_coupon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_coupon` (
  `users_id` bigint(20) NOT NULL,
  `coupon_id` bigint(20) NOT NULL,
  KEY `FKtgpybu7sib8s1whjjgb8c6yrn` (`coupon_id`),
  KEY `FKhkgpo86cdn4hwbg7j8otkci6y` (`users_id`),
  CONSTRAINT `FKhkgpo86cdn4hwbg7j8otkci6y` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKtgpybu7sib8s1whjjgb8c6yrn` FOREIGN KEY (`coupon_id`) REFERENCES `coupon` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_coupon`
--

LOCK TABLES `users_coupon` WRITE;
/*!40000 ALTER TABLE `users_coupon` DISABLE KEYS */;
/*!40000 ALTER TABLE `users_coupon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_id_friends_list`
--

DROP TABLE IF EXISTS `users_id_friends_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_id_friends_list` (
  `users_id` bigint(20) NOT NULL,
  `id_friends_list_id` bigint(20) NOT NULL,
  KEY `FKp11f0yltpucd9ixa4bu6fs5cc` (`id_friends_list_id`),
  KEY `FK2aeyr0g8968dlig9qtkrqwvcl` (`users_id`),
  CONSTRAINT `FK2aeyr0g8968dlig9qtkrqwvcl` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKp11f0yltpucd9ixa4bu6fs5cc` FOREIGN KEY (`id_friends_list_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_id_friends_list`
--

LOCK TABLES `users_id_friends_list` WRITE;
/*!40000 ALTER TABLE `users_id_friends_list` DISABLE KEYS */;
/*!40000 ALTER TABLE `users_id_friends_list` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-30  8:04:40
