FROM openjdk:17-oracle

# Configure working directory
RUN mkdir /app

COPY ./target/metrics-1.0-SNAPSHOT.jar /app/metrics.jar

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "/app/metrics.jar" ]