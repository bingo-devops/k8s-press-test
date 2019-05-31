FROM openjdk:8-jdk

ARG JAR_FILE

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "-Djava.security.egd=file:/dev/urandom", "/app.jar"]
