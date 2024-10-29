FROM tomcat

WORKDIR /app

COPY target/tienda.war /app/tienda.war
COPY tiendaLenceria.db /app/tiendaLenceria.db

ENV SQLITE_DB_PATH=/app/tiendaLenceria.db

EXPOSE 8080
CMD ["catalina.sh", "run"]