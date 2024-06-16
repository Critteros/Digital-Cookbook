FROM eclipse-temurin:22-jdk-alpine as build

WORKDIR /build
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw install -DskipTests

FROM eclipse-temurin:22-jre-alpine
ARG JAR_FILE=target/*.jar
COPY --from=build /build/${JAR_FILE} /app/app.jar
WORKDIR /app
VOLUME /app/media
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]