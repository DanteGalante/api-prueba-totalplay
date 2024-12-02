# Usar una imagen base de java 17
FROM openjdk:17-jdk-slim

WORKDIR /totalplay

COPY target/empleados-0.0.1-SNAPSHOT.jar app.jar

COPY .env .env
COPY wait-for-it.sh /wait-for-it.sh 
RUN chmod +x /wait-for-it.sh

EXPOSE 8080

# Comando para ejecutar la app
# ENTRYPOINT ["java", "-jar", "app.jar"]
ENTRYPOINT ["/wait-for-it.sh", "oracle-db:1521", "--timeout=180", "--", "java", "-jar", "app.jar"]