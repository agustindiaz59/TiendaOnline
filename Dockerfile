FROM tomcat:latest

# Copia el archivo WAR a la carpeta de despliegue de Tomcat
COPY target/tienda.war /usr/local/tomcat/webapps/tienda.war

# Copia el archivo de base de datos SQLite en la imagen
COPY tiendaLenceria.db /usr/local/tomcat/tiendaLenceria.db

# Define la variable de entorno para la base de datos SQLite
ENV SQLITE_DB_PATH=/usr/local/tomcat/tiendaLenceria.db

# Expone el puerto 8080 para acceder a la aplicación
EXPOSE 8080

# Comando para ejecutar Tomcat
CMD ["catalina.sh", "run"]