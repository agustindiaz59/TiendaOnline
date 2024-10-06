FROM tomcat
COPY target/tienda.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]