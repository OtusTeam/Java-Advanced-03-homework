#c DockerHub скачать JDK нужной версии
FROM openjdk:17-oracle
# Создать целевой каталог 
RUN mkdir /task12
#Скопировать приложение в контейнер
COPY ./target/api12.jar /task12/api12.jar
#открыть порт 8082
EXPOSE 8082
#запустить приложение при запуске контейнера
ENTRYPOINT [ "java", "-jar", "/task12/api12.jar" ]