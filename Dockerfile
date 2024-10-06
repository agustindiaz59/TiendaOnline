FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Ejecutar la aplicación en Tomcat
FROM tomcat:10.1-jdk17
COPY --from=build /app/target/tienda.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]