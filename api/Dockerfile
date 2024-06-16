FROM eclipse-temurin:22-jdk-alpine as build

WORKDIR /build
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw install -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM eclipse-temurin:22-jre-alpine
ARG DEPENDENCY=/build/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
WORKDIR /app
VOLUME /app/media
EXPOSE 8080
ENTRYPOINT ["java","-cp","lib/*:.","dev.critteros.javaflavors.JavaFlavorsApplication"]