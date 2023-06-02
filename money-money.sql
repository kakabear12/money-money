CREATE DATABASE  IF NOT EXISTS `money_money` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `money_money`;
-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: money_money
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `asset`
--

DROP TABLE IF EXISTS `asset`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `asset` (
  `asset_id` bigint NOT NULL AUTO_INCREMENT,
  `asset_name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `value` decimal(38,2) NOT NULL,
  `asset_type_id` bigint DEFAULT NULL,
  PRIMARY KEY (`asset_id`),
  KEY `FK4udnqglbxg2lpibsun3s0mtf6` (`asset_type_id`),
  CONSTRAINT `FK4udnqglbxg2lpibsun3s0mtf6` FOREIGN KEY (`asset_type_id`) REFERENCES `asset_type` (`asset_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asset`
--

LOCK TABLES `asset` WRITE;
/*!40000 ALTER TABLE `asset` DISABLE KEYS */;
INSERT INTO `asset` VALUES (1,'Tiền mặt trong túi','Số tiền mặt đang được giữ trong ví cá nhân',10000.00,1),(2,'Tiền tiết kiệm ngân hàng','Số tiền đã gửi vào tài khoản tiết kiệm ngân hàng với lãi suất ưu đãi.',10000.00,2),(3,'Thẻ ghi nợ','Thẻ liên kết với tài khoản ngân hàng, cho phép chủ thẻ rút tiền mặt và thanh toán mua sắm trực tuyến hoặc trực tiếp tại cửa hàng',10000.00,3),(4,'Thẻ tín dụng','Thẻ liên kết với tài khoản tín dụng, cho phép chủ thẻ mua sắm và thanh toán trước, sau đó hoàn trả theo khoản vay hàng tháng.',10000.00,3);
/*!40000 ALTER TABLE `asset` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `asset_type`
--

DROP TABLE IF EXISTS `asset_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `asset_type` (
  `asset_type_id` bigint NOT NULL AUTO_INCREMENT,
  `asset_type_name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`asset_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asset_type`
--

LOCK TABLES `asset_type` WRITE;
/*!40000 ALTER TABLE `asset_type` DISABLE KEYS */;
INSERT INTO `asset_type` VALUES (1,'Tiền mặt','Bao gồm số tiền mặt đang có trong ví hoặc tài khoản ngân hàng'),(2,'Tiết kiệm','Bao gồm số tiền đã gửi vào các tài khoản tiết kiệm ngân hàng để tích lũy lãi suất.'),(3,'Thẻ ngân hàng','Bao gồm các loại thẻ thanh toán liên kết với tài khoản ngân hàng, như thẻ ghi nợ và thẻ tín dụng');
/*!40000 ALTER TABLE `asset_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill`
--

DROP TABLE IF EXISTS `bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill` (
  `bill_id` bigint NOT NULL AUTO_INCREMENT,
  `amount` decimal(38,2) NOT NULL,
  `bill_name` varchar(255) NOT NULL,
  `due_date` date NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`bill_id`),
  KEY `FKqhq5aolak9ku5x5mx11cpjad9` (`user_id`),
  CONSTRAINT `FKqhq5aolak9ku5x5mx11cpjad9` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill`
--

LOCK TABLES `bill` WRITE;
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `budget`
--

DROP TABLE IF EXISTS `budget`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `budget` (
  `budget_id` bigint NOT NULL AUTO_INCREMENT,
  `budget_amount` decimal(10,2) NOT NULL,
  `budget_limit` decimal(13,2) NOT NULL,
  `category` varchar(255) NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`budget_id`),
  KEY `FKkuh8cj1roovp9nh6ut2igrxm2` (`user_id`),
  CONSTRAINT `FKkuh8cj1roovp9nh6ut2igrxm2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `budget`
--

LOCK TABLES `budget` WRITE;
/*!40000 ALTER TABLE `budget` DISABLE KEYS */;
/*!40000 ALTER TABLE `budget` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `budget_category`
--

DROP TABLE IF EXISTS `budget_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `budget_category` (
  `budget_category_id` bigint NOT NULL AUTO_INCREMENT,
  `amount` decimal(38,2) DEFAULT NULL,
  `category_name` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`budget_category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `budget_category`
--

LOCK TABLES `budget_category` WRITE;
/*!40000 ALTER TABLE `budget_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `budget_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `expense`
--

DROP TABLE IF EXISTS `expense`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `expense` (
  `expense_id` bigint NOT NULL AUTO_INCREMENT,
  `amount` decimal(38,2) DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `asset_id` bigint DEFAULT NULL,
  `expense_category_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`expense_id`),
  KEY `FKhlqihq7uyu04quhp65s8d6csx` (`asset_id`),
  KEY `FKhuho44ssbmqywb3nfmm4ljfmk` (`expense_category_id`),
  KEY `FK758h5dgdblrpwoaaycbmn29i0` (`user_id`),
  CONSTRAINT `FK758h5dgdblrpwoaaycbmn29i0` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKhlqihq7uyu04quhp65s8d6csx` FOREIGN KEY (`asset_id`) REFERENCES `asset` (`asset_id`),
  CONSTRAINT `FKhuho44ssbmqywb3nfmm4ljfmk` FOREIGN KEY (`expense_category_id`) REFERENCES `expense_category` (`expense_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expense`
--

LOCK TABLES `expense` WRITE;
/*!40000 ALTER TABLE `expense` DISABLE KEYS */;
INSERT INTO `expense` VALUES (2,4000.00,'2023-05-28 15:46:31.576000','Chơi game',1,2,NULL),(3,1000.00,'2023-04-28 15:50:36.941000','Đua thuyền ',4,2,NULL),(4,100000.00,'2023-05-30 15:17:15.074000','Mua bánh mì',1,1,1),(6,30000.00,'2023-05-28 23:43:08.868000','Cơm sườn',1,1,1),(8,100000.00,'2023-05-30 15:46:27.080000','Mua bánh mì',1,1,1),(10,35000.00,'2023-06-02 09:21:41.040000','Mua phở',1,1,1),(11,12000.00,'2023-06-02 17:00:02.359000','Mua red bull',1,1,1),(12,115000.00,'2023-06-01 18:55:59.366000','Mua mún đậu mắm tôm cho 3 đứa',1,1,1);
/*!40000 ALTER TABLE `expense` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `expense_category`
--

DROP TABLE IF EXISTS `expense_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `expense_category` (
  `expense_category_id` bigint NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`expense_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expense_category`
--

LOCK TABLES `expense_category` WRITE;
/*!40000 ALTER TABLE `expense_category` DISABLE KEYS */;
INSERT INTO `expense_category` VALUES (1,'Ăn uống'),(2,'Giải trí'),(3,'Giao thông vận tải'),(4,'Sinh hoạt'),(5,'Quần áo'),(6,'Giáo dục'),(7,'Sức Khỏe'),(8,'Khác');
/*!40000 ALTER TABLE `expense_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `income`
--

DROP TABLE IF EXISTS `income`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `income` (
  `income_id` bigint NOT NULL AUTO_INCREMENT,
  `amount` decimal(38,2) DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `income_category_id` bigint DEFAULT NULL,
  `asset_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`income_id`),
  KEY `FKexikice0carufifesu0q5iaqx` (`income_category_id`),
  KEY `FKqfmnmaecfqlrnka09q9tyfy6s` (`asset_id`),
  KEY `FK8qxqvv9oy66uuotjtfvqu1fxy` (`user_id`),
  CONSTRAINT `FK8qxqvv9oy66uuotjtfvqu1fxy` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKexikice0carufifesu0q5iaqx` FOREIGN KEY (`income_category_id`) REFERENCES `income_category` (`income_category_id`),
  CONSTRAINT `FKqfmnmaecfqlrnka09q9tyfy6s` FOREIGN KEY (`asset_id`) REFERENCES `asset` (`asset_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `income`
--

LOCK TABLES `income` WRITE;
/*!40000 ALTER TABLE `income` DISABLE KEYS */;
INSERT INTO `income` VALUES (3,100000000.00,'2023-05-30 15:28:38.978000','Nhận lương tháng 5',1,1,1),(4,100000000.00,'2023-05-30 15:41:18.098000','Nhận lương tháng 5',1,1,1),(5,100000000.00,'2023-05-30 17:05:28.533000','Lương cứng của công ty',1,1,2);
/*!40000 ALTER TABLE `income` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `income_category`
--

DROP TABLE IF EXISTS `income_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `income_category` (
  `income_category_id` bigint NOT NULL AUTO_INCREMENT,
  `income_category_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`income_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `income_category`
--

LOCK TABLES `income_category` WRITE;
/*!40000 ALTER TABLE `income_category` DISABLE KEYS */;
INSERT INTO `income_category` VALUES (1,'Tiền lương'),(2,'Tiền trợ cấp'),(3,'Tiền thưởng'),(4,'Tiền đầu tư'),(5,'Chứng khoán'),(6,'Cho thuê nhà'),(7,'Kinh doanh online'),(8,'Khác');
/*!40000 ALTER TABLE `income_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `password_reset_token`
--

DROP TABLE IF EXISTS `password_reset_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `password_reset_token` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `expiration_time` datetime(6) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_USER_PASSWORD_TOKEN` (`user_id`),
  CONSTRAINT `FK_USER_PASSWORD_TOKEN` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `password_reset_token`
--

LOCK TABLES `password_reset_token` WRITE;
/*!40000 ALTER TABLE `password_reset_token` DISABLE KEYS */;
/*!40000 ALTER TABLE `password_reset_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_gateway`
--

DROP TABLE IF EXISTS `payment_gateway`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_gateway` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `api_key` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `secret_key` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_gateway`
--

LOCK TABLES `payment_gateway` WRITE;
/*!40000 ALTER TABLE `payment_gateway` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment_gateway` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `premium_subscription`
--

DROP TABLE IF EXISTS `premium_subscription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `premium_subscription` (
  `subscription_id` bigint NOT NULL AUTO_INCREMENT,
  `end_date` datetime(6) NOT NULL,
  `start_date` datetime(6) NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`subscription_id`),
  KEY `FKdge3fakyt2ef18sfaqy4dh95d` (`user_id`),
  CONSTRAINT `FKdge3fakyt2ef18sfaqy4dh95d` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `premium_subscription`
--

LOCK TABLES `premium_subscription` WRITE;
/*!40000 ALTER TABLE `premium_subscription` DISABLE KEYS */;
/*!40000 ALTER TABLE `premium_subscription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `refresh_token`
--

DROP TABLE IF EXISTS `refresh_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `refresh_token` (
  `id` bigint NOT NULL,
  `expiry_date` datetime(6) NOT NULL,
  `token` varchar(255) NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_r4k4edos30bx9neoq81mdvwph` (`token`),
  KEY `FKfgk1klcib7i15utalmcqo7krt` (`user_id`),
  CONSTRAINT `FKfgk1klcib7i15utalmcqo7krt` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refresh_token`
--

LOCK TABLES `refresh_token` WRITE;
/*!40000 ALTER TABLE `refresh_token` DISABLE KEYS */;
INSERT INTO `refresh_token` VALUES (1,'2023-05-28 16:16:05.198114','691dee7462a30f4966652aa5e3a3a6d533e38c6d90c68d16036bb7550012cbb8',1),(2,'2023-05-28 16:35:04.730824','a4a07e265ccdfdb5207f6859abb23144746d659b79f5776eb6a3dfdd48b40cdc',1),(52,'2023-05-28 16:45:06.290586','cb90b3212676afa9ae000a780ce17e60dee0e350d36ef2b90465a599d9eda211',1),(102,'2023-05-28 16:52:49.125907','9050b7b89070612366374f2a7cb634cc92c70ab391b55a9e3903fc959957528d',1),(152,'2023-05-28 17:04:08.523513','7db8740a96df15403c3ebf465cc27103edc5a84cd4bdd351d204d7f9f89f147b',1),(202,'2023-05-28 17:12:38.039956','1f778554076f31bfe473d89f1448018bfc6c4a127a21bda6f912b48c6105584c',1),(252,'2023-05-30 08:46:37.728314','5df5296f7832faf72f62b03b03ca5006c709cca4fe46c43d39484aed782e1db9',1),(302,'2023-05-30 08:58:26.998496','fb7d81e4b1d712881e284ea36faa175986c5e80bf2a569538b545c10c7aeb016',1),(352,'2023-05-30 09:10:57.444083','da6ac1d86defa093b814d93313cd33c37d5a4d2637c081ea5d5b8868701345e8',1),(402,'2023-05-30 09:44:56.917311','d8225b0797fcee8de19bb4c4f11be6ef18b96ed73603f90c8b0d397f2599a24b',1),(452,'2023-05-30 10:30:12.355627','ab467f9f3a913baa4a10a96c0be44efb160d78489fad253a7f4d192055dbdd52',1),(453,'2023-05-30 10:30:34.529295','e24cd6f0901c33514deed3a0a12f24d5dff2ea7ca0a7a68d82c2e8c6ce565748',1),(454,'2023-05-30 10:33:55.938809','87808c846d287b73772338bbd1c5ef986ad24ec7dbf732b221298d6e72c5f560',2),(502,'2023-05-31 06:36:02.407559','8894ad91834ba9326209300a5dbba0346b7bf816966f0c87b24b088036e7edd5',1),(552,'2023-06-02 02:07:29.146020','b429ff1c69e19daedd73ca8aa3de36fd50e4ded2c1cce84a69a422b5236ddc40',3),(602,'2023-06-02 02:11:57.675284','8ec6cd9dc570315b85d37c38e898ba4ad02211c0898acaaa2cb741013451f05d',1),(652,'2023-06-02 02:16:39.047914','f24ee9d602426fbbf9446997e019d121bd143155e9f64f7f2239a3352427b7dd',1),(653,'2023-06-02 02:24:55.806196','e7d899052b7b4efb99b9bd37416ff678780d5eb3146540dc7f98b25d81f6bf44',3),(654,'2023-06-02 02:30:25.499906','8b59a694e83634729cc5df093b8cb71f43bfe173459ffd406bdf1ff3542eda7c',3),(702,'2023-06-02 02:33:39.697216','ca8af3cdcc40cbe06587e75a60d4594618773928f74e658b9d4b04da6db67b45',1),(752,'2023-06-02 02:41:45.448224','123528ba19c0857ff3aa91d139bbaa2f8e8d0460a350f0c2b902a02d06c943c4',1),(802,'2023-06-02 02:52:31.510839','1df3ff6047ba8201a959d8b249615958e3c5d7579c3c7c5de43363558d1a0f38',1),(852,'2023-06-02 02:55:59.809979','fb4e5f22cba2502ddefe94bf58dca0802fcadacde5df460564b25680b110fbc9',1),(902,'2023-06-02 10:27:43.872497','3e58b6a8c18862218a6c4dc98aea81115c49f74b2bcb6775f0fc97c07f824bc1',1),(952,'2023-06-02 10:56:15.219593','ce134439feb807f172009587e8b18688357e96f93e39067a4e3c985765ffbeb9',1),(953,'2023-06-02 11:07:15.391339','82ce3254d86e54d990ebd95bc04b6ad5107014d457aaa8ed6b69e220297aec76',1),(1002,'2023-06-02 12:17:27.194964','f77cc25a624b3d9aba85d73fde7c0f862e1b7ccc87b9c7bbb4812267390a45f5',1),(1003,'2023-06-02 12:18:44.837179','ca9832ed8d04928c68aa16fba0dd0d64abf9ee522a3330921d22b8d0fbbdeb85',1),(1052,'2023-06-02 12:24:45.976096','b8ce16bf0e7fa1fbd7be64875dfdef157c69c9c1dae35b55d6c5cb546bb3dc00',1),(1102,'2023-06-02 12:39:01.684009','eb06d101a660b1d160230b70d0c504c5c7e1bee44c889b2957f9c3c333962c95',1),(1152,'2023-06-02 13:16:14.959004','ebdb6ad03bb160c61be6783939530925777c9488a52f6a4ec772710a292bfd8d',1),(1202,'2023-06-02 13:24:22.603680','8e9b8e94c846a518700868690f62b29074dc91a7de6666f298850fd747b50b79',1),(1252,'2023-06-02 13:35:14.973821','cf17c1cb07fdb4bcb6c4033d22fec329468de2a0ea8209a7879b64bde129acec',1);
/*!40000 ALTER TABLE `refresh_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `refresh_token_seq`
--

DROP TABLE IF EXISTS `refresh_token_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `refresh_token_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refresh_token_seq`
--

LOCK TABLES `refresh_token_seq` WRITE;
/*!40000 ALTER TABLE `refresh_token_seq` DISABLE KEYS */;
INSERT INTO `refresh_token_seq` VALUES (1351);
/*!40000 ALTER TABLE `refresh_token_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `report` (
  `report_id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `report_data` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `report_type_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`report_id`),
  KEY `FK989tfinp1jxv79fvmju0cfpj3` (`report_type_id`),
  KEY `FKj62onw73yx1qnmd57tcaa9q3a` (`user_id`),
  CONSTRAINT `FK989tfinp1jxv79fvmju0cfpj3` FOREIGN KEY (`report_type_id`) REFERENCES `report_type` (`report_type_id`),
  CONSTRAINT `FKj62onw73yx1qnmd57tcaa9q3a` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report_type`
--

DROP TABLE IF EXISTS `report_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `report_type` (
  `report_type_id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `report_type_name` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`report_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report_type`
--

LOCK TABLES `report_type` WRITE;
/*!40000 ALTER TABLE `report_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `report_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `savings_goal`
--

DROP TABLE IF EXISTS `savings_goal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `savings_goal` (
  `goal_id` bigint NOT NULL AUTO_INCREMENT,
  `current_amount` decimal(38,2) NOT NULL,
  `goal_name` varchar(255) NOT NULL,
  `target_amount` decimal(38,2) NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`goal_id`),
  KEY `FKbyq4xhof3tuobca5a6kmv6qq8` (`user_id`),
  CONSTRAINT `FKbyq4xhof3tuobca5a6kmv6qq8` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `savings_goal`
--

LOCK TABLES `savings_goal` WRITE;
/*!40000 ALTER TABLE `savings_goal` DISABLE KEYS */;
/*!40000 ALTER TABLE `savings_goal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `enable` bit(1) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `password` varchar(60) NOT NULL,
  `role` smallint DEFAULT NULL,
  `asset_id` bigint DEFAULT NULL,
  `provider` varchar(255) NOT NULL,
  `provider_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `FK85iu4yrw5vmgjgutd2b4ft6a8` (`asset_id`),
  CONSTRAINT `FK85iu4yrw5vmgjgutd2b4ft6a8` FOREIGN KEY (`asset_id`) REFERENCES `asset` (`asset_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'quangvanpham02022001@gmail.com',_binary '','Van','Nguyen','$2a$11$4DACmtJKB0i9yr5.JnUyQOzF.nSwrO29yfw9cNG6n0y8UauD4yKlm',1,NULL,'local',NULL),(2,'vanpqse150505@fpt.edu.vn',_binary '','Quang van','Pham','$2a$11$c2pGytHvViE9B1tGbCYvrOSIm1UVIk9EGhyWvdza3HQf4K4SQLkI6',1,NULL,'local',NULL),(3,'phongkhamnhakhoathienthan@gmail.com',_binary '','Văn Phạm','Văn Phạm','$2a$11$lPu6Vi7WAVs/RScEjzYp2eyrRGXWxjyslqe/gMVxJayrgtJDK3s52',1,NULL,'google','105271564406848707613');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_documents`
--

DROP TABLE IF EXISTS `user_documents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_documents` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `document_name` varchar(255) NOT NULL,
  `document_path` varchar(255) NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1bk9akr19xluoygu4d4shpjgy` (`user_id`),
  CONSTRAINT `FK1bk9akr19xluoygu4d4shpjgy` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_documents`
--

LOCK TABLES `user_documents` WRITE;
/*!40000 ALTER TABLE `user_documents` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_documents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `verification_token`
--

DROP TABLE IF EXISTS `verification_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `verification_token` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `expiration_time` datetime(6) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_USER_VERIFI_TOKEN` (`user_id`),
  CONSTRAINT `FK_USER_VERIFI_TOKEN` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `verification_token`
--

LOCK TABLES `verification_token` WRITE;
/*!40000 ALTER TABLE `verification_token` DISABLE KEYS */;
INSERT INTO `verification_token` VALUES (1,'2023-05-28 22:55:03.012000','c3a31c5a-14b7-4659-823b-62d9c020b290',1),(2,'2023-05-30 17:12:40.181000','fcbab941-3546-4df8-8029-2ab85b377c2b',2),(3,'2023-06-02 08:46:36.679000','e9a1c73a-b428-42a2-9dcf-e7c1be6d15db',3);
/*!40000 ALTER TABLE `verification_token` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-02 20:17:59
