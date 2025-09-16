package es.valencia.ciudadania360.backend.config;

import es.valencia.ciudadania360.core.repository.CiudadanoRepository;
import es.valencia.ciudadania360.core.service.CiudadanoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Configuración de servicios del backend
 * Define los beans de servicios de lógica de negocio
 */
@Configuration
@ComponentScan(basePackages = {
    "es.valencia.ciudadania360.core.service",
    "es.valencia.ciudadania360.core.repository"
})
public class ServiceConfig {
    
    // Los servicios se configuran automáticamente mediante @ComponentScan
    // y las anotaciones @Service y @Repository
}
