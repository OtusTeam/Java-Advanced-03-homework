# Homework Kubernetes, Helm

### Build jar:
```
mvn clean verify
```

### Build image with our *.jar

```
docker build -t myhomeworkapp .
```

# PART1.Run our app in minikube.

### Generate the deployment:
```
$ kubectl create deployment myhomeworkapp --image=myhomeworkapp --dry-run=client -o yaml > deploy-myapp.yaml
```

### Create the deployment:
```
$ kubectl apply -f deploy-myapp.yaml
deployment.apps/myhomeworkapp created
```


### Get deployments
```
$ kubectl get deployments
NAME              READY   UP-TO-DATE   AVAILABLE   AGE
myhomeworkapp     1/1     1            1           19s
```

### Check that our pod is UP
```
$ kubectl get pods
NAME                               READY   STATUS             RESTARTS   AGE
myhomeworkapp-8bd578b94-lfgsd      1/1     Running            0          10m
```

### Port-forward
```
$ kubectl port-forward myhomeworkapp-8bd578b94-lfgsd 8081:8081
```

### Test minikube app:

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
curl http://localhost:8081/users'
```

#### Get HelloWorld:
```
$ curl http://127.0.0.1:8081/users/helloworld
Hello world!%
```



# PART.2  Run it via Helm chart:
```$ helm create homework13App ```

### Setup Chart.yaml (set image, ports etc)

### Deploy via Helm
```
helm install my-helm-homework  homework13App/ --values homework13App/values.yaml
```

### Check that app deployed:
```
hellomoto@Hellos-MacBook-Pro task13 % helm list --all                                                                 
NAME                    NAMESPACE       REVISION        UPDATED                                 STATUS          CHART                   APP VERSION
my-helm-homework        default         1               2024-07-21 13:52:00.153721 +0300 MSK    deployed        homework13app-0.1.0     1.16.0     
```

### Check that our pod is UP
```
$ kubectl get pods
```

### Port-forward
```
$ kubectl port-forward myhomeworkapp-8bd578b94-lfgsd 8081:8081
```

#### Get HelloWorld:
```
$ curl http://127.0.0.1:8081/users/helloworld
Hello world!
```

