
  Select * from product as p  
  inner JOIN product_attribute AS f2 ON p.id = f2.product_id AND f2.attribute_name = 'Multiplayer' AND (f2.attribute_value = 'Yes') 
  inner JOIN product_attribute AS f1 ON p.id = f1.product_id AND f1.attribute_name = 'Genre' AND (f1.attribute_value = 'Shooter' OR f1.attribute_value = 'Strategy'  OR f1.attribute_value = 'Arcade') 
  inner JOIN product_attribute AS f3 ON p.id = f3.product_id AND f3.attribute_name = 'Year' AND (f3.attribute_value = '2020')  
