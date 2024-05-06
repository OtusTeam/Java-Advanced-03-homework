# ДЗ-2
## moryakovdv

## Out of memory

Spring-приложение, реализующее несколько web-методов.  
Интерфейс с пользоваателем осуществляется через вызов соответсвующих URL
При вызове url /process?sessionId=<guid> происходит генерация Entry для случайного Integer-ключа и размещение ее в cache.
Значение Entry генерируется из содержимого файла LICENSE
Возвращает JSON с количеством выпоолненных запросов и попаданий в кеш

## Стек:
JDK 17  
Maven  
Spring-boot  
Jetty  

# Сборка
*$ mvn clean package*

# Запуск
*$ java -jar memory-dump.jar*  

## Оcновные классы:
- WebApplication - запускающий класс.  
- WebService - класс, обеспечивающий бизнес-логику веб-методов
- WebController - основной контроллер Spring для 
- User - сущность Пользователь. Хранится в in-memory H2 database
- UsersCrudRepository  - Spring JPA репозиторий для работы с БД


