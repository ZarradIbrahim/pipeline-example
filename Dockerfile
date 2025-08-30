# Utilise l'image officielle OpenJDK 17 comme image de base
FROM openjdk:17-jdk-slim AS build

# Définit le répertoire de travail dans le conteneur
WORKDIR /app

# Copie le fichier jar généré par Maven dans le conteneur
# (Remplacez 'pipeline-example-0.0.1-SNAPSHOT.jar' par le nom réel du jar si besoin)
COPY target/pipeline-example-0.0.1-SNAPSHOT.jar app.jar

# Expose le port 8080 (port par défaut de Spring Boot)
EXPOSE 8080

# Définit la commande de lancement de l'application
ENTRYPOINT ["java", "-jar", "app.jar"]

# Chaque ligne ci-dessus est commentée pour expliquer son rôle

