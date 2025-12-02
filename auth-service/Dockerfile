FROM maven:3.9.9-eclipse-temurin-21-alpine AS builder

WORKDIR /app

# Спочатку збираємо та встановлюємо common
COPY common/pom.xml ./common/
COPY common/src ./common/src
RUN --mount=type=cache,target=/root/.m2 \
    cd common && mvn clean install -DskipTests

# Тепер збираємо auth-service
COPY auth-service/pom.xml ./auth-service/
RUN --mount=type=cache,target=/root/.m2 \
    cd auth-service && mvn dependency:go-offline

COPY auth-service/src ./auth-service/src
RUN --mount=type=cache,target=/root/.m2 \
    cd auth-service && mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/auth-service/target/*.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]