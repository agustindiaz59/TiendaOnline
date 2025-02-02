package com.dev.tienda.config.security;

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
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;


/**
 * El proceso de spring security para verificar un usuario es:
 * SecurityFilterChain.formLogin(.loginPage("/ejemplo")) --> formulario de login --> SecurityFilterChain.formLogin(.loginProcessingUrl("/login")) POST --> AuthenticationManager --> AuthenticationProvider -> UserDetailsService
 * pagina protegida + JSESSIONID / pagina error de credenciales <-- SecurityFilterChain.formLogin(.loginSucess/failure()) <-- AuthenticationManager <-- coincide las credenciales? s/n <-- UserDetails
 *
 * @author matias
 * @version 1.0
 */
@Configuration
@EnableWebSecurity
@ComponentScan("com.dev.tienda")
public class SecurityConfig extends AbstractSecurityWebApplicationInitializer { //iniciador para aplicaciones MVC



    /**
     * Aqui estan las configuraciones generales de la aplicacion como rutas,
     * formularios de login y logout, csrf y otras opciones
     *
     * @param http objeto inyectado para configurar spring security
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        //Paginas del sistema
                        .requestMatchers("/","/index","/favicon.ico").permitAll()
                        //.requestMatchers("/products").authenticated()
                        .requestMatchers("/404","/products","/crearProductos","/vistaproducts/**","/vistaproducts").permitAll()
                        //Autenticaciones
                        .requestMatchers("/login","/auth").permitAll()
                        .requestMatchers("/logout").authenticated()
                        .requestMatchers("/registro","/registrar").permitAll()
                        ////Recursos estaticos
                        .requestMatchers("/public/**").permitAll()
                        .requestMatchers("/assets/**").permitAll()
                        .requestMatchers("/sass/**").permitAll()
                        .requestMatchers("/script.js").permitAll()
                        .requestMatchers("/app.js").permitAll()
                        .requestMatchers("/img/**").permitAll()
                        .requestMatchers("/sidebardcerraryabrir.js").permitAll()
                        .anyRequest().permitAll()
                )
                .exceptionHandling(exception -> exception //Se encarga de redirecciones y establecer codigos de error HTTP
                        .accessDeniedHandler(new CustomAccesDeniedHandler())

                )
                .formLogin(form ->form //Se encarga del login
                        .loginPage("/login")
                        .loginProcessingUrl("/auth") //ubicacion POST a donde enviar las credenciales
                        .defaultSuccessUrl("/index")
                        .failureUrl("/login")
                        //nombre de las credenciales que se validaran en la BBDD mediante biding html
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .successHandler(new LoginSuccessHandler()) //Experimental
                )
                .csrf(csrf -> csrf // Se encarga de proteccion contra Cross Site Request Forgery
                        .csrfTokenRepository(new HttpSessionCsrfTokenRepository())
                        //Con cookieRepository se habilitan aplicaciones basadas en javascript
                        //.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())

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
     * las solicita al userDetailsService y compara las contraseñas con el passwordEncoder
     *
     * @param userDetailsService servicio encargado de la busqueda en la base de datos del usuario
     * @param passwordEncoder objeto encargado de verificar si la contraseña encriptada coincide con la ingresada en el formulario de login
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder
    ) {

        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);

        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /*

     //Usar en caso de no tener un UserDetailsService propio
     @Bean
     @Autowired
     public UserDetailsManager userDetailsManager(DataSource dataSource){
         return new JdbcUserDetailsManager(dataSource).;
     }

     //Este bean es un wrapper de authManager para configurar las validaciones de usuario, opcional para detalle fino
     @Bean
     public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
         return authConfig.getAuthenticationManager();
     }

     */

}
