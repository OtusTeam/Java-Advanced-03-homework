# Spring Reactor homework

### User registration service via Spring Reactor framework:

## Run postgres DB, and init schema:
```
 docker run --name some-postgres -p 5432:5432 -e POSTGRES_PASSWORD=password -d postgres
psql -f src/main/resources/schema.sql-U postgres
```



1) Create user (login must be unique):
```
curl --location 'http://127.0.0.1:8080/users/create' \
--header 'Content-Type: application/json' \
--data '{
    "login": "user",
    "password":"12345"
}'
```

2) Login user, return 200 if login success, and 404 if user not found.

```
curl --location 'http://127.0.0.1:8080/users/login' \
--header 'Content-Type: application/json' \
--data '{
    "login": "user",
    "password":"12345"
}'
```