
ALTER TABLE `pizzas_ingredients` ADD FOREIGN KEY (`pizzas_id`) REFERENCES `pizzas` (`id`);

ALTER TABLE `pizzas_ingredients` ADD FOREIGN KEY (`ingredients_id`) REFERENCES `ingredients` (`id`);


ALTER TABLE `drinks` ADD FOREIGN KEY (`id`) REFERENCES `orderItems` (`id`);

ALTER TABLE `orderItems` ADD FOREIGN KEY (`id`) REFERENCES `orders` (`id`);

ALTER TABLE `desserts` ADD FOREIGN KEY (`id`) REFERENCES `orderItems` (`id`);

ALTER TABLE `pizzas` ADD FOREIGN KEY (`id`) REFERENCES `orderItems` (`id`);

ALTER TABLE `orders` ADD FOREIGN KEY (`idrider`) REFERENCES `riders` (`id`);



ALTER TABLE `customers_orders` ADD FOREIGN KEY (`customers_id`) REFERENCES `customers` (`id`);

ALTER TABLE `customers_orders` ADD FOREIGN KEY (`orders_id`) REFERENCES `orders` (`id`);

