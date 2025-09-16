package es.valencia.ciudadania360.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Clase principal de la aplicación Ciudadanía360
 * Configurada para ejecutarse como aplicación Spring Boot
 */
@SpringBootApplication
@ComponentScan(basePackages = {
    "es.valencia.ciudadania360.backend",
    "es.valencia.ciudadania360.core",
    "es.valencia.ciudadania360.ws.ciudadano"
})
@EnableJpaRepositories(basePackages = {
    "es.valencia.ciudadania360.core.repository"
})
@EntityScan(basePackages = {
    "es.valencia.ciudadania360.core.entity"
})
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("  CIUDADANIA360 - AYWEBFWK");
        System.out.println("  Iniciando aplicacion...");
        System.out.println("========================================");
        
        SpringApplication.run(Application.class, args);
        
        System.out.println("========================================");
        System.out.println("  Aplicacion iniciada correctamente");
        System.out.println("  URL: http://localhost:8080");
        System.out.println("========================================");
    }
}
