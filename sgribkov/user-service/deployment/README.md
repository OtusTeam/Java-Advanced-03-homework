# Развертывание сервиса регистрации пользователей в кластере Kubernetes

Проведено развертывание сервиса регистрации пользователей в кластере Kubernetes с использованием helm chart.
<br>
Для обеспечения надежности развернуто два экземпляра сервиса с балансировкой трафика.
<br>
Для проверки готовности сервиса принимать запросы пользователей добавлена readiness probe 
с HTTP GET запросом /user-service/hello/

## Предварительные работы

Развернут контейнер registry и зарегистрирован образ сервиса регистрации пользователей

*docker run -d -p 5000:5000 --restart=always --name registry registry:2*
<br>
*docker build -t user-service .*
<br>
*docker tag user-service localhost:5000/user-service:0.1*
<br>
*docker push localhost:5000/user-service:0.1*

## Развертывание

Создан helm chart с шаблонами service и deployment. 
<br>
[helm chart прилагается](./user-service)

Проведено развертывание сервиса:
<br>
*helm install user-service ./deployment/user-service*

### Проверка результатов развертывания

![screenshot](images/docker_desktop_containers.jpg)
<br><br>
*kubectl describe deployment user-service*

![screenshot](images/kubectl_describe_deployment.jpg)
<br><br>
*kubectl describe service user-service*

![screenshot](images/kubectl_describe_service.jpg)

### Проверка работы сервиса

Проведено нагрузочное тестирование сервиса регистрации пользователей для процесса сохранения нового пользователя.

Параметры плана тестирования:
* Количество пользователей: 1000
* Запуск запросов всех пользователей: в течение 10 секунд
* Количество повторений: 10

Сервис прошел нагрузочное тестирвание, успешно обработав все запросы.
Запросы распределялись балансировщиком между двумя экземплярами сервиса.

Логи экземпляра №1

![screenshot](images/docker_desktop_instance_1.jpg)
<br><br>
Логи экземпляра №2

![screenshot](images/docker_desktop_instance_2.jpg)