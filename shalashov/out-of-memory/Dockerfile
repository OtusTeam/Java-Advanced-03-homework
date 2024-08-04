FROM eclipse-temurin:17

RUN mkdir /opt/app
COPY ./target/out-of-memory-0.0.1-SNAPSHOT.jar /opt/app/out-of-memory.jar

EXPOSE 8081

CMD ["java", "-jar", "/opt/app/out-of-memory.jar"]