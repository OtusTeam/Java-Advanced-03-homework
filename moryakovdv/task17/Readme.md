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
- otus.moryakovdv.task17.web.UserController - обеспечивает рест-сервисы для работы с пользователем  
- otus.moryakovdv.task17.web.UserService - содержит бекэнд-методы, защищенные от перегрузки с помощью аннотаций Resilience4j
- otus.moryakovdv.task17.web.UserServiceExceptionHandlers - handler-ы ошибок, перехыватывающие исключения от Resilience4j
- otus.moryakovdv.task17.jmeter.EmbeddedJmeter - обеспечивает запуск нагрузочного скрипта task17.jmx без GUI Apache JMeter   
- task17.jmx - скрипт тестирования сервиса с профилем изменения нагрузки

- application.properties - содержит настройки для каждого механизма защиты от перегрузки 

## Сборка
*$ mvn clean install*

## Запуск

*$ ./startup.sh* - общий запускающий скрипт  

## Usage

http://localhost:8888/getUserAge?id=15

## Результаты 

Нагрузочный скрипт настроен на вызов трех методов, каждый из которых возвращает возраст пользователя, но защищен разным механизмом:
 - RateLimit (запросов/сек) - отменяет вызов метода, если превышен порог 20 запросов/сек  
 - RateLimit (запросов/мин) - отменяет вызов метода, если превышен порог 30 запросов/мин  
 - CircuitBreaker - отменяет вызов метода, если более 50% вызовов метода в течение заданного скольщящего окна завершились с ошибкой  

Скриншоты Jmeter и сообщений об ошибках
1. Circuit Breaker закрылся после нескольких неуспешных попыток вызвать метод
![image](https://github.com/user-attachments/assets/6def47cd-b54e-49f7-acb0-e6f16c342ba0)
![image](https://github.com/user-attachments/assets/8bad45bd-183e-402d-9577-a87354f3d3a1)
![image](https://github.com/user-attachments/assets/741ad8ef-1a62-41f3-a72d-a9307d01533a)

2. RateLimiter закрыл выполнение, после превышения порога запросов в секунду
   ![image](https://github.com/user-attachments/assets/841f4521-321d-4871-b48d-365e347ab2f9)
![image](https://github.com/user-attachments/assets/ed913b48-03ba-4f38-8a3c-0d524cb917e8)

  
3. Аналогично закрылся второй rateLimiter
   ![image](https://github.com/user-attachments/assets/e8c516a6-5190-4e75-b99d-d208fee48b68)
![image](https://github.com/user-attachments/assets/92bfbecb-12eb-4a84-9478-eaa7823db608)









