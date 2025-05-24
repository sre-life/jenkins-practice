# Usa una imagen base de OpenJDK para ejecutar la aplicación Java
FROM openjdk:8-jre-slim

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el JAR ejecutable de tu aplicación al contenedor
# Asume que el JAR se genera en target/jenkins-practice-app-1.0-SNAPSHOT.jar
COPY target/webapp-1.0.jar .

# Expone el puerto si tu aplicación web lo usa (ej. para Spring Boot)
# EXPOSE 8080

# Comando para ejecutar la aplicación cuando el contenedor se inicie
CMD ["java", "-jar", "webapp-1.0.jar"]

