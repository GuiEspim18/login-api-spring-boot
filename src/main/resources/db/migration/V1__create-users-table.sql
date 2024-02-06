CREATE TABLE users (

    id bigint not null auto_increment,
    name varchar(100) not null,
    username varchar(100) not null,
    email varchar(100) not null unique,
    password varchar(60) not null unique,
    street varchar(100) not null,
    neighborhood varchar(100) not null,
    zipcode varchar(9) not null,
    compliment varchar(100),
    number varchar(20),
    uf char(2) not null,
    city varchar(100) not null,

    primary key(id)

);