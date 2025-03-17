FROM eclipse-temurin:17-jre
COPY target/bloodbank-1.0.0.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
EXPOSE 8080