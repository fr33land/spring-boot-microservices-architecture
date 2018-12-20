
# Spring Boot microservices architecture based dashboard

[![Build Status](https://travis-ci.org/fr33land/spring-boot-microservices-architecture.svg?branch=master)](https://travis-ci.org/fr33land/spring-boot-microservices-architecture)

A purpose of this project is to aggregate and accumulate good practices of microservices found on the internet (especially in github). 
There are plenty of projects related to microservices in github but many of them are obsolete or didn't cover full microservice ecosystem scope. So I decided to create a small and simple dashboard project which tries to cover all the moving parts of microservices enviroment. Microservice dashboard project consists of five components: ConfigServer, EurekaServer, UaaService, UsersService, WebApp. These components covers such topics as OAuth 2.0 authentication, user login form, simple web page client UI integration, REST API calls with role based access permissions. A project itself has been developed using JAVA version 1.8 (easely could be downgraded to 1.7), Spring Boot framework, Netflix microservices libraries and Postgres database. In the next chapters I will present short summary of system components purposes.

## Microservices ecosystem

### CommonLibs - shared library used to store common components
This dependency is used accross all microservices. It holds commonly used objects between different components. This helps to prevent DRY (do-not-repeat-yourself) pratice accross code. Shared lib method is one of the commonly used pratices around microservices ecosystem

### EurekaServer - service registry and locator service
It is one of the most important components of microservice ecosystem. Eureka server holds all the information about microservices which exists in the cloud. A microservice which wants register itself with Eureka has to add information about Eureka server location to application.yaml file.    

### ConfigServer - configuration server
This microservice component is used to store and serve system configurations in cloud enviroment and serve them to the client throught the network. ConfigServer can take configuration data from filesystem or from versioning repositories (Git, SVN). At this moment ConfigServer doesn't play any role. It's just an empty mircoservice.

### UaaService - OAuth 2.0 authorization server
The other critical component of cloud enviroment. A purpose of this service is to server login window for web client app, authorize users and issue tokens. UaaServices uses DAO authentication provider for retrieving and checking user data. Issued tokens are stored as JWT tokens and encrypted with assymetric keys from JKS storage.     

### UsersService - OAuth 2.0 resource service
A resource service which stores information about user. The access to the service endpoints is secured with OAuth 2.0 JWT tokens which are decoded using JwtAccessTokenConverter with public key provided by UaaService from JKS storage.

### WebApp - web application OAuth 2.0 client
A simple client web app that uses UaaService as authentication provider and consumes secured UsersService endpoint. WebApp has a simple GUI based on Thymeleaf, JQuery, React and Bootstrap.

## Building and runing
The project is builded using maven command `mvn clean install`. In a root project directory parent pom file exists. Using this parent pom.xml file during maven build will compile all the project modules in one run. 
