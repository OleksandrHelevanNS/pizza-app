FROM maven:3.9.9-eclipse-temurin-21-alpine AS builder

WORKDIR /app

COPY common/pom.xml ./common/
COPY common/src ./common/src
RUN --mount=type=cache,target=/root/.m2 \
    cd common && mvn clean install -DskipTests

COPY menu-service/pom.xml ./menu-service/
RUN --mount=type=cache,target=/root/.m2 \
    cd menu-service && mvn dependency:go-offline

COPY menu-service/src ./menu-service/src
RUN --mount=type=cache,target=/root/.m2 \
    cd menu-service && mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/menu-service/target/*.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]