create table patient(
    id bigint not null auto_increment,
    name varchar(100) not null,
    phone varchar(100) not null,
    email varchar(100) not null,
    ssn varchar(100) not null,
    street varchar(100) not null,
    zone varchar(100) not null,
    zip varchar(100) not null,
    city varchar(100) not null,
    state varchar(100) not null,
    number varchar(50),
    additional_info varchar(100),
    primary key(id)
);