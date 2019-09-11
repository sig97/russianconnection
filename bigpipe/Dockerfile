FROM openjdk:8-jdk-alpine

VOLUME /tmp
# ARG JAR_FILE
# COPY ${JAR_FILE} app.jar
COPY build/libs/bigpipe*.jar /data/slurper.jar
WORKDIR /
EXPOSE 7878
ENTRYPOINT ["java", "-Dspring.profiles.active=localdocker", "-jar","/data/slurper.jar"]