# Étape 1 : Compiler avec Maven et Java 17
FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app

# Copier les fichiers du projet
COPY pom.xml .
COPY src ./src

# Compiler le projet
RUN mvn clean package -DskipTests

# Étape 2 : Exécuter avec Java 17
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copier le fichier JAR compilé
COPY --from=build /app/target/hotel-0.0.1-SNAPSHOT.jar app.jar

# Démarrer Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
