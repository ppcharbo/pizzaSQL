

category_product --> pizza_ingredient 
---------------
id_category --> pizza_id
id_product --> ingredient_id

product --> ingredient 
---------------
id_product --> id
id_manufacturer --> ingredient
				--> price 

manufacturer  -->pizza
---------------
id_manufacturer --> id
name---> name 












SELECT m.name, cp.id_category
FROM manufacturer as m
INNER JOIN product as p
    ON m.id_manufacturer = p.id_manufacturer
INNER JOIN category_product as cp
    ON p.id_product = cp.id_product
WHERE cp.id_category = 'some value'