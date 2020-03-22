FROM openjdk:11-jre-slim
ENV TZ=Europe/Vilnius
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
VOLUME /tmp
EXPOSE 8082
ARG JAR_FILE=target/UsersService.jar
COPY ${JAR_FILE} UsersService.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/UsersService.jar"]