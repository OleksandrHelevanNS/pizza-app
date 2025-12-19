FROM maven:3.9.9-eclipse-temurin-21-alpine AS builder

WORKDIR /app

COPY common/pom.xml ./common/
COPY common/src ./common/src
RUN --mount=type=cache,target=/root/.m2 \
    cd common && mvn clean install -DskipTests

COPY order-service/pom.xml ./order-service/
RUN --mount=type=cache,target=/root/.m2 \
    cd order-service && mvn dependency:go-offline

COPY order-service/src ./order-service/src
RUN --mount=type=cache,target=/root/.m2 \
    cd order-service && mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/order-service/target/*.jar app.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "app.jar"]