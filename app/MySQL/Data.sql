/*
 INGREDIENTS
 */
insert into ingredients(id, name,price, veggie) values ('1','pomodoro','100', '1');
insert into ingredients(id, name,price, veggie) values ('2','mozzarella','200', '1');
insert into ingredients(id, name,price, veggie) values ('3','prosciutto','300', '0');
insert into ingredients(id, name,price, veggie) values ('4','funghi','300', '1');
insert into ingredients(id, name,price, veggie) values ('5','salame piccante','400', '0');
insert into ingredients(id, name,price, veggie) values ('6','tonno','200', '0');
insert into ingredients(id, name,price, veggie) values ('7','cipolla','100', '1');
insert into ingredients(id, name,price, veggie) values ('8','mortadella','400', '0');
insert into ingredients(id, name,price, veggie) values ('9','pistacchi','500', '1');
insert into ingredients(id, name,price, veggie) values ('10','burrata','500', '1');
insert into ingredients(id, name,price, veggie) values ('11','salsiccia','400', '0');
insert into ingredients(id, name,price, veggie) values ('12','friarielli','300', '1');
insert into ingredients(id, name,price, veggie) values ('13','origano','200', '1');
insert into ingredients(id, name,price, veggie) values ('14','speck','300', '0');
insert into ingredients(id, name,price, veggie) values ('15','brie','300', '1');
insert into ingredients(id, name,price, veggie) values ('16','olive','300', '1');
insert into ingredients(id, name,price, veggie) values ('17','verdure grigliate','200', '1');
insert into ingredients(id, name,price, veggie) values ('18','gorgonzola','300', '1');
insert into ingredients(id, name,price, veggie) values ('19','fontina','300', '1');
insert into ingredients(id, name,price, veggie) values ('20','pinoli','100', '1');
insert into ingredients(id, name,price, veggie) values ('21','pesto','200', '1');
insert into ingredients(id, name,price, veggie) values ('22','patate','200', '1');
/*
 ITEM TYPES
 */
insert into items_type(id,type) values('1','pizza');
insert into items_type(id,type) values('2','drink');
insert into items_type(id,type) values('3','dessert');
/*
 PIZZAS
 */
insert into items(items_type_id, name) values ('1','Marinara');
insert into items(items_type_id, name) values ('1','Margherita');
insert into items(items_type_id, name) values ('1','Prosciutto e funghi');
insert into items(items_type_id, name) values ('1','4 formaggi');
insert into items(items_type_id, name) values ('1','Diavola');
insert into items(items_type_id, name) values ('1','Ligure');
insert into items(items_type_id, name) values ('1','Salsiccia e friarielli');
insert into items(items_type_id, name) values ('1','Vegetariana');
insert into items(items_type_id, name) values ('1','Tonno e cipolla');
insert into items(items_type_id, name) values ('1','Speck e brie');
insert into items(items_type_id, name) values ('1','Mortadella e pistacchi');
/*
 DRINKS
 */
insert into items(items_type_id, name,price) values ('2', 'Coca cola','2');
insert into items(items_type_id, name,price) values ('2', 'Coca cola zero','2');
insert into items(items_type_id, name,price) values ('2', 'Fanta','2');
insert into items(items_type_id, name,price) values ('2', 'Sprite','2');
insert into items(items_type_id, name,price) values ('2', 'Orangina','3');
/*
 DESSERTS
 */
insert into items(items_type_id, name,price) values ('3', 'Tiramisù','10');
insert into items(items_type_id, name,price) values ('3', 'Torta alle mele','6');
insert into items(items_type_id, name,price) values ('3', 'Panna cotta','8');
/*
 LINK INGREDIENTS TO PIZZA
 */
insert into items_ingredients(items_id,ingredients_id) values ('1','1');
insert into items_ingredients(items_id,ingredients_id) values ('1','13');
insert into items_ingredients(items_id,ingredients_id) values ('1','16');
insert into items_ingredients(items_id,ingredients_id) values ('2','1');
insert into items_ingredients(items_id,ingredients_id) values ('2','2');
insert into items_ingredients(items_id,ingredients_id) values ('3','1');
insert into items_ingredients(items_id,ingredients_id) values ('3','2');
insert into items_ingredients(items_id,ingredients_id) values ('3','3');
insert into items_ingredients(items_id,ingredients_id) values ('3','4');
insert into items_ingredients(items_id,ingredients_id) values ('4','2');
insert into items_ingredients(items_id,ingredients_id) values ('4','18');
insert into items_ingredients(items_id,ingredients_id) values ('4','19');
insert into items_ingredients(items_id,ingredients_id) values ('4','10');
insert into items_ingredients(items_id,ingredients_id) values ('5','1');
insert into items_ingredients(items_id,ingredients_id) values ('5','2');
insert into items_ingredients(items_id,ingredients_id) values ('5','5');
insert into items_ingredients(items_id,ingredients_id) values ('6','1');
insert into items_ingredients(items_id,ingredients_id) values ('6','20');
insert into items_ingredients(items_id,ingredients_id) values ('6','21');
insert into items_ingredients(items_id,ingredients_id) values ('6','22');
insert into items_ingredients(items_id,ingredients_id) values ('7','1');
insert into items_ingredients(items_id,ingredients_id) values ('7','2');
insert into items_ingredients(items_id,ingredients_id) values ('7','11');
insert into items_ingredients(items_id,ingredients_id) values ('7','12');
insert into items_ingredients(items_id,ingredients_id) values ('8','1');
insert into items_ingredients(items_id,ingredients_id) values ('8','16');
insert into items_ingredients(items_id,ingredients_id) values ('8','17');
insert into items_ingredients(items_id,ingredients_id) values ('8','20');
insert into items_ingredients(items_id,ingredients_id) values ('9','1');
insert into items_ingredients(items_id,ingredients_id) values ('9','2');
insert into items_ingredients(items_id,ingredients_id) values ('9','6');
insert into items_ingredients(items_id,ingredients_id) values ('9','7');
insert into items_ingredients(items_id,ingredients_id) values ('10','1');
insert into items_ingredients(items_id,ingredients_id) values ('10','2');
insert into items_ingredients(items_id,ingredients_id) values ('10','14');
insert into items_ingredients(items_id,ingredients_id) values ('10','15');
insert into items_ingredients(items_id,ingredients_id) values ('11','1');
insert into items_ingredients(items_id,ingredients_id) values ('11','10');
insert into items_ingredients(items_id,ingredients_id) values ('11','8');
insert into items_ingredients(items_id,ingredients_id) values ('11','9');
/*
 RIDERS
 */
insert into riders (id, name, postal_code) values ('1', 'Alice', '1000');
insert into riders (id, name, postal_code) values ('2', 'Bob', '1100');
insert into riders (id, name, postal_code) values ('3', 'Dave', '1200');
