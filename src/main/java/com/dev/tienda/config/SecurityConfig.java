package com.dev.tienda.config;

import com.dev.tienda.servicios.UsuarioUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;


/**
 * El proceso de spring security para verificar un usuario es:
 *
 * SecurityFilterChain.formLogin(.loginPage("/ejemplo")) --> formulario de login --> SecurityFilterChain.formLogin(.loginProcessingUrl("/login")) POST --> AuthenticationManager --> AuthenticationProvider -> UserDetailsService
 * pagina protegida + JSSESIONID / pagina error de credenciales <-- SecurityFilterChain.formLogin(.loginSucess/failure()) <-- AuthenticationManager <-- coincide las credenciales? s/n <-- UserDetails
 *
 * @author matias
 * @version 1.0
 */
@Configuration
@EnableWebSecurity
@ComponentScan("com.dev.tienda")
public class SecurityConfig extends AbstractSecurityWebApplicationInitializer { //iniciador para aplicaciones MVC

    @Autowired
    private UsuarioUserDetailsService usuarioUserDetailsService;

    /**
     *     Aqui estan las configuraciones generales de la aplicacion como rutas, formularios de login y logout, csrf y otras opciones
     *
     * @param http objeto inyectado para configurar spring security
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        //Paginas del sistema
                        .requestMatchers("/","/index").permitAll()
                        .requestMatchers("/products").authenticated()
                        //Autenticaciones
                        .requestMatchers("/login","/auth").permitAll()
                        .requestMatchers("/logout").authenticated()
                        .requestMatchers("/registro","/registrar").permitAll()
                        //Recursos estaticos
                        .requestMatchers("/public/**").permitAll()
                        .requestMatchers("/assets/**").permitAll()
                        .requestMatchers("/sass/**").permitAll()
                        .requestMatchers("/script.js").permitAll()
                )
                .formLogin(form ->form
                        .loginPage("/login")
                        .loginProcessingUrl("/auth") //ubicacion POST a donde enviar las credenciales
                        .defaultSuccessUrl("/index")
                        .failureUrl("/login")
                        //nombre de las credenciales que se validaran en la BBDD mediante biding html
                        .usernameParameter("username")
                        .passwordParameter("password")
                )
                .csrf(csrf -> csrf
                        .disable()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/index")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                );
        return http.build();
    }


    /**
     * Este bean se encarga de validar las credenciales del usuario (userDetails),
     *
     * @param userDetailsService servicio encargado de la busqueda en la base de datos del usuario
     * @param passwordEncoder objeto encargado de verificar si la contraseña encriptada coincide con la ingresada en el formulario de login
     */
    //las solicita al userDetailsService y compara las contraseñas con el passwordEncoder
    @Bean
    public DaoAuthenticationProvider authenticationProvider(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {

        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);

        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


// Usar en caso de no tener un UserDetailsService propio
//    @Bean
//    @Autowired
//    public UserDetailsManager userDetailsManager(DataSource dataSource){
//        return new JdbcUserDetailsManager(dataSource).;
//    }

    // Este bean es un wrapper de authManager para configurar las validaciones de usuario, opcional para detalle fino
    // @Bean
    // public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
    //     return authConfig.getAuthenticationManager();
    // }

}
