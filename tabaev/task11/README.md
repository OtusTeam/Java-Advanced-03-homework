# Реализация сервиса с использование реактивного подхода

## Стек

Java 17, H2, Spring WebFlux, R2DBC, Project Reactor, Spring Security

## Запуск сервиса

```bash
mvn clean spring-bot:run
```

H2 In Memory Database доступна на порту 8082

## API Endpoints

Все эндпоинты работают реактивно с использованием Spring WebFlux.

### User Registration

- **Endpoint**: `/user/registration`
- **Method**: `POST`
- **Description**: Регистрация нового пользователя.
- **Request Body**:
    ```json
    {
      "login": "string",
      "password": "string"
    }
    ```
- **Response**:
    ```json
    {
      "id": "long",
      "login": "string",
      "success": true
    }
    ```

### User Login

- **Endpoint**: `/user/login`
- **Method**: `POST`
- **Description**: Авторизация пользователя.
- **Request Body**:
    ```json
    {
      "login": "string",
      "password": "string"
    }
    ```
- **Response**:
    ```json
    {
      "login": "string",
      "success": true
    }
    ```

### Find All Users

- **Endpoint**: `/user`
- **Method**: `GET`
- **Description**: Получение списка всех пользователей.
- **Response**:
    ```json
    [
      {
        "id": "long",
        "login": "string"
      }
    ]
    ```
