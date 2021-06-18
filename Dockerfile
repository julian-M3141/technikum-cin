FROM openjdk:11-jre-slim

LABEL company="technikumwien.at" author="Julian"

WORKDIR /app

COPY target/person.jar .

CMD [ "java", "-jar", "person.jar" ]
