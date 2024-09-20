# ДЗ-9
## moryakovdv

## AsyncProfiler
Профилирование приложения с помощью AsyncProfiler  



## Стек:
JDK 17  
Maven  
Spring-boot    
ApacheJMeter  

Async-profiler-1.7 для Ubuntu

Использован дополнительный пакет к JDK для включения TLAB:  
*apt install openjdk-17-dbg*


## Основные классы
- otus.moryakovdv.task9.web.UserController - обеспечивает рест-сервисы для работы с регистрацией, логином пользователя  
- otus.moryakovdv.task9.jmeter.EmbeddedJmeter - обеспечивает запуск нагрузочного скрипта task9.jmx без GUI Apache JMeter   
- task9.jmx - скрипт тестирования сервиса 

## Сборка
*$ mvn clean install*

Дополнительные инструкции для профилирования вызовов процессов ядра и разрешения указателей
*sudo sh -c 'echo 1 >/proc/sys/kernel/perf_event_paranoid'*
*sudo sh -c 'echo 0 >/proc/sys/kernel/kptr_restrict'*
 

## Запуск
*$ java -jar api9/target/api9.jar * - запуск Springboot-приложения 
*$ java -jar embeddedjmeter9/target/embeddedjmeter9.jar* - запуск тестирования

*$ ./startup.sh* - общий запускающий скрипт  

## Usage

http://localhost:8082/login?userName=test&passwd=test&createIfAbsent=true  
Процедура логина пользователя с переданными в QueryString параметрами.  
с флагом createIfAbsent=true создает нового пользователя, если такого нет.  

## Результаты 

1. Запуск  на выполнение осуществлен с дополнительными ключами: 
-XX:+UnlockDiagnosticVMOptions 
-XX:+DebugNonSafepoints
-XX:+UnlockExperimentalVMOptions 
-XX:+PrintFlagsFinal

*java -XX:+UnlockDiagnosticVMOptions -XX:+DebugNonSafepoints -XX:+UnlockExperimentalVMOptions -XX:+PrintFlagsFinal-jar api9/target/api9.jar*

2. Далее acync-profiler запускался на pid процесс с ключами профилирования CPU на 30 секунд с выводом flame-chart
   ![image](https://github.com/user-attachments/assets/4db7d4f6-835e-4231-96f3-e87798873ee6)


3. Профиль нагрузочного тестирования сервиса запускался через 
*java -jar embeddedjmeter9/target/embeddedjmeter9.jar*

На скриншотах приведены результаты из flame-chart для метода processContent.
В первом случае(см. sync.png) метод синхронизирован через *synchronized* и добавлена инструкция Thread.sleep для эмуляции задержки при выполнении
Во втором случае(см. no-sync.png) синхронизация и задержки убраны, на flame-chart видно, что JVM_sleep больше не в топе процессорного времени.
![image](https://github.com/user-attachments/assets/66b721c4-e8c0-4e28-ac31-757ce2874ff8)
![image](https://github.com/user-attachments/assets/5098ca63-8800-4435-ad37-1ec7d1c30606)


























