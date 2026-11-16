# Java 17
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copier le projet
COPY . .

# Autoriser l'exécution de Maven
RUN chmod +x mvnw

# Compiler le projet
RUN ./mvnw clean package -DskipTests

# Démarrer Spring Boot
CMD ["java", "-jar", "target/hotel-0.0.1-SNAPSHOT.jar"]
