# Homework Docker.

### Build jar:
```
mvn clean verify
```

### build image with our *.jar and run it on port 8081

```
docker build -t myhomeworkapp . && docker run -p 80:8081 myhomeworkapp:latest
```

### Check that our container started:
```
docker ps
```

### Result:
```
hellomoto@Hellos-MacBook-Pro ~ % docker ps
CONTAINER ID   IMAGE                  COMMAND                  CREATED         STATUS         PORTS                    NAMES
a0add3bbef10   myhomeworkapp:latest   "/__cacert_entrypoin…"   2 minutes ago   Up 2 minutes   0.0.0.0:8081->8081/tcp   beautiful_cori
```

### Test docker app:

#### Create user:
```
curl --location 'http://127.0.0.1:8081/users/create' \
--header 'Content-Type: application/json' \
--data '{
    "name": "user1",
    "password":12356
}'
```

#### Get all users:
```
curl --location 'http://localhost:8081/users' 
```

#### Get HelloWorld:
curl --location 'http://127.0.0.1:80/users/helloworld'