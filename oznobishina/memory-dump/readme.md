Application to reproduce OutOfMemoryError.  

Java version 17, Gradle, Spring Boot 3.2

Run with vm options: -Xmx100m -XX:+HeapDumpOnOutOfMemoryError
After application started call localhost:8080/customer/login using jmeter to reproduce 
java.lang.OutOfMemoryError: Java heap space.

Heap dump will be created automatically.

# Задание 2
Поиск утечки памяти в приложении
Цель:
Создать тестовой приложение с утечкой памяти и найти её с помощью специальных инструментов

Описание/Пошаговая инструкция выполнения домашнего задания:
1. Реализоавть простое приложение на spring boot:
1.1 Сервис регистрации пользователя в системе: rest service принимающий login и password от пользователя
1.2 Для хранение данных использовать БД H2
1.3 Для доступа к данным использовать Spring JPA
2. Заложить проблему, вызывающую OutOfMemoryError. Примечание: приложение должно постепенно копить мусор в течение нескольких минут
3. Запускать приложение с инструкцией, позволяющей собирать дамп хипа перед падением
4. Провести анализ дампа инструментам Eclipse Memory Analyzer Tool, найти утечку, и предоставить скиншот того места, 
где можно сделать вывод об утечку (с комментариями, поясняющими почему вы считаете это место утечкой)
5. Поправить утечку памяти (отдельным коммитом в пулл реквесте).

P.S. Может показаться, что нужно реализовать лишнее, но данное приложение вы будете использовать в следующих ДЗ, докручивая по заданию.

Критерии оценки:
Написано приложение, которое после запуске падает через несколько минут с дампом памяти
В отчёте есть скриншоты Eclipse Memory Anylizer Tool, указание строк кода приложения и с объяснениями, на основании каких данных сделан вывод об утечке и его место положении.
Статус "Принято" ставится при выполнении всех пунктов

Map занимает большую часть выделенной памяти, это видно в столбце Retained heap, то есть объекты лежашие в
мапе и доступные через нее заняли много места.
![img.png](img.png)



# Задание 3
Домашнее задание
CDS с использованием Spring

Цель:
Создать архив CDS и оценить прирост времени старта
Описание/Пошаговая инструкция выполнения домашнего задания:
1. Использовать приложение из предыдущего задания
2. Доработать приложение: модель в H2 стоит описать в виде скрипта миграции liquibase (можно flyway)
3. Замерить время старта приложения
4. Создать CDS архив
5. Запустить приложение с CDS
6. Оценить ускорение старта приложения
   7.* Добиться более существенной экономии при старте за счёт использования бОльшего числа классов (библиотек), загруженных к моменту формирования CDS архива.

Критерии оценки
Есть исходный код приложения и инструкции запуска без CDS и с включённым CDS
В отчёте присутствуют времена старта приложения в обоих случая и есть оценка ускорения старта приложения
Статус ""Принято"" ставится при выполнении всех пунктов без звездочки"



### Без CDS
Время старта приложения:
#### Started MemoryDumpApplication in 3.299 seconds (process running for 3.776)
Started MemoryDumpApplication in 3.32 seconds (process running for 3.812)

### C CDS
Error: 
Must enable AllowArchivingWithJavaAgent in order to run Java agent during CDS dumping
Solution: add flags -XX:+UnlockDiagnosticVMOptions -XX:+AllowArchivingWithJavaAgent

Вести лог: -Xlog:class+load:file=cds_off.log - тут видно откуда загрузились классы
Включить использование архива-Xshare:on

Запускать приложение с VM-options
-Xmx100m
-Xshare:off (или on)
-XX:+UnlockDiagnosticVMOptions
-XX:+AllowArchivingWithJavaAgent
-Xlog:class+load:file=cds_off.log

Время старта приложения:
#### Started MemoryDumpApplication in 3.123 seconds (process running for 3.504)
Started MemoryDumpApplication in 3.138 seconds (process running for 3.63)
Started MemoryDumpApplication in 3.204 seconds (process running for 3.592)

Ускорение старта приложения около 200 мс))

