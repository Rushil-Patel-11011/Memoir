# FROM openjdk:11-jdk-slim
# VOLUME /tmp
# COPY target/*.jar app.jar
# ENTRYPOINT ["java","-jar","/app.jar"]
# EXPOSE 8080


FROM maven:3-openjdk-11 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:11-jdk-slim
COPY --from=build /target/Memoir-0.0.1-SNAPSHOT.jar Memoir.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","Memoir.jar"]