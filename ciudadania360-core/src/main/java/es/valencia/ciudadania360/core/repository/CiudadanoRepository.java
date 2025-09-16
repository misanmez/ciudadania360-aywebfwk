package es.valencia.ciudadania360.core.repository;

import es.valencia.ciudadania360.core.entity.Ciudadano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio JPA para la entidad Ciudadano
 * Acceso a datos del módulo core (lógica de negocio compartida)
 */
@Repository
public interface CiudadanoRepository extends JpaRepository<Ciudadano, Long> {
    
    /**
     * Busca ciudadanos activos
     */
    List<Ciudadano> findByActivoTrue();
    
    /**
     * Busca ciudadano por DNI
     */
    Optional<Ciudadano> findByDni(String dni);
    
    /**
     * Busca ciudadano activo por DNI
     */
    @Query("SELECT c FROM Ciudadano c WHERE c.dni = :dni AND c.activo = true")
    Optional<Ciudadano> findActivoByDni(@Param("dni") String dni);
    
    /**
     * Busca ciudadanos por municipio
     */
    List<Ciudadano> findByMunicipioAndActivoTrue(String municipio);
    
    /**
     * Busca ciudadanos por provincia
     */
    List<Ciudadano> findByProvinciaAndActivoTrue(String provincia);
    
    /**
     * Verifica si existe un ciudadano con el DNI dado
     */
    boolean existsByDni(String dni);
    
    /**
     * Verifica si existe un ciudadano activo con el DNI dado
     */
    @Query("SELECT COUNT(c) > 0 FROM Ciudadano c WHERE c.dni = :dni AND c.activo = true")
    boolean existsActivoByDni(@Param("dni") String dni);
}
