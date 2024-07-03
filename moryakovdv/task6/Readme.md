# ДЗ-6
## moryakovdv

## Java Microbenchmark Harness
1. Springboot-приложение с несколькими веб-сервисами.  
2. Замеры производительности для различных реализаций хеширования паролей  
3. Тестируются классы *JMHSample_21_ConsumeCPU, JMHSample_27_Params, JMHSample_33_SecurityManager* из библиотеки примеров JMH Oracle  

4. Реализовано тестирование веб-сервисов через Junit c *Spring ApplicationContext*.  
Идея взята тут [https://gist.github.com/msievers/ce80d343fc15c44bea6cbb741dde7e45]

## Стек:
JDK 17  
Maven  
Spring-boot  
Jetty  
Java Microbenchmark Harness

## Основные классы
- Md5Hasher - хеширование строки  с использованием MD5
- SHA256Hasher - хеширование строки  с использованием SHA-256
- SHA512Hasher - хеширование строки  с использованием SHA-512
- ParentBenchmark и его наследники - инфраструктура для тестирования различных метрик производительности методов из классов-хешировщиков
- BenchmarkStarter - запускающий JMH-тесты класс.

- BenchmarkSpringBase - JUNIT-обертка для запуска JMH, позволяет доступиться к SpringContext в методах классов-наследниках
- UserServiceJmhBenchmark - класс JUNIT-теста, наследник от BenchmarkSpringBase, содержит метод тестирования производительности вызова login-сервиса

## Сборка
*$ mvn clean verify*



## Запуск
*$ java -jar jmh.jar* - запуск с параметрами по-умолчанию, вывод результатов тестирования в консоль.  
  
*$ ./startup.sh* - shell-скрипт перенаправляющий вывод результатов в лог-файл 

*$ mvn test* - запуск обычных Unit-тестов и BenchmarkSpringBase

## Результаты

На одних и тех же входных данных *The quick brown fox jumps over the lazy dog* при сравнении средневзвешенного времени выполнения среди трех алгоритмов хеширования более производительным ожидаемо является реализация MD5.  
![image](https://github.com/OtusTeam/Java-Advanced-homework/assets/14349345/541dfba8-c400-4f14-bef6-7901d66e1b2b)

Полные результаты тестов в файлe *benchmark.log*








