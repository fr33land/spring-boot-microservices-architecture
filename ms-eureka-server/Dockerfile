FROM openjdk:11-jre-slim
ENV TZ=Europe/Vilnius
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
VOLUME /tmp
EXPOSE 8761
COPY target/*.jar ms-eureka-server.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/ms-eureka-server.jar"]