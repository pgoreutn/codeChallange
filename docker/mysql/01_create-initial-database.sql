
CREATE TABLE IF NOT EXISTS product
(
    id              binary(16)    not null,
    name            varchar(150)  not null,
    description     varchar(256)  not null,
    weight          decimal(4,2)   not null,
    price           decimal(6,2)   not null,
    country         varchar(20)   not null,
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP
 );

ALTER TABLE  product
    ADD CONSTRAINT  PRIMARY KEY (id);

ALTER TABLE product
    ADD CONSTRAINT UNIQUE (name);
