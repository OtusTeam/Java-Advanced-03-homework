#c DockerHub скачать JDK нужной версии
FROM openjdk:17-oracle
# Создать целевой каталог 
RUN mkdir /task14
#Скопировать приложение в контейнер
COPY ./target/api14.jar /task14/api14.jar
#открыть порт 8082
EXPOSE 8082
#запустить приложение при запуске контейнера
ENTRYPOINT [ "java", "-jar", "/task14/api14.jar" ]