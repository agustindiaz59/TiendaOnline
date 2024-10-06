# Etapa 2: Ejecutar la aplicación en Tomcat
FROM tomcat
COPY target/tienda.war /app/target/tienda.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]