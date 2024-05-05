CREATE TABLE my_user
(
    id       uuid PRIMARY KEY,
    login VARCHAR(255),
    password VARCHAR(255)
);

INSERT INTO my_user (id, login, password)
VALUES ('9d66aa52-88db-4fb2-b136-3f0257c29357', 'iskander', '$2a$10$4nHuaTNBNwgUOc10W.4uR.MAsPjCj/sjmvJF6NWXHgxvwiHcVxP76');
