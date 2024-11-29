FROM alpine:latest

RUN apk add openjdk17
WORKDIR /spring-user-service
COPY ./target/task12-0.0.1.jar task12.jar

EXPOSE 8080
EXPOSE 8082

CMD echo hello

ENTRYPOINT ["java", "-jar", "task12.jar"]
