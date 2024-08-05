FROM bellsoft/liberica-openjre-alpine:17.0.5
ENV TZ=Europe/Moscow
WORKDIR /apps
EXPOSE 8080
COPY /target/*.jar /apps/user-service.jar
ENTRYPOINT ["sh", "-c", "java -jar user-service.jar"]