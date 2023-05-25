--  SELECT * FROM product 
--  JOIN (select * from product_attribute WHERE attribute_name='Genre' and attribute_value='Shooter' or attribute_value='Strategy') 
--  as pattribute 
--  ON pattribute.product_id=product.id
 
--  select p.* from product p join product_attribute pa on p.id = pa.product_id where(pa.attribute_name, pa.attribute_value) in (('Genre','Shooter'),('Genre','Strategy'),('Year','2022'))
 --and (attribute_name = 'Year' and attribute_value = '2018' or attribute_value = '2022'
--  SELECT * FROM product 
--   JOIN (select * from product_attribute where (attribute_name = 'Genre' and attribute_value = 'Shooter' or attribute_value = 'Strategy') ) as pattribute ON pattribute.product_id=product.id

  Select * from product as p  
  inner JOIN product_attribute AS f2 ON p.id = f2.product_id AND f2.attribute_name = 'Multiplayer' AND (f2.attribute_value = 'Yes') 
  inner JOIN product_attribute AS f1 ON p.id = f1.product_id AND f1.attribute_name = 'Genre' AND (f1.attribute_value = 'Shooter' OR f1.attribute_value = 'Strategy'  OR f1.attribute_value = 'Arcade') 
  inner JOIN product_attribute AS f3 ON p.id = f3.product_id AND f3.attribute_name = 'Year' AND (f3.attribute_value = '2020')  
-- Select * FROM product_attribute AS genre
-- JOIN product_attribute AS yiar
--   ON genre.id = yiar.id
--    AND genre.attribute_name = 'Genre' AND (genre.attribute_value = 'Shooter' or genre.attribute_value = 'Strategy')
--    AND yiar.attribute_name = 'Year' AND yiar.attribute_value = '2022'


--   Select * from (Select * from product as p  JOIN product_attribute AS f1 ON p.id = f1.product_id AND f1.attribute_name = 'Genre' or f1.attribute_name = 'Year') where (attribute_name = 'Year' and (attribute_value = '2022' or attribute_value='2020')) or 
--   Inner JOIN product_attribute AS f2 ON p.id = f2.product_id AND f2.attribute_value = '2022' or f2.attribute_value = 'Strategy' or f2.attribute_value='Shooter'
--   Select * from product as p JOIN product_attribute AS f1 ON p.id = f1.product_id AND f1.attribute_name = 'Genre' AND (f1.attribute_value = 'Shooter' OR f1.attribute_value = 'Strategy')  JOIN product_attribute AS f2 ON p.id = f2.product_id AND f2.attribute_name = 'Year' AND (f2.attribute_value = '2020' OR f2.attribute_value = '2022')
/* filter2 join */


-- second join doesnt work because no in resulting table
-- INNER JOIN 
--     product_attribute AS f2 
--         ON 
--     p.id = f2.product_id 
--         AND 
--     f1.attribute_name = 'Year'
--         AND
--     f1.attribute_value = '2022'
-- 	OR
-- 	f1.attribute_value = 'Strategy'
  
  
--    SELECT * FROM product 
--  Inner JOIN (select * from product_attribute where (attribute_name = 'Genre' and attribute_value = 'Shooter' or attribute_value = 'Strategy') ) as pattribute ON pattribute.product_id=product.id
-- select * from product_attribute where (attribute_name = 'Genre' and attribute_value = 'Shooter' or attribute_value = 'Strategy') and (attribute_name = 'Year' and attribute_value = '2018' or attribute_value = '2022');
--  SELECT
--     *
-- FROM
--     product p
-- WHERE
--     p.id IN (
--     SELECT
--         matching_attrs.product_id
--     FROM
--         (
--         SELECT
--             count(*) AS matching_attr_count,
--             pa.product_id
--         FROM
--             product_attribute pa
--         WHERE
--             pa.product_subcategory_id = 123
--             AND ((pa.attribute_name = 'cpu'
--                 AND pa.attribute_value = 'core i7')
--             OR (pa.attribute_name = 'year'
--                 AND pa.attribute_value='2022'))
--         GROUP BY
--             pa.product_id) AS matching_attrs
--     WHERE
--         matching_attrs.matching_attr_count = 2) 


-- select * from product_attribute  WHERE attribute_name='Genre'
--  WHERE product_attribute.attribute_name='genre' AND (product_attribute.attribute_value = 'shooter' OR product_attribute.attribute_value = 'strategy');