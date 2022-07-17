CREATE TABLE maker
(
    id bigserial PRIMARY KEY NOT NULL,
    name varchar(30) NOT NULL,
    country varchar(30) NOT NULL
);

CREATE TABLE partition
(
    id bigserial PRIMARY KEY NOT NULL,
    name varchar(30) NOT NULL
);

CREATE TABLE product
(
    id bigserial PRIMARY KEY NOT NULL,
    name varchar(30) NOT NULL,
    description text NOT NULL,
    price numeric(20, 2) NOT NULL,
    available boolean NOT NULL,
    currency varchar(3) NOT NULL,
    guarantee integer,
    maker_id bigint NOT NULL,
    partition_id bigint NOT NULL,
    CONSTRAINT fk_product_partition FOREIGN KEY (partition_id) REFERENCES partition (id),
    CONSTRAINT fk_product_maker FOREIGN KEY (maker_id) REFERENCES maker (id)
);