CREATE TABLE my_user
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    login    VARCHAR(255),
    password VARCHAR(255)
);

INSERT INTO my_user (login, password)
VALUES ('iskander', '$2a$10$4nHuaTNBNwgUOc10W.4uR.MAsPjCj/sjmvJF6NWXHgxvwiHcVxP76');
