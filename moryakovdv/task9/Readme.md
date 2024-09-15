# ДЗ-9
## moryakovdv

## AsyncProfiler
Профилирование приложения с помощью AsyncProfiler

## Стек:
JDK 17  
Maven  
Spring-boot    
ApacheJMeter  

## Основные классы
- otus.moryakovdv.task9.web.UserController - обеспечивает рест-сервисы для работы с регистрацией, логином пользователя  
- otus.moryakovdv.task9.jmeter.EmbeddedJmeter - обеспечивает запуск нагрузочного скрипта task9.jmx без GUI Apache JMeter   
- task9.jmx - скрипт тестирования сервиса 

## Сборка
*$ mvn clean install*

## Запуск
*$ java -jar api9/target/api9.jar * - запуск Springboot-приложения 
*$ java -jar embeddedjmeter9/target/embeddedjmeter9.jar* - запуск тестирования

*$ ./startup.sh* - общий запускающий скрипт  

## Usage

http://localhost:8082/login?userName=test&passwd=test&createIfAbsent=true  
Процедура логина пользователя с переданными в QueryString параметрами.  
с флагом createIfAbsent=true создает нового пользователя, если такого нет.  

## Результаты 












