INSERT INTO maker (name, country)
VALUES ('SAMSUNG', 'Япония'),
       ('Apple', 'США'),
       ('Dell', 'США'),
       ('Electrolux', 'Швеция');

INSERT INTO partition (name)
VALUES ('Телевизоры'),
       ('Смартфоны'),
       ('Пылесосы');

INSERT INTO product (name, description, price, available, currency, guarantee, maker_id, partition_id)
VALUES ('IPHONE 13', 'IPHONE 13 512GB', 102000, true, 'RUR', 1, 2, 2),
       ('IPHONE 12', 'IPHONE 12 64GB', 60000, true, 'RUR', 1, 2, 2),
       ('Пылесос Electrolux Ultra', 'Пылесос Electrolux Ultra Silencer новинка', 7000, true, 'RUR', 1, 4, 3);