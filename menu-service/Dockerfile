FROM eclipse-temurin:17-jdk AS builder
WORKDIR /app

COPY pom.xml .
COPY src src

# Використовуємо Maven з базового образу для збірки
RUN apt-get update && \
    apt-get install -y maven && \
    mvn clean package -DskipTests && \
    mkdir -p target/extracted && \
    java -Djarmode=layertools -jar target/*.jar extract --destination target/extracted

FROM eclipse-temurin:17-jre
WORKDIR /app

# Копіюємо розпаковані шари з builder
COPY --from=builder /app/target/extracted/dependencies/ ./
COPY --from=builder /app/target/extracted/spring-boot-loader/ ./
COPY --from=builder /app/target/extracted/snapshot-dependencies/ ./
COPY --from=builder /app/target/extracted/application/ ./

ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]