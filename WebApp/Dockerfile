FROM openjdk:8-jdk-alpine
ENV TZ=Europe/Vilnius
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
VOLUME /tmp
EXPOSE 8081
ARG JAR_FILE=target/WebApp.jar
COPY ${JAR_FILE} WebApp.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/WebApp.jar"]