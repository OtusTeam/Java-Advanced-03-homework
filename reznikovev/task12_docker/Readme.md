Использование docker

```shell
   sudo docker build . -t task12:first
```

```text
    [+] Building 2.0s (9/9) FINISHED                                                                                                                                                                                                                                         docker:default
     => [internal] load build definition from Dockerfile                                                                                                                                                                                                                               0.0s
     => => transferring dockerfile: 358B                                                                                                                                                                                                                                               0.0s
     => [internal] load metadata for docker.io/bellsoft/liberica-openjre-alpine:17                                                                                                                                                                                                     1.0s
     => [internal] load .dockerignore                                                                                                                                                                                                                                                  0.0s
     => => transferring context: 2B                                                                                                                                                                                                                                                    0.0s
     => CACHED [1/4] FROM docker.io/bellsoft/liberica-openjre-alpine:17@sha256:6a8d10320df48a2fdbf9f0b5701e4489ad557db01e8d826eaa0549e3dad8b59a                                                                                                                                        0.0s
     => [internal] load build context                                                                                                                                                                                                                                                  0.7s
     => => transferring context: 87B                                                                                                                                                                                                                                                   0.7s
     => [2/4] RUN mkdir -p /app                                                                                                                                                                                                                                                        0.4s
     => [3/4] WORKDIR /app                                                                                                                                                                                                                                                             0.0s
     => [4/4] COPY ./target/task12_docker*.jar /app/app.jar                                                                                                                                                                                                                            0.1s
     => exporting to image                                                                                                                                                                                                                                                             0.1s
     => => exporting layers                                                                                                                                                                                                                                                            0.1s
     => => writing image sha256:732c07f797252818c7ae189a3ef3c41ed7234aa5e10b57c5f984ec2ec4deaf4a                                                                                                                                                                                       0.0s
     => => naming to docker.io/library/task12:first
```

```shell
   sudo docker run -d -p 8081:8080 task12:first
```
```text
    f36023c20d0f3c710e4fdd68df1f75c36465c352bade8298042342f40ef77faf
```

```shell
   curl 'http://localhost:8081/api/hello'
```

```text
    Hello, world!
```