# Поиск утечки памяти в приложении

приложение с утечкой памяти на SpringBoot.
При запуске приложения эмулятор запросов начинает отправку запросов на регистрацию пользователя, после чего приложение
падает с OutOfMemoryError

### Project conf:

JDK 17, Maven, SpringBoot,

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.2.5/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.2.5/maven-plugin/reference/html/#build-image)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.2.5/reference/htmlsingle/index.html#data.sql.jpa-and-spring-data)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.2.5/reference/htmlsingle/index.html#web)

### Отчет

* реализовано приложение выдающее java.lang.OutOfMemoryError: Java heap space
* причина заключается в использовании в строке
  com/example/outofmemorytask/config/ApplicationConfig.java:24
  java.util.concurrent.Executors.newCachedThreadPool()
  и сохранении всех пользователей в лист
  com.example.outofmemorytask.service.impl.UserServiceImpl.allUserEntities
* Данная ошибка была выявлена путем пересмотра бизнес логики - а именно:

- использования лишних данных, которые можно вынести на сторону запроса в БД(к примеру вместо коллекции всех
  пользователей, которых мы берем из БД, мы можем одним запросом проверить наличие пользователя в БД),
- сокращения количества нагрузки на БД
- Сокращения нагрузки на jvm, бутем ограничения количества потоков в ThreadPoolExecutor
  После оптимизации логики сервиса ошибка устранилась

* Скриншот ниже
  ![outofmemory exc.png](src%2Fmain%2Fresources%2Foutofmemory%20exc.png)
  ![outofmemory exc(heap dump_1).png](src%2Fmain%2Fresources%2Foutofmemory%20exc%28heap%20dump_1%29.png)
  ![outofmemory exc(heap dump_2).png](src%2Fmain%2Fresources%2Foutofmemory%20exc%28heap%20dump_2%29.png)

