FROM openjdk:17-oracle

WORKDIR /opt

COPY target/category-api-0.0.1-SNAPSHOT.jar /opt/category-api.jar

ENTRYPOINT ["java", "-jar", "category-api.jar" ]
