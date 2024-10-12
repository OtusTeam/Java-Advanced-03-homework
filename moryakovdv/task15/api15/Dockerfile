#c DockerHub скачать JDK нужной версии
FROM openjdk:17-oracle
# Создать целевой каталог 
RUN mkdir /task15
#Скопировать приложение в контейнер
COPY ./target/api15.jar /task15/api15.jar
#открыть порт 8082
EXPOSE 8082
#запустить приложение при запуске контейнера
ENTRYPOINT [ "java", "-jar", "/task15/api15.jar" ]