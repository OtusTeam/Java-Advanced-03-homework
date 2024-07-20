Требования: Java 17



## Задание:
1. Использовать приложение из предыдущего задания

2. Доработать приложение: модель в H2 стоит описать в виде скрипта миграции liquibase (можно flyway)

3. Замерить время старта приложения

4. Создать CDS архив

5. Запустить приложение с CDS

6. Оценить ускорение старта приложения

7. Добиться более существенной экономии при старте за счёт использования бОльшего числа классов (библиотек), загруженных к моменту формирования CDS архива.

## Как воспроизвести работу приложения:
1. ```java -Xshare:dump```
2. Run with option ```-Xshare:off -Xlog:startuptime```
   Create VM, 0.4560495 secs
   Started ApplicationLauncher in 5.661 seconds (process running for 6.665)
3. Run with option ```-Xshare:on -Xlog:startuptime```
   Create VM, 0.4820600 secs
   Started ApplicationLauncher in 5.142 seconds (process running for 6.146)
4.1 Run with option ```-XX:DumpLoadedClassList=classes.lst```
4.2 Run create_archive.sh
4.3 Run run_with_archive.sh
   Create VM, 0.4430532 secs
   Started ApplicationLauncher in 4.975 seconds (process running for 5.968)