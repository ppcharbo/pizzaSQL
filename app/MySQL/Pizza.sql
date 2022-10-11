CREATE TABLE `pizza` (
  `id` int PRIMARY KEY,
  `name` varchar(255)
);

CREATE TABLE `ingredient` (
  `id` int PRIMARY KEY,
  `ingredient` varchar(255),
  `price` int
);

CREATE TABLE `pizza_ingredient` (
  `pizza_id` int NOT NULL,
  `ingredient_id` int NOT NULL,
  PRIMARY KEY (`pizza_id`, `ingredient_id`)
);

CREATE TABLE `desserts` (
  `id` int NOT NULL,
  `name` varchar(255),
  `price` int NOT NULL,
  PRIMARY KEY (`id`)
) ;


CREATE TABLE `drinks` (
  `id` int NOT NULL,
  `name` varchar(255) ,
  `price` int NOT NULL,
  PRIMARY KEY (`id`)
) ;

CREATE TABLE `customers` (
  `id` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `address` varchar(255) NOT NULL,
  `postal_code` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ;


CREATE TABLE `riders` (
  `id` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `last_order` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ;

CREATE TABLE `orders` (
  `id` int NOT NULL,
  `idcustomer` int NOT NULL,
  `idrider` int NOT NULL,
  `price` int NOT NULL,
  `delivered` BOOLEAN DEFAULT NULL,
  `discountCode` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`,`idcustomer`),
  KEY `idcustomer_idx` (`idcustomer`),
  KEY `idrider_idx` (`idrider`),
  CONSTRAINT `idcustomer` FOREIGN KEY (`idcustomer`) REFERENCES `customers` (`id`),
  CONSTRAINT `idrider` FOREIGN KEY (`idrider`) REFERENCES `riders` (`id`)
) ;

ALTER TABLE `pizza_ingredient` ADD FOREIGN KEY (`pizza_id`) REFERENCES `pizza` (`id`);

ALTER TABLE `pizza_ingredient` ADD FOREIGN KEY (`ingredient_id`) REFERENCES `ingredient` (`id`);


