# Реализация benchmark для алгоритма

Приложение на SpringBoot с подключенным Java Microbenchmark Harness.


### Project conf:

JDK 17, Maven, SpringBoot, JMH

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.2.5/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.2.5/maven-plugin/reference/html/#build-image)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.2.5/reference/htmlsingle/index.html#data.sql.jpa-and-spring-data)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.2.5/reference/htmlsingle/index.html#web)

### Отчет

* Реализованы тесты по замеру производительности с помощью JMH
Для запуска требуется произвести команду "mvn clean test"
* Скриншот и отчет JMH ниже

[jmh_benchmark_result](src%2Fmain%2Fresources%2Fjmh_benchmark_result)

![jmh_logs_result.png](src%2Fmain%2Fresources%2Fjmh_logs_result.png)
По не ясным причинам в текущем проекте JMH использует только Throughput mode
![photo_2024-07-25_20-45-59.jpg](..%2F..%2F..%2F..%2F..%2F..%2FUsers%2FAnton%2FDownloads%2FTelegram%20Desktop%2Fphoto_2024-07-25_20-45-59.jpg)
![photo_2024-07-25_20-46-08.jpg](src%2Fmain%2Fresources%2Fphoto_2024-07-25_20-46-08.jpg)

JMHSample_20_Annotations:
![JMHSample_20_Annotations.png](src%2Fmain%2Fresources%2FJMHSample_20_Annotations.png)****
JMHSample_26_BatchSize:
![JMHSample_24_Inheritance.png](src%2Fmain%2Fresources%2FJMHSample_24_Inheritance.png)
JMHSample_24_Inheritance
![JMHSample_26_BatchSize.png](src%2Fmain%2Fresources%2FJMHSample_26_BatchSize.png)
