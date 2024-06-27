FROM openjdk:17
EXPOSE 8080
ADD target/spring-project.jar spring-project.jar

ENTRYPOINT ["java","-jar", "/spring-project.jar"]