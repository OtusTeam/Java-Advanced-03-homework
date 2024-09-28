# ДЗ-11
## moryakovdv

## React-ивный сервис
Веб-сервис регистрации пользователя с использованием WebFlux, React и R2dbcRepositories

## Стек:
JDK 17  
Maven  
Spring-boot    
WebFFlux
R2db
H2

## Основные классы
- otus.moryakovdv.task11.web.UserController - обеспечивает рест-сервисы для работы с регистрацией, логином пользователя  
- otus.moryakovdv.task11.repository.FluxUserRepository - неблокирующий репозиторий, имплементирующий R2dbcRepository<UserOtus, Integer>

## Сборка
*$ mvn clean install*


## Запуск
*$ java -jar api11/target/api11.jar * - запуск Springboot-приложения 
*$ ./startup.sh* - общий запускающий скрипт  

## Usage

http://localhost:8082/login?userName=test&passwd=test&createIfAbsent=true  
Процедура логина пользователя с переданными в QueryString параметрами.  
с флагом createIfAbsent=true создает нового пользователя, если такого нет.  

## Результаты 
Задача реализована с использованием неблокирующих библиотек WebFlux, r2dbc и БД H2.



























