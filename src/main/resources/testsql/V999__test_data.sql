INSERT INTO maker (id, name, country)
VALUES (1, 'SAMSUNG', 'Япония'),
       (2, 'Apple', 'США'),
       (3, 'Dell', 'США'),
       (4, 'Electrolux', 'Швеция');

INSERT INTO partition (id, name)
VALUES (1, 'Телевизоры'),
       (2, 'Смартфоны'),
       (3, 'Пылесосы');

INSERT INTO product (id, name, description, price, available, currency, guarantee, maker_id, partition_id)
VALUES (1, 'IPHONE 13', 'IPHONE 13 512GB', 102000, true, 'RUR', 1, 2, 2),
       (2, 'IPHONE 12', 'IPHONE 12 64GB', 60000, true, 'RUR', 1, 2, 2),
       (3, 'Пылесос Electrolux Ultra', 'Пылесос Electrolux Ultra Silencer новинка', 7000, true, 'RUR', 1, 4, 3);