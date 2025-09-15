package es.valencia.ciudadania360.ws.ciudadano.service;

import es.valencia.ciudadania360.ws.ciudadano.entity.Ciudadano;
import es.valencia.ciudadania360.ws.ciudadano.entity.CiudadanoRequest;
import es.valencia.ciudadania360.ws.ciudadano.entity.CiudadanoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper MapStruct para Ciudadano
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CiudadanoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "activo", ignore = true)
    Ciudadano toEntity(CiudadanoRequest request);

    CiudadanoResponse toResponse(Ciudadano entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "activo", ignore = true)
    Ciudadano updateEntity(CiudadanoRequest request, @MappingTarget Ciudadano entity);
}
