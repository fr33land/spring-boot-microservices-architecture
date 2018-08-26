FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8089
ARG JAR_FILE=target/UaaService.jar
COPY ${JAR_FILE} UaaService.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/UaaService.jar"]