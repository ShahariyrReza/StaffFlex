CREATE DATABASE  IF NOT EXISTS `employee_directory`;
USE `employee_directory`;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Data for table `employee`
--

INSERT INTO `employee` VALUES 
	(1,'shahariyr','reza','shahariyr.reza@hotmail.com'),
	(2,'Emma','Watson','emma.watson@hotmail.com'),
	(3,'Shibber','Ahmmed','shibber.ahmmed@yahoo.com'),
	(4,'Akash','Sarker','akash.sarker@gmail.com'),
	(5,'Mazher','Reza','mazher.reza@gmail.com');

