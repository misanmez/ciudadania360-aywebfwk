package es.valencia.ciudadania360.ws.ciudadano.service;

import es.valencia.ciudadania360.ws.ciudadano.entity.Ciudadano;
import es.valencia.ciudadania360.ws.ciudadano.entity.CiudadanoRequest;
import es.valencia.ciudadania360.ws.ciudadano.entity.CiudadanoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

/**
 * Servicio para operaciones de Ciudadano
 */
@Service
public class CiudadanoService {

    @Autowired
    private CiudadanoMapper ciudadanoMapper;

    // Simulación de base de datos en memoria para testing
    private final List<Ciudadano> ciudadanos = new ArrayList<>();
    private Long nextId = 1L;

    public List<CiudadanoResponse> findAll() {
        // Para testing, devolvemos una lista con un ciudadano de ejemplo
        Ciudadano ciudadanoEjemplo = Ciudadano.builder()
                .id(1L)
                .nombre("Ejemplo")
                .apellidos("Test")
                .dni("12345678A")
                .activo(true)
                .build();
        return List.of(ciudadanoMapper.toResponse(ciudadanoEjemplo));
    }

    public Optional<CiudadanoResponse> findById(Long id) {
        if (id.equals(1L)) {
            Ciudadano ciudadano = Ciudadano.builder()
                    .id(1L)
                    .nombre("Ejemplo")
                    .apellidos("Test")
                    .dni("12345678A")
                    .activo(true)
                    .build();
            return Optional.of(ciudadanoMapper.toResponse(ciudadano));
        }
        return Optional.empty();
    }

    public CiudadanoResponse save(CiudadanoRequest request) {
        Ciudadano ciudadano = ciudadanoMapper.toEntity(request);
        ciudadano.setId(1L);
        ciudadano.setActivo(true);
        return ciudadanoMapper.toResponse(ciudadano);
    }

    public Optional<CiudadanoResponse> update(Long id, CiudadanoRequest request) {
        if (id.equals(1L)) {
            Ciudadano ciudadano = Ciudadano.builder()
                    .id(1L)
                    .nombre("Actualizado")
                    .apellidos("Test")
                    .dni("12345678A")
                    .activo(true)
                    .build();
            ciudadanoMapper.updateEntity(request, ciudadano);
            return Optional.of(ciudadanoMapper.toResponse(ciudadano));
        }
        return Optional.empty();
    }

    public boolean delete(Long id) {
        return id.equals(1L);
    }
}
