create table if not exists customer
(
    id       int NOT NULL,
    login    varchar(50),
    password varchar(50),
    email    varchar(50)
);

INSERT INTO customer (id, login, password, email) VALUES (1, 'kitty', 'encoded password', 'kitty@email.com');
INSERT INTO customer (id, login, password, email) VALUES (2, 'puppy', 'encoded password', 'puppy@email.com');
INSERT INTO customer (id, login, password, email) VALUES (3, 'sweety', 'encoded password', 'sweety@email.com');