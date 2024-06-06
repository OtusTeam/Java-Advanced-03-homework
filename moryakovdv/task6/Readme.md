# ДЗ-6
## moryakovdv

## Java Microbenchmark Harness
Springboot-приложение с несколькими веб-сервисами.  
Замеры производительности для различных реализаций хеширования паролей  
Также тестируются классы *JMHSample_21_ConsumeCPU, JMHSample_27_Params, JMHSample_33_SecurityManager* из библиотеки примеров JMH Oracle  


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

## Сборка
*$ mvn clean verify*

## Запуск
*$ java -jar jmh.jar* - запуск с параметрами по-умолчанию, вывод результатов тестирования в консоль.  
  
*$ ./startup.sh* - shell-скрипт перенаправляющий вывод результатов в лог-файл 

## Результаты

При сравнении средневзвешенного времени выполнения среди трех алгоритмов хеширования более производительным представляется реализация SHA256Hasher.  
![image](https://github.com/OtusTeam/Java-Advanced-homework/assets/14349345/1bcd5c6b-3dce-411d-ac8c-5b48459f03f0)

В целом на одних и тех же входных данных *The quick brown fox jumps over the lazy dog* показаны схожие результаты.  

Полные результаты тестов в файлe *benchmark.log*







