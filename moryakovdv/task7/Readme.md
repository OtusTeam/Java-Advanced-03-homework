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

## результаты запуска теста

см. файлы jmeter.log

Скриншот






