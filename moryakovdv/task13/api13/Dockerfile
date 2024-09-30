#c DockerHub скачать JDK нужной версии
FROM openjdk:17-oracle
# Создать целевой каталог 
RUN mkdir /task13
#Скопировать приложение в контейнер
COPY ./target/api13.jar /task13/api13.jar
#открыть порт 8082
EXPOSE 8082
#запустить приложение при запуске контейнера
ENTRYPOINT [ "java", "-jar", "/task13/api13.jar" ]