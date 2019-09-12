FROM openjdk:8-jdk-alpine

VOLUME /tmp
# ARG JAR_FILE
# COPY ${JAR_FILE} app.jar
COPY build/libs/api*.jar /data/api.jar
EXPOSE 7979 8000
WORKDIR /
ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,address=8000,server=y,suspend=n
ENTRYPOINT ["java", "-Dspring.profiles.active=localdocker", "-jar","/data/api.jar"]