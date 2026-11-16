# Utiliser Java 17
FROM eclipse-temurin:17-jdk-alpine

# Créer un dossier de travail
WORKDIR /app

# Copier tous les fichiers du projet
COPY . .

# Compiler le projet (on saute les tests pour aller plus vite)
RUN ./mvnw clean package -DskipTests

# Lancer l'application
CMD ["java", "-jar", "target/hotel-0.0.1-SNAPSHOT.jar"]
