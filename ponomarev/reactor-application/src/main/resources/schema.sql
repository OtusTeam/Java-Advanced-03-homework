create table if not exists USER_ENTITY(
object_id long GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
user_name varchar(255) NOT NULL UNIQUE,
user_data varchar(255) NOT NULL,
password varchar(255) NOT NULL
);