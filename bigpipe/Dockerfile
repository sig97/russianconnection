FROM openjdk:8-jdk-alpine

VOLUME /tmp
# ARG JAR_FILE
# COPY ${JAR_FILE} app.jar
COPY build/libs/bigpipe*.jar /data/slurper.jar
WORKDIR /
ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,address=8000,server=y,suspend=n
EXPOSE 7878 8000
ENTRYPOINT ["java", "-Dspring.profiles.active=localdocker", "-jar","/data/slurper.jar"]