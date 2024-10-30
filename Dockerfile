FROM tomcat:latest

# Copia el archivo WAR a la carpeta de despliegue de Tomcat
COPY target/tienda.war /usr/local/tomcat/webapps/tienda.war

# Copia el archivo de base de datos SQLite en la raíz de Tomcat
COPY tiendaLenceria.db /usr/local/tomcat/tiendaLenceria.db

# Define la variable de entorno (opcional si ya la tienes en el .properties)
ENV SQLITE_DB_PATH=/usr/local/tomcat/tiendaLenceria.db

# Expone el puerto 8080 para acceder a la aplicación
EXPOSE 8080

# Comando para ejecutar Tomcat y ver los logs
CMD ["catalina.sh", "run", "&&", "tail", "-f", "/usr/local/tomcat/logs/catalina.out"]


# Comando para ejecutar Tomcat
CMD ["catalina.sh", "run"]