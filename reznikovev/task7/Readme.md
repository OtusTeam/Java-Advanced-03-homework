Генератор нагрузки для выбранного протокола

1. Тест через UI-ный интерфейс Jmeter'a
   ![img1.jpg](img1.jpg)
   ![img2.jpg](img2.jpg)


2. Тест через отдельный подмодуль с библиотекой Jmeter

   ```
   mvn spring-boot:run -f task2/pom.xml
   mvn clean verify -pl task7 "-Djmeter.skipTests=false"
   ```

   ![img3.jpg](img3.jpg)
      

