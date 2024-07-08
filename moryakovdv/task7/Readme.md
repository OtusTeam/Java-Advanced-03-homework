# ДЗ-7
## moryakovdv

## JMeter и нагрузочное тестирование
1. Реализован модуль с Springboot-приложением, предоставляющее сервис регистрации пользователя
2. Модуль embeddedjmeter реализует запуск скрипта нгрузочного тестировния в Headless-режиме с использованием библиотек ApacheJMeter_core, ApacheJMeter_http и других

## Стек:
JDK 17  
Maven  
Spring-boot  
Jetty  
ApacheJMeter

## Основные классы
- otus.moryakovdv.task7.web.UserController - обеспечивает рест-сервисы для работы с регистрацией, логином пользователя
- otus.moryakovdv.task7.jmeter.EmbeddedJmeter - обеспечивает запуск нагрузочного скрипта task7.jmx без GUI Apache JMeter 
- task7.jmx - скрипт тестироввания сервиса с профилем изменения нагрузки

## Сборка
*$ mvn clean install*


## Запуск
*$ java -jar api/target/api.jar* - запуск Springboot-приложения
 
*$ java -jar embeddedjmeter/target/embeddedjmeter.jar* - запуск тестирования


## Скриншоты

Профиль и результаты тестирования из GUI
![Selection_1380](https://github.com/OtusTeam/Java-Advanced-homework/assets/14349345/f445cdce-a4ce-4e94-9b0e-8e53539bca57)
![Selection_1381](https://github.com/OtusTeam/Java-Advanced-homework/assets/14349345/f20172d6-5bf6-4f7f-8318-f83a3368a8ba)
![Selection_1382](https://github.com/OtusTeam/Java-Advanced-homework/assets/14349345/df99363c-6aff-45f1-81d8-5ce416c77aed)


## результаты запуска теста

см. файлы jmeter.log

Скриншот
![Selection_1383](https://github.com/OtusTeam/Java-Advanced-homework/assets/14349345/b826d125-e3a8-4958-abab-9afe87fc6a71)






