package es.valencia.ciudadania360.ws.ciudadano.service;

import es.valencia.ciudadania360.ws.ciudadano.entity.Ciudadano;
import es.valencia.ciudadania360.ws.ciudadano.entity.CiudadanoRequest;
import es.valencia.ciudadania360.ws.ciudadano.entity.CiudadanoResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-15T15:35:03+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class CiudadanoMapperImpl implements CiudadanoMapper {

    @Override
    public Ciudadano toEntity(CiudadanoRequest request) {
        if ( request == null ) {
            return null;
        }

        Ciudadano.CiudadanoBuilder ciudadano = Ciudadano.builder();

        ciudadano.nombre( request.getNombre() );
        ciudadano.apellidos( request.getApellidos() );
        ciudadano.dni( request.getDni() );
        ciudadano.fechaNacimiento( request.getFechaNacimiento() );
        ciudadano.email( request.getEmail() );
        ciudadano.telefono( request.getTelefono() );
        ciudadano.direccion( request.getDireccion() );
        ciudadano.codigoPostal( request.getCodigoPostal() );
        ciudadano.municipio( request.getMunicipio() );
        ciudadano.provincia( request.getProvincia() );
        ciudadano.pais( request.getPais() );

        return ciudadano.build();
    }

    @Override
    public CiudadanoResponse toResponse(Ciudadano entity) {
        if ( entity == null ) {
            return null;
        }

        CiudadanoResponse.CiudadanoResponseBuilder ciudadanoResponse = CiudadanoResponse.builder();

        ciudadanoResponse.id( entity.getId() );
        ciudadanoResponse.nombre( entity.getNombre() );
        ciudadanoResponse.apellidos( entity.getApellidos() );
        ciudadanoResponse.dni( entity.getDni() );
        ciudadanoResponse.fechaNacimiento( entity.getFechaNacimiento() );
        ciudadanoResponse.email( entity.getEmail() );
        ciudadanoResponse.telefono( entity.getTelefono() );
        ciudadanoResponse.direccion( entity.getDireccion() );
        ciudadanoResponse.codigoPostal( entity.getCodigoPostal() );
        ciudadanoResponse.municipio( entity.getMunicipio() );
        ciudadanoResponse.provincia( entity.getProvincia() );
        ciudadanoResponse.pais( entity.getPais() );
        ciudadanoResponse.activo( entity.isActivo() );

        return ciudadanoResponse.build();
    }

    @Override
    public Ciudadano updateEntity(CiudadanoRequest request, Ciudadano entity) {
        if ( request == null ) {
            return entity;
        }

        entity.setNombre( request.getNombre() );
        entity.setApellidos( request.getApellidos() );
        entity.setDni( request.getDni() );
        entity.setFechaNacimiento( request.getFechaNacimiento() );
        entity.setEmail( request.getEmail() );
        entity.setTelefono( request.getTelefono() );
        entity.setDireccion( request.getDireccion() );
        entity.setCodigoPostal( request.getCodigoPostal() );
        entity.setMunicipio( request.getMunicipio() );
        entity.setProvincia( request.getProvincia() );
        entity.setPais( request.getPais() );

        return entity;
    }
}
