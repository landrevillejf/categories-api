FROM openjdk:8-jdk-alpine
MAINTAINER lanaforge.ca
COPY target/docker-cognos-categories-api-0.0.1.jar cognos-categories-api.jar
ENTRYPOINT ["java","-jar","/cognos-categories-api.jar"]
