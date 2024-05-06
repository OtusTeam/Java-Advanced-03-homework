# Memory dump hw

### Стэк:
Spring Boot, Spring Data JPA, Liquibase, H2, Spring Boot Test

### Тестирование
Для тестирования приложения использовал JMeter.

<img alt="img_5.png" height="300" src="img_5.png" width="600"/>

### Параметры запуска приложения для получения OutOfMemoryError
-Xms80m -Xmx80m -XX:+UseParallelGC -XX:+HeapDumpOnOutOfMemoryError

### Анализ Eclipse MAT Dump
В ходе анализа dump было найдено 2 проблемы и первая из них указывала на утечку:

<img alt="img.png" height="300" src="img.png" width="600"/>

Построил dominator tree, отсортировал и нашел класс SimpleCacheImpl из приложения

<img alt="img_1.png" height="300" src="img_1.png" width="600"/>

Содержащаяся в нём Map содержит большое количество записей и занимает 41.39%:

<img alt="img_2.png" height="300" src="img_2.png" width="600"/>
