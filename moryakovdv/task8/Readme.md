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
После старта приложения запускается профиль тестирования *task8.jmx* - три группы потоков * 10 юзеров (см. скриншот 1).   
После окончания нагрузочного теста приложение останавливается и записанный JFR анализируется в Java Mission Control.     
1. В сигнатуру методов UserController намерено были добавлены синхронизаторы (скриншот 2)  
Записан файл *task8-journal.jfr*  
JFR и JMC отрегировали указанием на класс `UserController` в разделе Java Locking (скриншот 3 и 4).  
    
2. Лишние синхронизаторы убраны, тесты запущены снова (скриншот 5).   
Записан файл *task8-journal-clean.jfr*    
в отчетах JMC раздел Java Locking вернулся в норму, блокировка зафиксирована лишь на Spring-методах обращения к CRUD (скриншот 6 и 7)  
 
![image](https://github.com/user-attachments/assets/c290dc17-8d44-40e2-beda-9f152b33b714)  
![image](https://github.com/user-attachments/assets/b55837c8-306e-4a66-a9af-f8be938266eb)  
![image](https://github.com/user-attachments/assets/270c6613-fc25-4d24-8227-3c9dd7afc3f7)  
![image](https://github.com/user-attachments/assets/c1dea3ff-2c1e-4ef5-b220-e9b1f99b2d5d)  
![image](https://github.com/user-attachments/assets/0c1f27b2-b8cf-453f-bdd4-7c1f4480145e)  
![image](https://github.com/user-attachments/assets/878f8b6f-5af5-486c-850d-c46bd980a48a)  
![image](https://github.com/user-attachments/assets/d82706a5-e948-4efd-b160-958366e463db)  












