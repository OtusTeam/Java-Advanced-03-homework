FROM openjdk:17-oracle

# Configure working directory
RUN mkdir /app

COPY ./target/openapi-0.0.1-SNAPSHOT.jar /app/openapi.jar

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "/app/openapi.jar" ]