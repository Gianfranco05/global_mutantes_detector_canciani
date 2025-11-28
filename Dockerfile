# ========================================
# ETAPA 1: BUILD (Compilación)
# ========================================
FROM gradle:8.8-jdk17-alpine AS build

WORKDIR /app

COPY . .

RUN gradle bootJar --no-daemon

# ========================================
# ETAPA 2: RUNTIME (Ejecución)
# ========================================
FROM eclipse-temurin:17-jre-alpine

EXPOSE 8080

COPY --from=build /app/build/libs/mutant-detector-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
