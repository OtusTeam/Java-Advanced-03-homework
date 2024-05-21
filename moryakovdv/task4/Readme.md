# ДЗ-4
## moryakovdv

# java modules
Проект предоставления данных о текущих погодных условиях.


## Стек:
JDK 17,  
SpringBoot  
Maven  

# Сборка
*$ mvn clean package*

# Запуск
*$ java -jar weatherapi-jar-with-dependencies.jar*

# Вызов
*http://localhost:8080/weather*
Возвращает идентификатор записи, помещенной в хранилище

## Оcновные модули и классы:
Модульный проект.  
**- api** - основной запускаемый модуль, содержит main и контроллеры для внешнего вызова  
Class Application - Запускающий класс  
Class Api - контроллер с веб-методами   

**- service** - сервис преобразования данных от модуля provider  
Class WeatherService - методы получения данных о погоде от модуля provider, преобразования их в WeatherConditions и передачи в модуль Core  

**- provider** - поставщик данных о текущих погодных условиях  
Class WeatherProvider - методы генерации данных о текущих погодных условиях  

**- core** - запись в хранилище данных на момент запроса.  
Class WeatherEntity - сущность "текущая погод" для помещения в хранилище   
Class Core - методы принимают WeatherConditions от модуля service, преобразует их в WeatherEntity и кладет в HasMap, возвращая идентификатор записи.  

**- weatherdata** - модуль "shared data", используется в Service и core  
Class WeatherConditions - представление текущей погоды в виде enum-a и вывод "human-readable" имени.  




