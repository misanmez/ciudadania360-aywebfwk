package es.valencia.ciudadania360.core.mapper;

import es.valencia.ciudadania360.core.entity.Ciudadano;
import es.valencia.ciudadania360.common.dto.request.CiudadanoRequestDTO;
import es.valencia.ciudadania360.common.dto.response.CiudadanoResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Mapper para convertir entre entidades y DTOs de Ciudadano
 * Utiliza MapStruct para conversiones automáticas
 * Pertenece al módulo core (lógica de negocio compartida)
 */
@Mapper
public interface CiudadanoMapper {
    
    CiudadanoMapper INSTANCE = Mappers.getMapper(CiudadanoMapper.class);
    
    /**
     * Convierte entidad Ciudadano a DTO de respuesta
     */
    @Mapping(source = "activo", target = "activo")
    CiudadanoResponseDTO toResponseDTO(Ciudadano ciudadano);
    
    /**
     * Convierte DTO de request a entidad Ciudadano
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "activo", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    Ciudadano toEntity(CiudadanoRequestDTO request);
    
    /**
     * Actualiza una entidad existente con datos del DTO
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "activo", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    void updateEntity(CiudadanoRequestDTO request, @org.mapstruct.MappingTarget Ciudadano entity);
}
