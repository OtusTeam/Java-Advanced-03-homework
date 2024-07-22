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

