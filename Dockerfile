FROM maven:3.9.9-amazoncorretto-17-debian as build

WORKDIR /app

COPY pom.xml .
COPY src ./src
COPY client ./client

RUN apt-get update && apt-get install -y curl && \
    curl -sL https://deb.nodesource.com/setup_18.x | bash - && \
    apt-get install -y nodejs && \
    npm install -g @angular/cli

ENV NODE_OPTIONS=--openssl-legacy-provider

RUN mvn clean package -DskipTests


FROM eclipse-temurin:17-jre
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
EXPOSE 8080