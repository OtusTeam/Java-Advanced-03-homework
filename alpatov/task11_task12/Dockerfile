FROM bellsoft/liberica-openjdk-alpine-musl:21
ARG JAR_FILE=target/task11_task12-3.3.0.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]