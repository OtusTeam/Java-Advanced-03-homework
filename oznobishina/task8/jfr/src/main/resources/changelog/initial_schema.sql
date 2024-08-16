create table customer
(
    id       int NOT NULL,
    login    varchar(50),
    password varchar(50)
);

INSERT INTO customer (login, password) VALUES ('kitty', 'encoded password');
INSERT INTO customer (login, password) VALUES ('puppy', 'encoded password');
INSERT INTO customer (login, password) VALUES ('sweety', 'encoded password');