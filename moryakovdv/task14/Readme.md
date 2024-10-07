# ДЗ-14
## moryakovdv

## Metrics, Prometheus, Grafana
Обвязка Spring-приложения метриками, создание Grafana dashboards

## Стек:
JDK 17  
Maven  
Spring-boot    
Docker  
Docker-desktop c подключенной опцией Kubernetes  
Spring-Actuator  

## Основные классы

- otus.moryakovdv.task14.web.WebController, UserController  - сервисы, обеспечивающие доступ к rest-api  
- Dockerfile - файл описания контейнера приложения.  
- infra/docker-compose.yml - файл описания композита из 3х контейнеров - приложения, сервера prometheus и сервера grafana.  
Каждый из них шарит общую сеть `otus`.  

## Сборка
*$ mvn clean package*

## Запуск
*$ java -jar api14/target/api14.jar * - запуск Springboot-приложения  
*$ ./startup.sh* - общий запускающий скрипт  - Выполняет запуск всех трех контейнеров запуском команды `docker-compose -p otus up`

## Usage
*$ ./startup.sh*

## Результаты 
*$ ./startup.sh*
1. Билд и запуск успешно прошли
  ![image](https://github.com/user-attachments/assets/de0df6e5-cda0-44be-ac89-b85da16eda85)

2. Приложение доступно по проброшенному порту 8082
  ![image](https://github.com/user-attachments/assets/71517401-10bb-4a17-9f34-4749ec0fe685)

3. Внутри работают эндпоинты /actuator/prometheus
   ![image](https://github.com/user-attachments/assets/84d12c3d-bb4b-4b5c-b96c-f5a60ad96e10)

4. сервер метрик prometheus доступен по порту 9090
   ![image](https://github.com/user-attachments/assets/178f884f-39df-465f-8bee-94582758acec)

5. Графана доступна на порту 3000, Дашбоард загружен по описанию из файла otus.json
    ![image](https://github.com/user-attachments/assets/3b94f53e-8ebf-40de-bec5-884b47ba6753)

6. Нагрузка, поданная через Apache Jmeter отображется на чартах поступающих запросов (рейт, количество удачных,неудчных и т.п.)
   ![image](https://github.com/user-attachments/assets/5ac75ead-fdc2-4105-8a7b-ede045868cf4)
   
   ![image](https://github.com/user-attachments/assets/5ecf1dc7-ffeb-4a6d-992b-e4c7040a0533)


































