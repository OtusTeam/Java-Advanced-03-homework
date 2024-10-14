# ДЗ-17
## moryakovdv

## resilience4j

## Стек:
JDK 17  
Maven  
Spring-boot  
ApacheJMeter  
resilience4j  

## Основные классы
- otus.moryakovdv.task17.web.UserController - обеспечивает рест-сервисы для работы с регистрацией, логином пользователя  
- otus.moryakovdv.task17.jmeter.EmbeddedJmeter - обеспечивает запуск нагрузочного скрипта task17.jmx без GUI Apache JMeter   
- task17.jmx - скрипт тестироввания сервиса с профилем изменения нагрузки  

## Сборка
*$ mvn clean install*

## Запуск

*$ ./startup.sh* - общий запускающий скрипт  

## Usage

http://localhost:8888/getUserAge?id=15

## Результаты 












