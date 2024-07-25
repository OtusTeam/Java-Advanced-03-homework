FROM bellsoft/liberica-openjre-alpine:17

ENV HOME_DIR="/app"
ENV SRC_APP_NAME="./target/task12_docker*.jar"
ENV APP_NAME="app.jar"
ENV FULL_APP_NAME="${HOME_DIR}/${APP_NAME}"

RUN mkdir -p ${HOME_DIR}
WORKDIR ${HOME_DIR}
COPY ${SRC_APP_NAME} "${FULL_APP_NAME}"
EXPOSE 8080

CMD java -jar ${FULL_APP_NAME}
