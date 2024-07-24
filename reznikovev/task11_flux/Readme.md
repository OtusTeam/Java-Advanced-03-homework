Реализация кеширования пользователей через WebFlux

Запуск приложения и теста через Jmeter

   ```
   mvn spring-boot:run -f task11_flux/pom.xml
   mvn clean verify -pl task7 "-Djmeter.skipTests=false"
   ```
Вывод логов Jmeter

```log
    [INFO] Waiting for possible Shutdown/StopTestNow/HeapDump/ThreadDump message on port 4445
    [INFO] summary +      1 in 00:00:00 =    2,8/s Avg:   244 Min:   244 Max:   244 Err:     0 (0,00%) Active: 1 Started: 1 Finished: 0
    [INFO] summary +     33 in 00:00:28 =    1,2/s Avg:    19 Min:     6 Max:    71 Err:     0 (0,00%) Active: 20 Started: 20 Finished: 0
    [INFO] summary =     34 in 00:00:29 =    1,2/s Avg:    26 Min:     6 Max:   244 Err:     0 (0,00%)
    [INFO] summary +     49 in 00:00:30 =    1,6/s Avg:    16 Min:     5 Max:    50 Err:     0 (0,00%) Active: 20 Started: 20 Finished: 0
    [INFO] summary =     83 in 00:00:59 =    1,4/s Avg:    20 Min:     5 Max:   244 Err:     0 (0,00%)
    [INFO] summary +     51 in 00:00:30 =    1,7/s Avg:    14 Min:     4 Max:    29 Err:     0 (0,00%) Active: 20 Started: 20 Finished: 0
    [INFO] summary =    134 in 00:01:29 =    1,5/s Avg:    18 Min:     4 Max:   244 Err:     0 (0,00%)
    [INFO] summary +     49 in 00:00:30 =    1,6/s Avg:    13 Min:     5 Max:    24 Err:     0 (0,00%) Active: 20 Started: 20 Finished: 0
    [INFO] summary =    183 in 00:01:59 =    1,5/s Avg:    17 Min:     4 Max:   244 Err:     0 (0,00%)
    [INFO] summary +     51 in 00:00:30 =    1,7/s Avg:    13 Min:     3 Max:    25 Err:     0 (0,00%) Active: 20 Started: 20 Finished: 0
    [INFO] summary =    234 in 00:02:29 =    1,6/s Avg:    16 Min:     3 Max:   244 Err:     0 (0,00%)
    [INFO] summary +     49 in 00:00:30 =    1,6/s Avg:    12 Min:     3 Max:    20 Err:     0 (0,00%) Active: 20 Started: 20 Finished: 0
    [INFO] summary =    283 in 00:02:59 =    1,6/s Avg:    15 Min:     3 Max:   244 Err:     0 (0,00%)
    [INFO] summary +     51 in 00:00:30 =    1,7/s Avg:    11 Min:     4 Max:    20 Err:     0 (0,00%) Active: 20 Started: 20 Finished: 0
    [INFO] summary =    334 in 00:03:29 =    1,6/s Avg:    15 Min:     3 Max:   244 Err:     0 (0,00%)
    [INFO] summary +     49 in 00:00:30 =    1,6/s Avg:     6 Min:     3 Max:    19 Err:     0 (0,00%) Active: 20 Started: 20 Finished: 0
    [INFO] summary =    383 in 00:03:59 =    1,6/s Avg:    13 Min:     3 Max:   244 Err:     0 (0,00%)
    [INFO] summary +     51 in 00:00:30 =    1,7/s Avg:    11 Min:     3 Max:    22 Err:     0 (0,00%) Active: 20 Started: 20 Finished: 0
    [INFO] summary =    434 in 00:04:29 =    1,6/s Avg:    13 Min:     3 Max:   244 Err:     0 (0,00%)
    [INFO] summary +     49 in 00:00:30 =    1,6/s Avg:     9 Min:     3 Max:    15 Err:     0 (0,00%) Active: 20 Started: 20 Finished: 0
    [INFO] summary =    483 in 00:04:59 =    1,6/s Avg:    13 Min:     3 Max:   244 Err:     0 (0,00%)
    [INFO] summary +     51 in 00:00:30 =    1,7/s Avg:    11 Min:     3 Max:    19 Err:     0 (0,00%) Active: 5 Started: 20 Finished: 15
    [INFO] summary =    534 in 00:05:29 =    1,6/s Avg:    13 Min:     3 Max:   244 Err:     0 (0,00%)
    [INFO] summary +      4 in 00:00:02 =    2,6/s Avg:     8 Min:     5 Max:    12 Err:     0 (0,00%) Active: 0 Started: 20 Finished: 20
    [INFO] summary =    538 in 00:05:30 =    1,6/s Avg:    12 Min:     3 Max:   244 Err:     0 (0,00%)
    [INFO] Tidying up ...    @ 2024 Jul 24 15:35:02 MSK (1721824502482)
    [INFO] ... end of run
```
      

