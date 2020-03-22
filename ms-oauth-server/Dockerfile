FROM openjdk:11-jre-slim
ENV TZ=Europe/Vilnius
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
VOLUME /tmp
EXPOSE 8089
ARG JAR_FILE=target/UaaService.jar
COPY ${JAR_FILE} UaaService.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/UaaService.jar"]