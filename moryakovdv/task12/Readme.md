# ДЗ-12
## moryakovdv

## Docker
Запуск Spring-приложения в Docker


## Стек:
JDK 17  
Maven  
Spring-boot    
Docker

## Основные классы

- otus.moryakovdv.task12.web.WebController - обеспечивает рест-сервис /hello

## Сборка
*$ mvn clean package*

## Запуск
*$ java -jar api12/target/api12.jar * - запуск Springboot-приложения  
*$ ./startup.sh* - общий запускающий скрипт  - Выполняет билд и зпуск контейнера с нужными параметрами.  

## Usage
*$ ./startup.sh*

## Результаты 
*$ ./startup.sh*

1. Запуск сборки и контейнера 
![image](https://github.com/user-attachments/assets/85c33590-b22c-43d2-a982-4a55e9e97ba4)

2. Приложение запускается вместе с контейнером
![image](https://github.com/user-attachments/assets/72c6cf88-1536-4123-9b35-6f338c8c7dfb)

3. Docker desktop видит созданный контейнер, порты проброшены
![image](https://github.com/user-attachments/assets/cde7626e-c35d-4c4d-89f7-0a95bf072616)

4. ответ на запрос рест-сервиса из Postman
![image](https://github.com/user-attachments/assets/9b95a1e1-aeac-4856-ac36-1b1c5c17fcca)




























