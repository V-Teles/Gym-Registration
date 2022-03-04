CREATE DATABASE  IF NOT EXISTS `gym_users`;
USE `gym_users`;
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `phone_number` int(15) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

LOCK TABLES `users` WRITE;

INSERT INTO `users` VALUES (1,'Mary','Public','1999-10-01','391488677','Corso Sanpaolo 21','mary@gmail.com'),
						   (2,'John','Doe','1989-12-11','391243677','Via Monginevro 34','john@gmail.com'),
						   (3,'Ajay','Rao','1970-07-15','391427447','Via Roma 65','ajay@gmail.com'),
						   (4,'Bill','Neely','2001-06-22','391422677','Via Garibaldi 26','bill@gmail.com'),
						   (5,'Maxwell','Dixon','1992-05-24','391429487','Corso Giambone 88','max@gmail.com');

UNLOCK TABLES;