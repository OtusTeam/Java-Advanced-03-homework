# Запуск SpringBoot приложения в Docker

## Стек

Java 17, H2, Spring WebFlux, R2DBC, Project Reactor, Spring Security, Docker

## Сборка jar

Перед сборкой Docker-образа необходимо выполнить команду сборки Maven:

```bash
mvn clean install
```

## Сборка Docker-образа
```bash
docker build -t task-12:0.0.1 -f Dockerfile .
```

## Запуск контейнера
```bash
docker run -p 8080:8080 -p 8082:8082 task-12:0.0.1
```

Эта команда запустит контейнер и сопоставит порты:

- **8080**: основной порт приложения.
- **8082**: порт, на котором доступна база данных H2.
