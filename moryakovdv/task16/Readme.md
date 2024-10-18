# ДЗ-16
## moryakovdv

## gRPC и Protobuf
Реализация удаленных вызовов процедур с использованием протокола Protobuf и фреймворка gRPC.

## Стек:
JDK 17  
Maven  
Protobuf compiler для Ubuntu из пакетов:  ```sudo apt-get install protobuf-compile```  

## Основные классы
Пакет *proto*	- Описание структуры пакетов и прототипов вызовов функций. При сборке с помощью protobuf-compiler генерируются классы моделей передаваемых данных, а также заглушки с методами (stubs) для дальнейшей фактической реализации.  
 	- *UserService.proto* - модель данных "клиент" и методы работы  ней  
 	- *ProductService.proto* - модель данных "товар" и методы работы с ней  
 	- *UserProductService.proto* - модель данных "корзина"  
Пакет *grpcServer* - имплементация серверной части "обслуживания" клиентских вызовов.  
 	- *UserController.java* - Работа с клиентами( пользователями)  
 	- *ProductController.java* - Работа с товарами и "корзиной"  
 	- *ServerApplication.java* - Запуск сервера на *localhost:8888* и ожидание запросов  
Пакет *grpcClient* - имплементация "запросной" части - клиента  
  	- *ClientApplication.java* - имитация клиентского приложения, осуществляет вызовы на *localhost:8888* различных методов на сервере  

## Сборка
*$ mvn clean package*

## Запуск
*$ ./startup.sh* - общий запускающий скрипт  - Выполняет билд и последовательный запуск  grpc-сервера и клиента 

## Usage
*$ ./startup.sh*

## Результаты 
*$ ./startup.sh*
1. Сборка java-файлов из .proto
   ![image](https://github.com/user-attachments/assets/7586b172-c887-4754-9a09-0c0040a129b1)

Результат:
![image](https://github.com/user-attachments/assets/b3568f32-50d2-4848-b97d-a7abaa19eeb3)

   

3. Сборка всего проекта
   ![image](https://github.com/user-attachments/assets/22bff122-87a6-488a-9b9e-c496c461aab5)

4. Запуск и готовность grpc-сервера к приему запросов
   ![image](https://github.com/user-attachments/assets/f4f483f9-461b-457a-b8ab-84c1f34997c3)

5. Запуск и последовательное выполнение клиентом вызовов серверных функций:
- создание пользователя
- изменение имени пользователя
- изменение email пользователя
- создание товара
- привязка товара к корзине пользователя
![image](https://github.com/user-attachments/assets/55a9cb56-0686-4787-b394-a3bb39a63b7f)

  




























