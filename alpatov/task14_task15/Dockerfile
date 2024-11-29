FROM bellsoft/liberica-openjdk-alpine-musl:21
ARG JAR_FILE=target/task14_task15-3.3.0.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]