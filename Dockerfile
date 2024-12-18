# Usa una imagen base de OpenJDK
FROM openjdk:23-jdk-slim

# Establece el directorio de trabajo
WORKDIR /app

# Copia solo los archivos necesarios
COPY . /app

# Instala Maven y actualiza la lista de paquetes en una sola capa
RUN apt-get update && apt-get install -y maven && rm -rf /var/lib/apt/lists/*

# Da permisos a mvnw (si es necesario)
RUN chmod +x ./mvnw

# Construye el proyecto con Maven
RUN ./mvnw clean install -DskipTests

# Expone el puerto para la aplicación Spring Boot
EXPOSE 8080

# Comando para iniciar la aplicación
CMD ["./mvnw", "spring-boot:run"]
