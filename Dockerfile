FROM openjdk:11
ADD target/automatic-irrigation-system.jar automatic-irrigation-system.jar
ENTRYPOINT ["java", "-jar","automatic-irrigation-system.jar"]
EXPOSE 8080
