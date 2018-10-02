FROM openjdk:8-jdk-alpine
ENV TZ=Europe/Vilnius
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
VOLUME /tmp
EXPOSE 8761
ARG JAR_FILE=target/EurekaServer.jar
COPY ${JAR_FILE} EurekaServer.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/EurekaServer.jar"]