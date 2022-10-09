insert into ingredient(id, ingredient,price) values ('1','ham','50');
insert into ingredient(id, ingredient,price) values ('2','tomato','60');
insert into ingredient(id, ingredient,price) values ('3','oil extra vergin ','66');
insert into ingredient(id, ingredient,price) values ('4','Salame','100');
insert into ingredient(id, ingredient,price) values ('5','Mozzarella','100');
insert into pizza(id, name) values ('1','margerita');
insert into pizza(id, name) values ('2','calabrese');
insert into pizza(id, name) values ('3','napolitana');
insert into pizza_ingredient(pizza_id,ingredient_id) values ('1','3');
insert into pizza_ingredient(pizza_id,ingredient_id) values ('1','2');
insert into pizza_ingredient(pizza_id,ingredient_id) values ('1','5');
insert into pizza_ingredient(pizza_id,ingredient_id) values ('2','3');
insert into pizza_ingredient(pizza_id,ingredient_id) values ('2','4');
insert into pizza_ingredient(pizza_id,ingredient_id) values ('2','5');
insert into pizza_ingredient(pizza_id,ingredient_id) values ('3','3');
insert into pizza_ingredient(pizza_id,ingredient_id) values ('3','1');
insert into pizza_ingredient(pizza_id,ingredient_id) values ('3','5');

