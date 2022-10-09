CREATE TABLE `pizza` (
  `id` int PRIMARY KEY,
  `name` varchar(255)
);

CREATE TABLE `ingredient` (
  `id` int PRIMARY KEY,
  `ingredient` varchar(255)
);

CREATE TABLE `pizza_ingredient` (
  `pizza_id` int NOT NULL,
  `ingredient_id` int NOT NULL,
  PRIMARY KEY (`pizza_id`, `ingredient_id`)
);

ALTER TABLE `pizza_ingredient` ADD FOREIGN KEY (`pizza_id`) REFERENCES `pizza` (`id`);

ALTER TABLE `pizza_ingredient` ADD FOREIGN KEY (`ingredient_id`) REFERENCES `ingredient` (`id`);

