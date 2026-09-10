# 1. Etapa de compilación utilizando el wrapper propio del proyecto
FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app

# Copiar archivos del proyecto
COPY . .

# Dar permisos de ejecución a gradlew y compilar omitiendo tests
RUN chmod +x gradlew
RUN ./gradlew bootJar --no-daemon -x test

# 2. Etapa de ejecución ligera
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]