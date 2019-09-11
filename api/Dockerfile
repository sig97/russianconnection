FROM openjdk:8-jdk-alpine

VOLUME /tmp
# ARG JAR_FILE
# COPY ${JAR_FILE} app.jar
COPY build/libs/api*.jar /data/api.jar
WORKDIR /
EXPOSE 7979
ENTRYPOINT ["java", "-Dspring.profiles.active=localdocker", "-jar","/data/api.jar"]