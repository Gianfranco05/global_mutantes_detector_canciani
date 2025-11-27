# ========================================
# ETAPA 1: BUILD (Compilación)
# ========================================
FROM eclipse-temurin:17-jdk-alpine AS build

WORKDIR /app

COPY . .

RUN chmod +x ./gradlew
RUN ./gradlew bootJar --no-daemon

# ========================================
# ETAPA 2: RUNTIME (Ejecución)
# ========================================
FROM eclipse-temurin:17-jre-alpine

EXPOSE 8080

COPY --from=build /app/build/libs/mutant-detector-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
