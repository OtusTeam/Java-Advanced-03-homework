# JMH benchmarking

## Запуск тестов

*mvn clean test* 

## Тесты

### Общие параметры

* fork iterations: 0
* measurement iterations: 3
* warmup iterations: 3
* threads: 1
* output time unit: MICROSECONDS

Показатели производительности:
* AverageTime
* SampleTime
* SingleShotTime
* Throughput

### UserEncryptorBenchmark 

Проверяется производительность шифрования паролей пользователей по алгоритмам:
* MD5
* SHA-256
* SHA-512

Каждый алгоритм проверяется как с применением "соления", так и без.

##### Результат
![screenshot](images/UserEncryptorBenchmark.jpg)

### UserDaoBenchmark

Проверяется производительность процесса регистрации пользователя с сохранением в БД.
Инициализация spring и транспорт http rest не учитвыаются.

##### Результат
![screenshot](images/UserDaoBenchmark.jpg)

### Отчет по результатам
[Сгенерированный отчет по проведенным тестам](jmh-result.json)

## Примеры тестов OpenJDK

#### JMHSample_03_States
![screenshot](images/JMHSample_03_States.jpg)

#### JMHSample_18_Control
![screenshot](images/JMHSample_18_Control.jpg)

#### JMHSample_27_Params
![screenshot](images/JMHSample_27_Params.jpg)


