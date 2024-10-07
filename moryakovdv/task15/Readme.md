# ДЗ-15
## moryakovdv

## Open Api
Описание веб-сервис с помощью Open API.  
Deploy SpringBoot приложения в Kubernetes с помощью Helm  

## Стек:
JDK 17  
Maven  
Spring-boot    
Open Api
Docker
Docker-desktop c подключенной опцией Kubernetes
helm для Ubuntu (snap-версия)
## Основные классы

- otus.moryakovdv.task15.web.WebController - обеспечивает рест-сервис /
Dockerile - файл описания настроек контейнера.  
## Сборка
*$ mvn clean package*

## Запуск
*$ java -jar api15/target/api15.jar * - запуск Springboot-приложения  
*$ ./startup.sh* - общий запускающий скрипт  - Выполняет билд и деплой контейнера в Kubernetes в соответсвии с описанием из файлов helm/Chart.yaml .  

## Usage
*$ ./startup.sh*

## Результаты 
*$ ./startup.sh*






























