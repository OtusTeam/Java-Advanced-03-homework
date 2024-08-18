# устанавливаем самую версию JVM
FROM eclipse-temurin:17
RUN mkdir /opt/app
COPY target/reactor-application-1.0.jar /opt/app/reactor-application-1.0.jar

# внешний порт, по которому наше приложение будет доступно извне
EXPOSE 80
CMD ["java", "-jar", "/opt/app/reactor-application-1.0.jar"]