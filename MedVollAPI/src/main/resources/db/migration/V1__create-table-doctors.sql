create table doctor(
    id bigint not null auto_increment,
    name varchar(100) not null,
    email varchar(100) not null,
    register varchar(100) not null,
    speciality varchar(100) not null,
    street varchar(100) not null,
    zone varchar(100) not null,
    zip varchar(100) not null,
    city varchar(100) not null,
    state varchar(100) not null,
    number varchar(100),
    additional_info varchar(100),
    primary key(id)
);