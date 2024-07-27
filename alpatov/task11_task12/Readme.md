# Реактивное программирование: Reactor + Вспоминаем Docker

## Описание

Реализация сервиса с использованием реактивного подхода и Запуск SpringBoot приложения в Docker

Для создания jar файла  
```mvn package```

Для сборки образа:  
```docker build -t task11-12_image .```

Для запуска образа:  
```docker run -d -p 80:8080 task11-12_image ```


Для запуска с Postgres:   
```docker-compose up```

Для проверки:  
```curl -X GET --location "http://localhost:80/hello"```
