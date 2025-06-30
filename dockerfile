FROM maven:3.8.3-openjdk-17 AS build

WORKDIR /app
COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /app/target/Quizapp-0.0.1-SNAPSHOT.jar Quizapp.jar

Expose 8080
ENTRYPOINT ["java", "-jar" , "Quizapp.jar"]
