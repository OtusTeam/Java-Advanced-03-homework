# ДЗ-15
## moryakovdv

## Open Api
Описание веб-сервиса с помощью Open API.  
Deploy SpringBoot приложения в Kubernetes с помощью Helm  

## Стек:
JDK 17  
Maven  
Spring-boot    
Open Api
Docker
Docker-desktop c подключенной опцией Kubernetes
helm для Ubuntu (snap-версия)

Open-api+Swagger 

## Основные классы

- otus.moryakovdv.task15.web.Task15Controller - обеспечивает рест-сервисы add, edit, get
Dockerile - файл описания настроек контейнера.  
## Сборка
*$ mvn clean package*

## Запуск
*$ java -jar api15/target/api15.jar * - запуск Springboot-приложения  
*$ ./startup.sh* - общий запускающий скрипт  - Выполняет билд и деплой контейнера в Kubernetes в соответсвии с описанием из файлов helm/Chart.yaml .  

## Usage
*$ ./startup.sh*

## Результаты 
*$ ./startup.sh*
1. Развертывание helm-chart-а
   ![image](https://github.com/user-attachments/assets/143c11de-7496-47d7-8b3d-86c6903f05eb)

2. Контейнер запущен, приложение доступно, порт 8082 проброшен на 8080
  ![image](https://github.com/user-attachments/assets/7f03c572-18ca-479a-a60e-194fee93a39d)

3. json-описание рест-методов доступно по http://localhost:8080/api-docs
  ![image](https://github.com/user-attachments/assets/30cf19d2-ab31-4a2e-a058-b4cd825a74e4)

4. графическое описание доступно по http://localhost:8080/api-docs-ui
![image](https://github.com/user-attachments/assets/857d8436-c848-4366-bea4-441e9fda7fcd)





























