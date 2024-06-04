Application to reproduce OutOfMemoryError.  

Java version 17, Gradle, Spring Boot 3.2

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

