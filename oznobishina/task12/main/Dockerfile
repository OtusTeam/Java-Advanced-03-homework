FROM openjdk:17-oracle

# Configure working directory
RUN mkdir /app

COPY ./target/main-0.0.1-SNAPSHOT.jar /app/main.jar

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "/app/main.jar" ]