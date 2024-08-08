FROM bellsoft/liberica-openjre-alpine:17.0.5
ENV TZ=Europe/Moscow
WORKDIR /app
EXPOSE 8080
COPY /target/*.jar /app/app.jar
ENTRYPOINT ["sh", "-c", "java -jar app.jar"]
