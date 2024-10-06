FROM amazoncorretto
COPY target/tienda.war tienda.war

expose 8080
