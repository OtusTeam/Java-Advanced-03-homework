# ДЗ-8
## moryakovdv

## JFR
Профилирование приложения с помощью JFR
Запуск приложения осуществляется с ключами активации журналирования JFR
-XX:StartFlightRecording:filename=task8.jfr
По завершению работы нагрузочного приложения протокол анализируется на предмет рабоыт с памятью, lock-aми и т.п. 
Намеренно внесены "ложные" вызовы методов, вносящих рандомную задержку во время выполнения функций.

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
*$ java -jar api/target/api.jar -XX:StartFlightRecording:filename=task8.jfr* - запуск Springboot-приложения
 
*$ java -jar embeddedjmeter/target/embeddedjmeter.jar* - запуск тестирования


## Скриншоты




## результаты запуска теста




