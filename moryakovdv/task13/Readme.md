# ДЗ-13
## moryakovdv

## Kubernetes 
Deploy SpringBoot приложения в Kubernetes с помощью Helm

## Стек:
JDK 17  
Maven  
Spring-boot    
Docker
Docker-desktop c подключенной опцией Kubernetes
helm для Ubuntu (snap-версия)
## Основные классы

- otus.moryakovdv.task13.web.WebController - обеспечивает рест-сервис /hello
Dockerile - файл описания настроек контейнера.  
## Сборка
*$ mvn clean package*

## Запуск
*$ java -jar api13/target/api13.jar * - запуск Springboot-приложения  
*$ ./startup.sh* - общий запускающий скрипт  - Выполняет билд и деплой контейнера в Kubernetes в соответсвии с описанием из файлов helm/Chart.yaml .  

## Usage
*$ ./startup.sh*

## Результаты 
*$ ./startup.sh*

Процесс сборки и деплоя
1. ![image](https://github.com/user-attachments/assets/14fe4034-068c-4155-a209-5831fc7dade3)
2. ![image](https://github.com/user-attachments/assets/ec78dced-4f1f-402f-9140-b2c8f06feec8)
3. ![image](https://github.com/user-attachments/assets/84b86129-9ca0-48b6-b37f-6641d027dd29)
4. ![image](https://github.com/user-attachments/assets/4fb14f3f-dde6-4964-9c46-d8cb5577240b)

Контейнер запущен и виден в docker-desktop
![image](https://github.com/user-attachments/assets/76839aae-060e-46c8-a6d9-632df62edb29)































