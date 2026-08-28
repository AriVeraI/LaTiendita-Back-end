-- MySQL dump 10.13  Distrib 8.0.46, for Win64 (x86_64)
--
-- Host: localhost    Database: la_yeya
-- ------------------------------------------------------
-- Server version	8.0.46

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
-- Current Database: `la_yeya`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `la_yeya` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `la_yeya`;

--
-- Table structure for table `carrito`
--

DROP TABLE IF EXISTS `carrito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carrito` (
  `id_carrito` int NOT NULL AUTO_INCREMENT,
  `fecha_creacion` datetime NOT NULL,
  `fecha_actualizacion` datetime NOT NULL,
  `usuarios_id_usuario` int NOT NULL,
  PRIMARY KEY (`id_carrito`,`usuarios_id_usuario`),
  KEY `fk_carrito_usuarios1_idx` (`usuarios_id_usuario`),
  CONSTRAINT `fk_carrito_usuarios1` FOREIGN KEY (`usuarios_id_usuario`) REFERENCES `usuarios` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carrito`
--

LOCK TABLES `carrito` WRITE;
/*!40000 ALTER TABLE `carrito` DISABLE KEYS */;
INSERT INTO `carrito` VALUES (1,'2026-06-01 10:30:00','2026-06-01 10:30:00',1),(2,'2026-06-05 14:15:00','2026-06-06 09:20:00',2),(3,'2026-06-10 16:45:00','2026-06-10 16:45:00',3),(4,'2026-06-12 11:00:00','2026-06-13 12:10:00',4),(5,'2026-06-15 08:20:00','2026-06-15 08:20:00',5);
/*!40000 ALTER TABLE `carrito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carrito_productos`
--

DROP TABLE IF EXISTS `carrito_productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carrito_productos` (
  `id_carrito_productos` bigint NOT NULL AUTO_INCREMENT,
  `cantidad` int NOT NULL,
  `productos_id_productos` int NOT NULL,
  `carrito_id_carrito` int NOT NULL,
  PRIMARY KEY (`id_carrito_productos`,`productos_id_productos`,`carrito_id_carrito`),
  KEY `fk_carrito_productos_productos1_idx` (`productos_id_productos`),
  KEY `fk_carrito_productos_carrito1_idx` (`carrito_id_carrito`),
  CONSTRAINT `fk_carrito_productos_carrito1` FOREIGN KEY (`carrito_id_carrito`) REFERENCES `carrito` (`id_carrito`),
  CONSTRAINT `fk_carrito_productos_productos1` FOREIGN KEY (`productos_id_productos`) REFERENCES `productos` (`id_productos`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carrito_productos`
--

LOCK TABLES `carrito_productos` WRITE;
/*!40000 ALTER TABLE `carrito_productos` DISABLE KEYS */;
INSERT INTO `carrito_productos` VALUES (1,1,4,1),(2,2,5,1),(3,1,1,1),(4,1,1,2),(5,1,3,2),(6,2,5,2),(7,2,2,3),(8,1,4,3),(9,1,5,3),(10,2,1,4),(11,1,2,4),(12,1,3,4),(13,1,4,4),(14,1,3,5),(15,3,5,5);
/*!40000 ALTER TABLE `carrito_productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categorias`
--

DROP TABLE IF EXISTS `categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categorias` (
  `id_categoria` int NOT NULL AUTO_INCREMENT,
  `nombre_categoria` varchar(45) NOT NULL,
  `slug` varchar(45) NOT NULL,
  PRIMARY KEY (`id_categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorias`
--

LOCK TABLES `categorias` WRITE;
/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
INSERT INTO `categorias` VALUES (1,'Joyeria editada','joyeria-editada'),(2,'Accesorios','accesorios'),(3,'Complementos','complementos'),(4,'Cuidado','cuidado'),(5,'General','general'),(7,'Prueba','prueba');
/*!40000 ALTER TABLE `categorias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `configuracion_tienda`
--

DROP TABLE IF EXISTS `configuracion_tienda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `configuracion_tienda` (
  `id` int NOT NULL,
  `nombre_tienda` varchar(100) NOT NULL,
  `correo_contacto` varchar(100) NOT NULL,
  `mensaje_bienvenida` varchar(255) DEFAULT NULL,
  `instagram` varchar(100) DEFAULT NULL,
  `tiktok` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuracion_tienda`
--

LOCK TABLES `configuracion_tienda` WRITE;
/*!40000 ALTER TABLE `configuracion_tienda` DISABLE KEYS */;
INSERT INTO `configuracion_tienda` VALUES (1,'La Tienda de la Yeya','hola@latienditadelayeya.com','Hola, Bienvenid@. Bisutería temática y única','latienditadelayeya','latienditadelayeya');
/*!40000 ALTER TABLE `configuracion_tienda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_pedido`
--

DROP TABLE IF EXISTS `detalle_pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalle_pedido` (
  `id_detalle_pedido` bigint NOT NULL AUTO_INCREMENT,
  `cantidad` int NOT NULL,
  `precio_total_unitario` decimal(10,2) NOT NULL,
  `pedidos_id_pedidos` int NOT NULL,
  `productos_id_productos` int NOT NULL,
  PRIMARY KEY (`id_detalle_pedido`,`pedidos_id_pedidos`,`productos_id_productos`),
  KEY `fk_detalle_pedido_productos1_idx` (`productos_id_productos`),
  KEY `fk_detalle_pedido_pedidos1_idx` (`pedidos_id_pedidos`),
  CONSTRAINT `fk_detalle_pedido_pedidos1` FOREIGN KEY (`pedidos_id_pedidos`) REFERENCES `pedidos` (`id_pedidos`),
  CONSTRAINT `fk_detalle_pedido_productos1` FOREIGN KEY (`productos_id_productos`) REFERENCES `productos` (`id_productos`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_pedido`
--

LOCK TABLES `detalle_pedido` WRITE;
/*!40000 ALTER TABLE `detalle_pedido` DISABLE KEYS */;
INSERT INTO `detalle_pedido` VALUES (1,1,120.00,1,1),(2,2,120.00,1,2),(3,1,180.00,1,3),(4,1,120.00,2,2),(5,1,180.00,2,3),(6,1,110.00,2,4),(7,2,60.00,2,5),(8,2,120.00,3,1),(9,1,120.00,3,2),(10,2,180.00,3,3),(11,1,110.00,3,4),(12,1,60.00,3,5),(13,1,120.00,4,1),(14,2,110.00,4,4),(15,3,60.00,4,5),(16,3,120.00,5,2),(17,1,110.00,5,4),(18,2,60.00,5,5),(19,1,120.00,6,1),(20,1,180.00,7,3),(21,1,180.00,8,3),(22,2,699.00,9,7),(23,1,60.00,10,5),(24,1,699.00,11,7),(25,1,699.00,12,7),(26,1,699.00,13,7);
/*!40000 ALTER TABLE `detalle_pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `direcciones`
--

DROP TABLE IF EXISTS `direcciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `direcciones` (
  `id_direccion` bigint NOT NULL AUTO_INCREMENT,
  `calle` varchar(45) NOT NULL,
  `numero` varchar(10) NOT NULL,
  `colonia` varchar(45) NOT NULL,
  `ciudad` varchar(25) NOT NULL,
  `estado` varchar(45) NOT NULL,
  `codigo_postal` varchar(45) NOT NULL,
  `usuarios_id_usuario` int NOT NULL,
  PRIMARY KEY (`id_direccion`,`usuarios_id_usuario`),
  KEY `fk_direcciones_usuarios_idx` (`usuarios_id_usuario`),
  CONSTRAINT `fk_direcciones_usuarios` FOREIGN KEY (`usuarios_id_usuario`) REFERENCES `usuarios` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `direcciones`
--

LOCK TABLES `direcciones` WRITE;
/*!40000 ALTER TABLE `direcciones` DISABLE KEYS */;
INSERT INTO `direcciones` VALUES (1,'Av. Paseo de los Leones','1500','Cumbres 1er Sector','Monterrey','Nuevo León','64610',1),(2,'Av. Universidad','101','Universidad','San Nicolás','Nuevo León','66455',2),(3,'Ruiz Cortines','3452','Mitras Centro','Monterrey','Nuevo León','64460',3),(4,'Calle Morelos','120-B','Centro','Monterrey','Nuevo León','64000',4),(5,'Blvd. Antonio L. Rdz.','405','San Jerónimo','Monterrey','Nuevo León','64640',5),(6,'Av. Prueba','0','Olimpica','Av. Prueba 123','Naucalpan','12345',6),(7,'Av. Prueba','0','Olimpica','Av. Prueba 123','Naucalpan','53690',6),(8,'Antonio Roldad','100','Radio','Naucalpan de Juarez','Mexico','53000',9),(9,'Del Silencio','13','Juarez','Tlalnepantla','Mexico','49710',6),(10,'Antonio','90','Juarez','Naucalpan de Juárez','Méx.','53690',7),(11,'Silencio','14','Nueva','Ciudad de Mexico','cdmx','52897',11),(12,'Noche Triste','455','Juarez','Ciudad de Mexico','cdmx','98552',13);
/*!40000 ALTER TABLE `direcciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `envios`
--

DROP TABLE IF EXISTS `envios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `envios` (
  `id_envios` bigint NOT NULL AUTO_INCREMENT,
  `paqueteria` varchar(45) NOT NULL,
  `numero_rastreo` varchar(45) NOT NULL,
  `estado_envio` varchar(45) NOT NULL,
  `fecha_despacho` datetime NOT NULL,
  `fecha_entrega_estimada` varchar(45) NOT NULL,
  `pedidos_id_pedidos` int NOT NULL,
  PRIMARY KEY (`id_envios`,`pedidos_id_pedidos`),
  KEY `fk_envios_pedidos1_idx` (`pedidos_id_pedidos`),
  CONSTRAINT `fk_envios_pedidos1` FOREIGN KEY (`pedidos_id_pedidos`) REFERENCES `pedidos` (`id_pedidos`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `envios`
--

LOCK TABLES `envios` WRITE;
/*!40000 ALTER TABLE `envios` DISABLE KEYS */;
INSERT INTO `envios` VALUES (1,'FedEx','FDX-12345','En tránsito','2026-06-03 09:00:00','2026-06-05',1),(2,'DHL','DHL-98765','Preparando','2026-06-07 10:00:00','2026-06-10',2),(3,'Estafeta','EST-44556','Entregado','2026-06-12 08:30:00','2026-06-14',3),(4,'Mercado Envíos','MEL-99887','Entregado','2026-06-14 11:00:00','2026-06-16',4),(5,'Redpack','RDP-11223','Pendiente','2026-06-17 09:00:00','2026-06-20',5),(6,'Envío estándar','YEYA-21942','Preparando','2026-08-27 05:35:16','2026-08-31',7),(7,'Envío estándar','YEYA-69183','Preparando','2026-08-27 17:00:18','2026-09-01',8),(8,'Envío estándar','YEYA-66989','En tránsito','2026-08-27 17:47:42','2026-09-01',9),(9,'Envío estándar','YEYA-35822','Preparando','2026-08-27 19:25:21','2026-09-01',10),(10,'Envío estándar','YEYA-59369','Preparando','2026-08-27 21:02:06','2026-09-01',11),(11,'Envío estándar','YEYA-18682','Preparando','2026-08-27 21:32:21','2026-09-01',12),(12,'Envío estándar','YEYA-28583','Preparando','2026-08-27 22:59:47','2026-09-01',13);
/*!40000 ALTER TABLE `envios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imagenes_productos`
--

DROP TABLE IF EXISTS `imagenes_productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `imagenes_productos` (
  `id_imagenes_productos` bigint NOT NULL AUTO_INCREMENT,
  `url_imagen` varchar(50) NOT NULL,
  `productos_id_productos` int NOT NULL,
  PRIMARY KEY (`id_imagenes_productos`,`productos_id_productos`),
  KEY `fk_imagenes_productos_productos1_idx` (`productos_id_productos`),
  CONSTRAINT `fk_imagenes_productos_productos1` FOREIGN KEY (`productos_id_productos`) REFERENCES `productos` (`id_productos`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imagenes_productos`
--

LOCK TABLES `imagenes_productos` WRITE;
/*!40000 ALTER TABLE `imagenes_productos` DISABLE KEYS */;
INSERT INTO `imagenes_productos` VALUES (1,'../../assets/Images/collarabeja.png',1),(2,'../../assets/Images/A1.png',2),(3,'../../assets/Images/collarazul.png',3),(4,'../../assets/Images/A4.png',4),(5,'../../assets/Images/An1.png',5),(6,'../../assets/Images/collarabeja.png',7),(9,'../../assets/Images/collarabeja.png',9);
/*!40000 ALTER TABLE `imagenes_productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagos`
--

DROP TABLE IF EXISTS `pagos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pagos` (
  `id_pagos` bigint NOT NULL AUTO_INCREMENT,
  `metodo_pago` varchar(45) NOT NULL,
  `monto` decimal(10,2) NOT NULL,
  `fecha_pago` datetime NOT NULL,
  `pedidos_id_pedidos` int NOT NULL,
  PRIMARY KEY (`id_pagos`,`pedidos_id_pedidos`),
  KEY `fk_pagos_pedidos1_idx` (`pedidos_id_pedidos`),
  CONSTRAINT `fk_pagos_pedidos1` FOREIGN KEY (`pedidos_id_pedidos`) REFERENCES `pedidos` (`id_pedidos`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagos`
--

LOCK TABLES `pagos` WRITE;
/*!40000 ALTER TABLE `pagos` DISABLE KEYS */;
INSERT INTO `pagos` VALUES (1,'Tarjeta de Crédito',540.00,'2026-06-02 12:05:00',1),(2,'PayPal',530.00,'2026-06-06 10:05:00',2),(3,'Transferencia',890.00,'2026-06-11 09:35:00',3),(4,'Tarjeta de Débito',520.00,'2026-06-13 14:05:00',4),(5,'OXXO',590.00,'2026-06-16 12:00:00',5),(6,'Tarjeta simulada',180.00,'2026-08-27 05:35:16',7),(7,'Tarjeta simulada',180.00,'2026-08-27 17:00:18',8),(8,'Tarjeta simulada',1398.00,'2026-08-27 17:47:42',9),(9,'Mercado Pago simulado',60.00,'2026-08-27 19:25:21',10),(10,'PayPal simulado',699.00,'2026-08-27 21:02:06',11),(11,'Mercado Pago simulado',699.00,'2026-08-27 21:32:21',12),(12,'PayPal simulado',699.00,'2026-08-27 22:59:47',13);
/*!40000 ALTER TABLE `pagos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedidos`
--

DROP TABLE IF EXISTS `pedidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedidos` (
  `id_pedidos` int NOT NULL AUTO_INCREMENT,
  `numero_pedido` int NOT NULL,
  `total` decimal(10,2) NOT NULL,
  `estado_pedido` varchar(45) NOT NULL,
  `fecha_creacion_pedido` datetime NOT NULL,
  `usuarios_id_usuario` int NOT NULL,
  PRIMARY KEY (`id_pedidos`,`usuarios_id_usuario`),
  KEY `fk_pedidos_usuarios1_idx` (`usuarios_id_usuario`),
  CONSTRAINT `fk_pedidos_usuarios1` FOREIGN KEY (`usuarios_id_usuario`) REFERENCES `usuarios` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedidos`
--

LOCK TABLES `pedidos` WRITE;
/*!40000 ALTER TABLE `pedidos` DISABLE KEYS */;
INSERT INTO `pedidos` VALUES (1,1001,540.00,'Procesando','2026-06-02 12:00:00',1),(2,1002,530.00,'Pagado','2026-06-06 10:00:00',2),(3,1003,890.00,'Enviado','2026-06-11 09:30:00',3),(4,1004,520.00,'Entregado','2026-06-13 14:00:00',4),(5,1005,590.00,'Pendiente','2026-06-16 11:15:00',5),(6,97578,120.00,'Procesando','2026-08-27 05:02:02',6),(7,21942,180.00,'Pagado','2026-08-27 05:35:15',6),(8,69183,180.00,'Pagado','2026-08-27 17:00:18',6),(9,66989,1398.00,'Enviado','2026-08-27 17:47:42',9),(10,35822,60.00,'Procesando','2026-08-27 19:25:21',6),(11,59369,699.00,'Procesando','2026-08-27 21:02:06',7),(12,18682,699.00,'Procesando','2026-08-27 21:32:21',11),(13,28583,699.00,'Procesando','2026-08-27 22:59:47',13);
/*!40000 ALTER TABLE `pedidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productos` (
  `id_productos` int NOT NULL AUTO_INCREMENT,
  `sku` varchar(10) NOT NULL,
  `nombre_producto` varchar(45) NOT NULL,
  `descripcion_producto` varchar(150) NOT NULL,
  `precio_producto` decimal(10,2) NOT NULL,
  `stock` int NOT NULL,
  `disponibilidad` varchar(10) NOT NULL,
  PRIMARY KEY (`id_productos`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (1,'COL-BOF-01','Boys Over Flowers','Collar de estrella del Kdrama Boys Over Flowers',120.00,1,'disponible'),(2,'ARE-JAK-01','Aretes Jake hora de aventura','Aretes tamaño grande de acrilico Jake',120.00,1,'disponible'),(3,'COL-ONP-01','One Piece doble dije','Collar largo con doble dije de One Piece',180.00,0,'agotado'),(4,'ARE-VAQ-01','Aretes vaqueros','Aretes de corazon y botas baqueras tamaño mediano',110.00,1,'disponible'),(5,'ANI-MIR-01','Miraculous Chat Noir','Anillo Miraculous de Adrien Agreste/ Chat Noir color negro',60.00,3,'disponible'),(7,'ANI-192956','Producto Prueba CRUD','Prueba',699.00,5,'disponible'),(9,'ANI-637755','Prueba','xwwss',189.00,1,'disponible');
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos_has_categorias`
--

DROP TABLE IF EXISTS `productos_has_categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productos_has_categorias` (
  `productos_id_productos` int NOT NULL,
  `categorias_id_categoria` int NOT NULL,
  PRIMARY KEY (`productos_id_productos`,`categorias_id_categoria`),
  KEY `fk_productos_has_categorias_categorias1_idx` (`categorias_id_categoria`),
  KEY `fk_productos_has_categorias_productos1_idx` (`productos_id_productos`),
  CONSTRAINT `fk_productos_has_categorias_categorias1` FOREIGN KEY (`categorias_id_categoria`) REFERENCES `categorias` (`id_categoria`),
  CONSTRAINT `fk_productos_has_categorias_productos1` FOREIGN KEY (`productos_id_productos`) REFERENCES `productos` (`id_productos`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos_has_categorias`
--

LOCK TABLES `productos_has_categorias` WRITE;
/*!40000 ALTER TABLE `productos_has_categorias` DISABLE KEYS */;
INSERT INTO `productos_has_categorias` VALUES (1,1),(2,1),(3,1),(4,1),(5,1),(7,1),(1,2),(2,2),(4,2),(9,2);
/*!40000 ALTER TABLE `productos_has_categorias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id_rol` int NOT NULL AUTO_INCREMENT,
  `rol_usuario` varchar(20) NOT NULL,
  PRIMARY KEY (`id_rol`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'admin'),(2,'user');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subcategoria`
--

DROP TABLE IF EXISTS `subcategoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subcategoria` (
  `id_subcategoria` int NOT NULL AUTO_INCREMENT,
  `nombre_subcategoria` varchar(45) NOT NULL,
  `categorias_id_categoria` int NOT NULL,
  PRIMARY KEY (`id_subcategoria`,`categorias_id_categoria`),
  KEY `fk_subcategoria_categorias1_idx` (`categorias_id_categoria`),
  CONSTRAINT `fk_subcategoria_categorias1` FOREIGN KEY (`categorias_id_categoria`) REFERENCES `categorias` (`id_categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subcategoria`
--

LOCK TABLES `subcategoria` WRITE;
/*!40000 ALTER TABLE `subcategoria` DISABLE KEYS */;
INSERT INTO `subcategoria` VALUES (1,'anillos',1),(2,'aretes',1),(3,'collar',1),(4,'diadema',2),(5,'lentes',2);
/*!40000 ALTER TABLE `subcategoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id_usuario` int NOT NULL AUTO_INCREMENT,
  `nombre_completo` varchar(100) NOT NULL,
  `email` varchar(45) NOT NULL,
  `telefono` varchar(10) NOT NULL,
  `password` varchar(100) NOT NULL,
  `fecha_registro` date NOT NULL,
  `roles_id_rol` int NOT NULL,
  PRIMARY KEY (`id_usuario`,`roles_id_rol`),
  KEY `fk_usuarios_roles1_idx` (`roles_id_rol`),
  CONSTRAINT `fk_usuarios_roles1` FOREIGN KEY (`roles_id_rol`) REFERENCES `roles` (`id_rol`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'Carlos Alberto Romero','cromero@empresa.com','8112345678','$2y$10$G8sg8Glh7mNMRd69ykTjK.CJ9x7t.4QCrCW2cRtuAamp/s6A8znVa','2026-01-15',1),(2,'Valeria Garza Treviño','vgarza@empresa.com','5559876543','$2y$10$smmKKK6tno4ziAZN89SHoO6F.otKs29mz73elfa/H9Xunav9HLcJ.','2026-02-20',2),(3,'Roberto Martínez','rmartinez@correo.com','3334567890','$2y$10$6sB6TEVSMqXlObSUCe9OOu2MXyhcjXAxqUR8HvTBB9Fzl85oU4.MO','2026-03-10',2),(4,'María Fernanda López','mflopez@correo.com','8187654321','$2y$10$ne.Zmap6Qg.ztTO09ZJ7MuTIoriR0ptEFoc9fM/8n901xAoUwHPye','2026-04-05',2),(5,'Javier Hernández','jhernandez@correo.com','5511223344','$2y$10$KhJsaTETs6T/jCCcUlyDRuPJoefcikcgwggjce9HRiBzwoXnoVAGW','2026-05-12',2),(6,'Usuario Prueba','prueba2026@yeya.com','5512349999','$2a$10$jFFPk7n/nJ6dvgbS9wDPD.TxNNoufBnYn6Rh7alcxiuQRvjvsjtqq','2026-08-26',2),(7,'Admin Prueba','adminprueba@yeya.com','5599990001','$2a$10$jFFPk7n/nJ6dvgbS9wDPD.TxNNoufBnYn6Rh7alcxiuQRvjvsjtqq','2026-08-27',1),(8,'Admin Temporal','admin2prueba@yeya.com','5588880002','$2a$10$IqmkeReYHWRuzfXv/gfgx.8NRTc0u0Kh2FfbwsA1PR9we58oei52e','2026-08-27',1),(9,'Santiago López','santiagolop@gmail.com','9541354971','$2a$10$mbzqJEzNEngBVH5G7PjJ9.hIKvRnAlKVf6pEHOUGkYz5bjQ.C1kO6','2026-08-27',2),(10,'Mariana Perez','mariana@yeya.com','5514257896','$2a$10$3rWnPmPjQ2agyHmYHWju4eQfx0CQOwTV0v1YW22VDnsfKn5hDToLO','2026-08-27',2),(11,'Ernesto Nava','ernestonava@yeya.com','5514257810','$2a$10$H1jLt6xD4XcgoejXFjTny.Z771GSPBI.2WOlOYN1lRRAl03BGKLGW','2026-08-27',2),(12,'Admin becario','adminbecario@yeya.com','5575511759','$2a$10$xia2L2I2.FrzWUD/PGiL/uo58xLfS0EqLGec0k8nAK2vmkmkcsql2','2026-08-27',2),(13,'Iran Perez','iran@yeya.com','9541789657','$2a$10$WKeeb5g7hWid7cBKqcN9T.7Gpfeucoel3Gq2QNl0uO668JTGcCi6C','2026-08-27',2);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `variantes`
--

DROP TABLE IF EXISTS `variantes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `variantes` (
  `id_variantes` int NOT NULL AUTO_INCREMENT,
  `sku_variante` varchar(45) NOT NULL,
  `atributos` varchar(45) NOT NULL,
  `stock_variante` varchar(45) NOT NULL,
  PRIMARY KEY (`id_variantes`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `variantes`
--

LOCK TABLES `variantes` WRITE;
/*!40000 ALTER TABLE `variantes` DISABLE KEYS */;
INSERT INTO `variantes` VALUES (1,'VAR-COL-01','Dorado','15'),(2,'VAR-COL-02','Plateado','20'),(3,'VAR-COL-03','Rosa','5'),(4,'VAR-COL-04','Negro','8'),(5,'VAR-COL-05','Amarillo','12'),(6,'VAR-MAT-01','Fantasia','30'),(7,'VAR-MAT-02','Acrilico','10'),(8,'VAR-MAT-03','Acero inoxidable','25'),(9,'VAR-MAT-04','Acero inoxidable','18'),(10,'VAR-MAT-05','Plata','7');
/*!40000 ALTER TABLE `variantes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `variantes_has_productos`
--

DROP TABLE IF EXISTS `variantes_has_productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `variantes_has_productos` (
  `variantes_id_variantes` int NOT NULL,
  `productos_id_productos` int NOT NULL,
  PRIMARY KEY (`variantes_id_variantes`,`productos_id_productos`),
  KEY `fk_variantes_has_productos_productos1_idx` (`productos_id_productos`),
  KEY `fk_variantes_has_productos_variantes1_idx` (`variantes_id_variantes`),
  CONSTRAINT `fk_variantes_has_productos_productos1` FOREIGN KEY (`productos_id_productos`) REFERENCES `productos` (`id_productos`),
  CONSTRAINT `fk_variantes_has_productos_variantes1` FOREIGN KEY (`variantes_id_variantes`) REFERENCES `variantes` (`id_variantes`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `variantes_has_productos`
--

LOCK TABLES `variantes_has_productos` WRITE;
/*!40000 ALTER TABLE `variantes_has_productos` DISABLE KEYS */;
INSERT INTO `variantes_has_productos` VALUES (2,1),(8,1),(5,2),(7,2),(1,3),(6,3),(3,4),(9,4),(4,5),(10,5);
/*!40000 ALTER TABLE `variantes_has_productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'la_yeya'
--

--
-- Dumping routines for database 'la_yeya'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-08-27 18:09:14
