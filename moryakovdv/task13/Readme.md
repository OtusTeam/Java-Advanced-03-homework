# ДЗ-13
## moryakovdv

## Kubernetes 
Deploy SpringBoot приложения в Kubernetes с помощью Helm

## Стек:
JDK 17  
Maven  
Spring-boot    
Docker
Docker-desktop c подключенной опцией Kubernetes
helm для Ubuntu (snap-версия)
## Основные классы

- otus.moryakovdv.task13.web.WebController - обеспечивает рест-сервис /hello
Dockerile - файл описания настроек контейнера.  
## Сборка
*$ mvn clean package*

## Запуск
*$ java -jar api13/target/api13.jar * - запуск Springboot-приложения  
*$ ./startup.sh* - общий запускающий скрипт  - Выполняет билд и деплой контейнера в Kubernetes в соответсвии с описанием из файлов helm/Chart.yaml .  

## Usage
*$ ./startup.sh*

## Результаты 
*$ ./startup.sh*






























