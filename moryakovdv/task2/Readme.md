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


Суть ДЗ - изначально заложить утечку памяти, которая при многократных вызовах веб-сервиса в конце-концов приводила бы к OutOfMemoryError.
Далее, с помощью средств нагрузочного тестирования, получения и анализа дампа памяти выяснить возможное место утечки и исправить отдельном коммитом.

## Выполнение:
1. Обнаружение ошибки при выполнении в консоли IDE ![oom](https://github.com/OtusTeam/Java-Advanced-homework/assets/14349345/1cb5fdd2-3c58-4634-93bd-55cd5aa39bec)
2. Запуск приложения с ключом -XX:HeapDumpOnOutOfMemoryError
3. Подключение Apache Jmeter для нагрузочного траффика и VisualVM для мониторинга ![load-testing](https://github.com/OtusTeam/Java-Advanced-homework/assets/14349345/67aba4ca-5c81-489f-a7d0-584a9782f4d7)
4. Загрузка полученного дампа памяти в Eclipse memory Analyzer, просмотр дерева DominatorTree, обнаружение HashMap с большим количеством Entry
![mat-screenshot](https://github.com/OtusTeam/Java-Advanced-homework/assets/14349345/afe377da-9438-4f2f-a9d9-d22b196604f0)
5. Замена HashMap на WeakHashMap
6. Повторный анализ Apache Jmeter + VisualVM ![oom-fixed](https://github.com/OtusTeam/Java-Advanced-homework/assets/14349345/e126ec4e-b563-4039-9f98-fd5063ebe2c7)



 
