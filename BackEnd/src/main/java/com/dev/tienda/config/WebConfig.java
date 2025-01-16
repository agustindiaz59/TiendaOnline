package com.dev.tienda.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

@ComponentScan("com.dev.tienda")
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {


    //--------------------Thymeleaf core---------------------------//
	@Bean
    public SpringResourceTemplateResolver templateResolver(){
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setPrefix("classpath:/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");
        // Template cache is true by default. Set to false if you want
        // templates to be automatically updated when modified.
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine(){
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    @Bean
    public ThymeleafViewResolver viewResolver(){ 
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        return viewResolver;
    }
    //-------------------------------------------------------------//




    //-------------------Manejadores de recursos-------------------//
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/static/assets/");
        registry.addResourceHandler("/public/**").addResourceLocations("classpath:/static/public/");
        registry.addResourceHandler("/sass/**").addResourceLocations("classpath:/static/Sass/");
        //registry.addResourceHandler("/script.js").addResourceLocations("classpath:/static/script.js/");
        registry.addResourceHandler("/app.js").addResourceLocations("classpath:/static/app.js/");
        registry.addResourceHandler("/favicon.ico").addResourceLocations("classpath:/static/favicon.ico/");//Los archivos terminan en barras
        registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/img/");
        //registry.addResourceHandler("/sidebardcerraryabrir.js").addResourceLocations("/static/sidebardcerraryabrir.js/");

        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    }
    //------------------------------------------------------------//

}
