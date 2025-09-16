package es.valencia.ciudadania360.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Controlador de salud para verificar que la aplicación funciona
 */
@RestController
@RequestMapping("/api")
public class HealthController {

    @GetMapping("/health")
    public Map<String, Object> health() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("timestamp", LocalDateTime.now());
        response.put("application", "Ciudadanía360");
        response.put("version", "1.0.0");
        return response;
    }

    @GetMapping("/info")
    public Map<String, Object> info() {
        Map<String, Object> response = new HashMap<>();
        response.put("name", "Ciudadanía360");
        response.put("description", "Sistema de gestión ciudadana del Ayuntamiento de Valencia");
        response.put("version", "1.0.0");
        response.put("database", "PostgreSQL");
        response.put("framework", "Spring Boot + AyWebFwk");
        return response;
    }
}
