# Сервис для работы с данными пользователей и продуктов с использованием gRPC

Приложение на spring boot с использованием gRPC для работы с данными пользователей и продуктов.
В проект добавлена зависимость ru.otus.user-service-proto-common для использования классов GRPC сервисов, сгенерированных ранее.

## Запуск
*java -jar users-service-proto-server-0.1.jar* 


## Методы сервиса и демонстрация их работы

В качестве gRPC клиента использовался Postman 

#### Добавление нового пользователя

![screenshot](images/user_service_proto_create_user.jpg)

#### Добавление нового продукта

![screenshot](images/user_service_proto_create_product.jpg)

#### Изменение адреса электронной почты пользователя

![screenshot](images/user_service_proto_change_user_email.jpg)

#### Изменение имени пользователя

![screenshot](images/user_service_proto_change_user_name.jpg)

#### Добавление продукта в карту пользователя 

![screenshot](images/user_service_proto_add_product_to_card.jpg)

#### Логи

Результаты выполнения перечисленных выше методов в указанном порядке

![screenshot](images/user_service_proto_logs.jpg)