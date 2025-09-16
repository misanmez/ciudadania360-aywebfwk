package es.valencia.ciudadania360.core.mapper;

import es.valencia.ciudadania360.common.dto.request.CiudadanoRequestDTO;
import es.valencia.ciudadania360.common.dto.response.CiudadanoResponseDTO;
import es.valencia.ciudadania360.core.entity.Ciudadano;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-16T16:22:26+0200",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.43.0.v20250819-1513, environment: Java 21.0.8 (Eclipse Adoptium)"
)
public class CiudadanoMapperImpl implements CiudadanoMapper {

    @Override
    public CiudadanoResponseDTO toResponseDTO(Ciudadano ciudadano) {
        if ( ciudadano == null ) {
            return null;
        }

        CiudadanoResponseDTO.CiudadanoResponseDTOBuilder<?, ?> ciudadanoResponseDTO = CiudadanoResponseDTO.builder();

        if ( ciudadano.getActivo() != null ) {
            ciudadanoResponseDTO.activo( ciudadano.getActivo() );
        }
        ciudadanoResponseDTO.apellidos( ciudadano.getApellidos() );
        ciudadanoResponseDTO.codigoPostal( ciudadano.getCodigoPostal() );
        ciudadanoResponseDTO.direccion( ciudadano.getDireccion() );
        ciudadanoResponseDTO.dni( ciudadano.getDni() );
        ciudadanoResponseDTO.email( ciudadano.getEmail() );
        ciudadanoResponseDTO.fechaNacimiento( ciudadano.getFechaNacimiento() );
        ciudadanoResponseDTO.id( ciudadano.getId() );
        ciudadanoResponseDTO.municipio( ciudadano.getMunicipio() );
        ciudadanoResponseDTO.nombre( ciudadano.getNombre() );
        ciudadanoResponseDTO.pais( ciudadano.getPais() );
        ciudadanoResponseDTO.provincia( ciudadano.getProvincia() );
        ciudadanoResponseDTO.telefono( ciudadano.getTelefono() );

        return ciudadanoResponseDTO.build();
    }

    @Override
    public Ciudadano toEntity(CiudadanoRequestDTO request) {
        if ( request == null ) {
            return null;
        }

        Ciudadano.CiudadanoBuilder<?, ?> ciudadano = Ciudadano.builder();

        ciudadano.apellidos( request.getApellidos() );
        ciudadano.codigoPostal( request.getCodigoPostal() );
        ciudadano.direccion( request.getDireccion() );
        ciudadano.dni( request.getDni() );
        ciudadano.email( request.getEmail() );
        ciudadano.fechaNacimiento( request.getFechaNacimiento() );
        ciudadano.municipio( request.getMunicipio() );
        ciudadano.nombre( request.getNombre() );
        ciudadano.pais( request.getPais() );
        ciudadano.provincia( request.getProvincia() );
        ciudadano.telefono( request.getTelefono() );

        return ciudadano.build();
    }

    @Override
    public void updateEntity(CiudadanoRequestDTO request, Ciudadano entity) {
        if ( request == null ) {
            return;
        }

        entity.setApellidos( request.getApellidos() );
        entity.setCodigoPostal( request.getCodigoPostal() );
        entity.setDireccion( request.getDireccion() );
        entity.setDni( request.getDni() );
        entity.setEmail( request.getEmail() );
        entity.setFechaNacimiento( request.getFechaNacimiento() );
        entity.setMunicipio( request.getMunicipio() );
        entity.setNombre( request.getNombre() );
        entity.setPais( request.getPais() );
        entity.setProvincia( request.getProvincia() );
        entity.setTelefono( request.getTelefono() );
    }
}
