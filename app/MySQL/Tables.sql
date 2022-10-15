CREATE TABLE `items_type` (
  `id` int PRIMARY KEY NOT NULL,
  `type` varchar(45) DEFAULT NULL
);

CREATE TABLE `ingredients` (
  `id` int PRIMARY KEY NOT NULL,
  `name` varchar(25) DEFAULT NULL,
  `price` double NOT NULL,
  `veggie` tinyint NOT NULL
);

CREATE TABLE `items` (
  `id` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `items_type_id` int,
  `name` varchar(25) DEFAULT NULL,
  `price` double
);

CREATE TABLE `customers` (
  `id` int PRIMARY KEY NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `postal_code` int NOT NULL,
  `email` varchar(45) NOT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `passwd` varchar(45) NOT NULL
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

ALTER TABLE `items` ADD FOREIGN KEY (`items_type_id`) REFERENCES `items_type` (`id`);

CREATE TABLE `orders_items` (
  `orders_id` int,
  `items_id` int,
  PRIMARY KEY (`orders_id`, `items_id`)
);

ALTER TABLE `orders_items` ADD FOREIGN KEY (`orders_id`) REFERENCES `orders` (`id`);

ALTER TABLE `orders_items` ADD FOREIGN KEY (`items_id`) REFERENCES `items` (`id`);


ALTER TABLE `orders` ADD FOREIGN KEY (`idrider`) REFERENCES `riders` (`id`);

CREATE TABLE `customers_orders` (
  `customers_id` int,
  `orders_id` int,
  PRIMARY KEY (`customers_id`, `orders_id`)
);

ALTER TABLE `customers_orders` ADD FOREIGN KEY (`customers_id`) REFERENCES `customers` (`id`);

ALTER TABLE `customers_orders` ADD FOREIGN KEY (`orders_id`) REFERENCES `orders` (`id`);


CREATE TABLE `items_ingredients` (
  `items_id` int,
  `ingredients_id` int,
  PRIMARY KEY (`items_id`, `ingredients_id`)
);

ALTER TABLE `items_ingredients` ADD FOREIGN KEY (`items_id`) REFERENCES `items` (`id`);

ALTER TABLE `items_ingredients` ADD FOREIGN KEY (`ingredients_id`) REFERENCES `ingredients` (`id`);

