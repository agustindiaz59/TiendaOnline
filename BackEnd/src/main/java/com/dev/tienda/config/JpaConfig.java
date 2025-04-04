package com.dev.tienda.config;


import java.io.IOException;
import java.util.Properties;
import javax.sql.DataSource;

import jakarta.validation.Validator;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import jakarta.persistence.EntityManagerFactory;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;


/**
 * Clase de configuracion de spring jpa + hibernate + MySQL
 *
 * @author matias
 * @version 1.0
 */
@Configuration
@EnableJpaRepositories("com.dev.tienda.repositorios")// Especial para @Repository
@ComponentScan({"com.dev.tienda.modelos","com.dev.tienda.dto"})
@EnableTransactionManagement(proxyTargetClass = true)
@EnableSpringDataWebSupport //Soportar paginacion web
//@PropertySource("classpath:ApplicationProperties.txt")//Traigo los datos de la BBDD desde un archivo externo
//@ComponentScan("com.dev.tienda")
public class JpaConfig {

    @Value("${datasource.driver}")
    private String driver;
    @Value("${datasource.url}")
    private String url;
    @Value("${datasource.user}")
    private String user;
    @Value("${datasource.password}")
    private String password;

    /**
     * DataSource es una abstraccion que contiene
     * los datos necesarios para la coneccion a la base de datos
     *
     * @return Datasource objeto que representa una conexion a la base de datos, con todos los datos correspondientes
     */
	@Bean
	public DataSource dataSource() {
		return DataSourceBuilder
				.create()
				.driverClassName(driver)
				.username(user)
				.password(password)
				.url(url)
				.build();
	}



    /**
     * Necesario como interfaz principal de spring JPA para manejar las transacciones
     *
     * @param emf EntityManagerFactory utilizado para la creacion EntityManager para cada clase mapeada
     */
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }



    /**
     * El EntityManagerFactory maneja la creacion de EntityManager para cada clase mapeada por el orm
     * este bean configura el entityManagerFactory con la implementacion de JPA que reciba y las propiedades de un archivo de texto externo
     *
     * @param vendorAdapter inyecta la implementacion de JPA, en este caso hibernate
     * @param hibernateProperties inyecta las propiedades de hibernate desde un archivo de texto externo
     * @return EntityManagerFactory que utilizará spring jpa para realizar los mapeos
     */
    @Bean()
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            HibernateJpaVendorAdapter vendorAdapter,
            @Qualifier("hibernateProperties")
            Properties hibernateProperties) {

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("com.dev.tienda.modelos"); // El paquete donde se encuentran las clases @Entity
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(hibernateProperties);

        return em;
    }


    /**
     * Establece un bean de hibernate para inyectar en el EntityManagerFactory
     *
     */
    @Bean
    public HibernateJpaVendorAdapter vendorAdapter(){
        //Configuracion de hibernate, reemplaza a persistence.xml
        return new HibernateJpaVendorAdapter();
    }


    /**
     * @return archivo de propiedades con instrucciones para hibernate, por ej. ddl.auto=update/create/create-drop
     */
    @Bean
    public Properties hibernateProperties(){
        // Aquí añades las propiedades de Hibernate
        Properties hibernateProperties = new Properties();

        //Traigo las propiedades desde el archivo de texto
        try {
            ClassPathResource archivo = new ClassPathResource("hibernateProperties.txt");
            hibernateProperties.load(archivo.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return hibernateProperties;
    }


    @Bean
    public Validator validator() {
        return new LocalValidatorFactoryBean();
    }

    /**
     * Bean de configuracion de migraciones Flyway, los archivos en la location() se ejecutaran y segun
     * la baselineOnMigrate() se configura si sobreescribe las tablas existentes en la base de datos de Datasource
     *
     * @param dataSource Base de datos en la cual surtiran los cambios
     * @return
     */

    @Bean
    public Flyway flyway(DataSource dataSource) {
        // Configuración de Flyway
        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)          // Conexión a la base de datos
                .baselineOnMigrate(true)         // Establecer línea base si el esquema ya tiene tablas
                .locations("db/migration")       // Ubicación de las migraciones (ruta predeterminada)
                .load();

        // Ejecutar las migraciones al inicio
        flyway.migrate();

        return flyway;
    }
}
