Задание:
Создать SpringBoot приложение с эндпоинтами и запустить его в Kubernetes с помощью Helm

Шаги по реализации:

> $ kubectl config get-contexts
> 
> $ kubectl config use-context docker-desktop


> $ helm create hello-world

зарегиться на docker и аутентифицироваться docker-desktop
Создать image и запушить в docker repository:

> docker build -t docker_myapp .

> docker tag docker_myapp kseniya1/docker_myapp  // (переименовать иначе будет ошибка при пуше)
> 
> docker push kseniya1/docker_myapp


Провалидировать чарт:
> helm template ./helm/hello-world --values ./main/values.yaml --debug
> 
> helm lint ./helm/hello-world --values ./main/values.yaml --debug
 

Деплой в kubernetes:
> helm upgrade --install helm-kubernetes-userapp --values ./main/values.yaml ./helm
