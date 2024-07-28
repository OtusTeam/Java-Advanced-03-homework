Изучение helm
Выполнение задания осуществлялось на ОС Windows с использованием WSL с установленными Docker и microk8s.


```shell
    mvn clean package -pl task12_docker
```

```text
    [INFO]
    [INFO] -----------------------< ru.otus:task12_docker >------------------------
    [INFO] Building docker 1.0-SNAPSHOT
    [INFO]   from pom.xml
    [INFO] --------------------------------[ jar ]---------------------------------
    [INFO]
    [INFO] --- clean:3.3.2:clean (default-clean) @ task12_docker ---
    [INFO] Deleting C:\apps\repo\otus\advanced_java\otus_git_hw\Java-Advanced-homework\reznikovev\task12_docker\target
    [INFO]
    [INFO] --- resources:3.3.1:resources (default-resources) @ task12_docker ---
    [INFO] skip non existing resourceDirectory C:\apps\repo\otus\advanced_java\otus_git_hw\Java-Advanced-homework\reznikovev\task12_docker\src\main\resources
    [INFO] skip non existing resourceDirectory C:\apps\repo\otus\advanced_java\otus_git_hw\Java-Advanced-homework\reznikovev\task12_docker\src\main\resources
    [INFO]
    [INFO] --- compiler:3.13.0:compile (default-compile) @ task12_docker ---
    [INFO] Recompiling the module because of changed source code.
    [INFO] Compiling 2 source files with javac [debug parameters release 17] to target\classes
    [INFO]
    [INFO] --- resources:3.3.1:testResources (default-testResources) @ task12_docker ---
    [INFO] skip non existing resourceDirectory C:\apps\repo\otus\advanced_java\otus_git_hw\Java-Advanced-homework\reznikovev\task12_docker\src\test\resources
    [INFO]
    [INFO] --- compiler:3.13.0:testCompile (default-testCompile) @ task12_docker ---
    [INFO] Recompiling the module because of changed dependency.
    [INFO]
    [INFO] --- surefire:3.1.2:test (default-test) @ task12_docker ---
    [INFO]
    [INFO] --- jar:3.3.0:jar (default-jar) @ task12_docker ---
    [INFO] Building jar: C:\apps\repo\otus\advanced_java\otus_git_hw\Java-Advanced-homework\reznikovev\task12_docker\target\task12_docker-1.0-SNAPSHOT.jar
    [INFO]
    [INFO] --- spring-boot:3.2.5:repackage (repackage) @ task12_docker ---
    [INFO] Replacing main artifact C:\apps\repo\otus\advanced_java\otus_git_hw\Java-Advanced-homework\reznikovev\task12_docker\target\task12_docker-1.0-SNAPSHOT.jar with repackaged archive, adding nested dependencies in BOOT-INF/.
    [INFO] The original artifact has been renamed to C:\apps\repo\otus\advanced_java\otus_git_hw\Java-Advanced-homework\reznikovev\task12_docker\target\task12_docker-1.0-SNAPSHOT.jar.original
    [INFO] ------------------------------------------------------------------------
    [INFO] BUILD SUCCESS
    [INFO] ------------------------------------------------------------------------
    [INFO] Total time:  3.663 s
    [INFO] Finished at: 2024-07-28T18:26:45+03:00
```

наименование 'localhost:32000' - требование microk8s
```shell
   sudo docker build . -t localhost:32000/be.app:1.0
```

```text
    [+] Building 2.3s (9/9) FINISHED                                                                                                 docker:default
     => [internal] load build definition from Dockerfile                                                                                       0.0s
     => => transferring dockerfile: 358B                                                                                                       0.0s
     => [internal] load metadata for docker.io/bellsoft/liberica-openjre-alpine:17                                                             1.2s
     => [internal] load .dockerignore                                                                                                          0.0s
     => => transferring context: 2B                                                                                                            0.0s
     => [1/4] FROM docker.io/bellsoft/liberica-openjre-alpine:17@sha256:a39e3fb3c96ae89e936a2e50c0a597a91d2357a1ec19096c05e851f54f3a9715       0.0s
     => [internal] load build context                                                                                                          0.7s
     => => transferring context: 19.77MB                                                                                                       0.7s
     => CACHED [2/4] RUN mkdir -p /app                                                                                                         0.0s
     => CACHED [3/4] WORKDIR /app                                                                                                              0.0s
     => [4/4] COPY ./target/task12_docker*.jar /app/app.jar                                                                                    0.1s
     => exporting to image                                                                                                                     0.1s
     => => exporting layers                                                                                                                    0.1s
     => => writing image sha256:aafe03ea5e6aa7354179f70cfb27a55b4e4c23454687b18487d45e9a013d365f                                               0.0s
     => => naming to localhost:32000/be.app:1.0  
```

```shell
   sudo docker push localhost:32000/be.app:1.0
```

```text
    958c9f1167c4: Pushed
    5f70bf18a086: Layer already exists
    6bd97fdc35c1: Layer already exists
    910386285f03: Layer already exists
    e98448f05f93: Layer already exists
    78561cef0761: Layer already exists
    1.0: digest: sha256:16beb6b0c9ad4adc3bf62591ffffb4f64cf0a28a9e50ef74cf31231dfbc8c622 size: 1576
```

```shell
  cd .\task13_helm\
  helm upgrade --install be-app --values .\values.yaml .\.
```

```text
    Release "be-app" has been upgraded. Happy Helming!
    NAME: be-app
    LAST DEPLOYED: Sun Jul 28 18:29:03 2024
    NAMESPACE: default
    STATUS: deployed
    REVISION: 14
    TEST SUITE: None
```

```shell
   helm ls
```

```text
    NAME    NAMESPACE       REVISION        UPDATED                                 STATUS          CHART           APP VERSION
    be-app  default         14              2024-07-28 18:29:03.0235529 +0300 MSK   deployed        be-app-2.0.0    1.0.0
```

```shell
  kubectl get pod -l app=be-app
```

```shell
    NAME                      READY   STATUS    RESTARTS   AGE
    be-app-5968ccb698-msm4x   1/1     Running   0          34s
```