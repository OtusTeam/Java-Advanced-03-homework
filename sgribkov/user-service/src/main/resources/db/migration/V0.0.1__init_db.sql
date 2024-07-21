
create table if not exists user_data
(
    login varchar(100) not null,
    password varchar(100) not null,
    primary key (login)
);