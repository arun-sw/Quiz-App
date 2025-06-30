From maven:3.8.3-openjdk-17 as bulid
COPY . /app
RUN mvn clean package -DskipTests

From openjdk:17.0.1-jdk-slim
COPY --From=bulid /target/Quizapp-0.0.1-SNAPSHOT.jar Quizapp.jar

Expose 8080
ENTRYPOINT ["java","-jar","Quizapp.jar"]
