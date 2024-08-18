# back
# устанавливаем самую версию JVM
FROM alpine:3.19

RUN export DOCKER_BUILDKIT=0
RUN export COMPOSE_DOCKER_CLI_BUILD=0

# указываем ярлык. Например, разработчика образа и проч. Необязательный пункт.
LABEL maintainer=rabota24021993@gmail.com
# указываем точку монтирования для внешних данных внутри контейнера (как мы помним, это Линукс)
VOLUME /tmp
# указываем, где в нашем приложении лежит джарник
ARG JAR_FILE=target/task11-reactor-1.0.jar
# указываем, envirement variables

#ENV DB_URL=jdbc:postgresql://data-builder-db:5432/reactor-app-data
#ENV DB_USERNAME=postgres
#ENV DB_PASS=pg123
# добавляем джарник в образ под именем rebounder-chain-backend.jar
ADD ${JAR_FILE} reactor-application.jar

# внешний порт, по которому наше приложение будет доступно извне
EXPOSE 80