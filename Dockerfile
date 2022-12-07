FROM openjdk:17.0.2-oracle
ADD target/perfect-app-1.0.0.jar perfect-app-1.0.0.jar
EXPOSE 8000

ENTRYPOINT ["java", "-jar", "perfect-app-1.0.0.jar"]
