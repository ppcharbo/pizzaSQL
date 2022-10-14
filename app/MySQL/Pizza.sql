CREATE TABLE `desserts` (
  `id` int PRIMARY KEY NOT NULL,
  `name` varchar(25) DEFAULT NULL,
  `price` double NOT NULL
);

CREATE TABLE `drinks` (
  `id` int PRIMARY KEY NOT NULL,
  `name` varchar(25) DEFAULT NULL,
  `price` double NOT NULL
);

CREATE TABLE `ingredients` (
  `id` int PRIMARY KEY NOT NULL,
  `name` varchar(25) DEFAULT NULL,
  `price` double NOT NULL,
  `veggie` tinyint NOT NULL
);

CREATE TABLE `pizzas` (
  `id` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `name` varchar(25) DEFAULT NULL
);

CREATE TABLE `customers` (
  `id` int PRIMARY KEY NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `email` varchar(45) NOT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `passwd` varchar(45) NOT NULL
);

CREATE TABLE `menuitems` (
  `id` int PRIMARY KEY NOT NULL,
  `name` varchar(25) DEFAULT NULL,
  `price` double NOT NULL
);

CREATE TABLE `riders` (
  `id` int PRIMARY KEY NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `available` tinyint NOT NULL DEFAULT "0",
  `postal_code` int NOT NULL
);

CREATE TABLE `orders` (
  `id` int NOT NULL,
  `idcustomer` int NOT NULL,
  `idrider` int NOT NULL,
  `price` double NOT NULL,
  `ready_at` timestamp NOT NULL,
  `picked_up_at` timestamp DEFAULT NULL,
  `delivered` tinyint DEFAULT NULL,
  `discount_code` varchar(10),
  PRIMARY KEY (`id`, `idcustomer`, `discount_code`)
);

CREATE TABLE `pizzas_ingredients` (
  `pizzas_id` int,
  `ingredients_id` int,
  PRIMARY KEY (`pizzas_id`, `ingredients_id`)
);

CREATE TABLE `customers_orders` (
  `customers_id` int,
  `orders_id` int,
  PRIMARY KEY (`customers_id`, `orders_id`)
);



