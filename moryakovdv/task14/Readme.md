# ДЗ-14
## moryakovdv

## Metrics, Prometheus, Graphana
Обвязка Spring-приложения метриками, создание Graphana dashboards

## Стек:
JDK 17  
Maven  
Spring-boot    
Docker
Docker-desktop c подключенной опцией Kubernetes
Spring-Actuator

## Основные классы

- otus.moryakovdv.task14.web.WebController, UserController  - сервисы, обеспечивающие доступ к rest-api  
Dockerile - файл описания настроек контейнера.  

## Сборка
*$ mvn clean package*

## Запуск
*$ java -jar api14/target/api14.jar * - запуск Springboot-приложения  
*$ ./startup.sh* - общий запускающий скрипт  - Выполняет билд и деплой контейнера в Kubernetes в соответсвии с описанием из файлов helm/Chart.yaml .  

## Usage
*$ ./startup.sh*

## Результаты 
*$ ./startup.sh*
































