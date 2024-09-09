# ДЗ-8
## moryakovdv

## JFR
Профилирование приложения с помощью JFR
Запуск приложения осуществляется с ключами активации журналирования JFR
-XX:StartFlightRecording:filename=task8.jfr
По завершению работы нагрузочного приложения протокол анализируется на предмет рабоыт с памятью, lock-aми и т.п. 

## Стек:
JDK 17  
Maven  
Spring-boot    
ApacheJMeter

## Основные классы
- otus.moryakovdv.task8.web.UserController - обеспечивает рест-сервисы для работы с регистрацией, логином пользователя
- otus.moryakovdv.task8.jmeter.EmbeddedJmeter - обеспечивает запуск нагрузочного скрипта task8.jmx без GUI Apache JMeter 
- task8.jmx - скрипт тестироввания сервиса с профилем изменения нагрузки


## Сборка
*$ mvn clean install*


## Запуск
*$ java -XX:StartFlightRecording:filename=task8-journal.jfr -jar api/target/api.jar * - запуск Springboot-приложения 
*$ java -jar embeddedjmeter/target/embeddedjmeter.jar* - запуск тестирования

*$ ./startup.sh* - общий запускающий скрипт

## Usage

http://localhost:8082/login?userName=test&passwd=test&createIfAbsent=true
Процедура логина пользователя с переданными в QueryString параметрами.
с флагом createIfAbsent=true создает нового пользователя, если такого нет.

## Результаты 
Для работы с журналом JFR использован плагин Java Mission Control к IDE Eclipse  
После старта приложения запускается профиль тестирования task8.jmx - три группы потоков * 10 юзеров (см. скриншот 1)   
После окончания нагрузочного теста приложение останавливается и записанный JFR анализируется в Java Mission Control     
1. В сигнатуру методов UserController намерено были добавлены синхронизаторы (скриншот 2) 
Записан файл task8-journal.jfr  
JFR и JMC отрегировали указанием на это в разделе Java Locking (скриншот 3 и 4)
    
2. Лишние синхронизаторы убраны, тесты запущены снова. 
Записан файл task8-journal-clean.jfr    
в отчетах JMC раздел Java Locking вернулся в норму, блокировка зафиксирована лишь на Spring-методах обращения к CRUD (скриншот 5 и 6)  
 







