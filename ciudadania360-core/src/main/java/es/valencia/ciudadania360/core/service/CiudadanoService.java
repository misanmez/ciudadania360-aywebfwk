package es.valencia.ciudadania360.core.service;

import es.valencia.ciudadania360.core.entity.Ciudadano;
import es.valencia.ciudadania360.core.repository.CiudadanoRepository;
import es.valencia.ciudadania360.common.dto.request.CiudadanoRequestDTO;
import es.valencia.ciudadania360.common.dto.response.CiudadanoResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Servicio de lógica de negocio para Ciudadano
 * Esta es la capa de lógica de negocio compartida que será utilizada por los servicios web
 * Pertenece al módulo core
 */
@Service
@Transactional
public class CiudadanoService {

    @Autowired
    private CiudadanoRepository ciudadanoRepository;

    /**
     * Obtiene todos los ciudadanos activos
     */
    @Transactional(readOnly = true)
    public List<Ciudadano> findAllActivos() {
        return ciudadanoRepository.findByActivoTrue();
    }

    /**
     * Busca un ciudadano por ID
     */
    @Transactional(readOnly = true)
    public Optional<Ciudadano> findById(Long id) {
        return ciudadanoRepository.findById(id);
    }

    /**
     * Busca un ciudadano activo por ID
     */
    @Transactional(readOnly = true)
    public Optional<Ciudadano> findActivoById(Long id) {
        return ciudadanoRepository.findById(id)
                .filter(Ciudadano::getActivo);
    }

    /**
     * Busca un ciudadano por DNI
     */
    @Transactional(readOnly = true)
    public Optional<Ciudadano> findByDni(String dni) {
        return ciudadanoRepository.findByDni(dni);
    }

    /**
     * Busca un ciudadano activo por DNI
     */
    @Transactional(readOnly = true)
    public Optional<Ciudadano> findActivoByDni(String dni) {
        return ciudadanoRepository.findActivoByDni(dni);
    }

    /**
     * Crea un nuevo ciudadano
     */
    public Ciudadano crearCiudadano(CiudadanoRequestDTO request) {
        // Validar que no existe otro ciudadano con el mismo DNI
        if (ciudadanoRepository.existsByDni(request.getDni())) {
            throw new IllegalArgumentException("Ya existe un ciudadano con el DNI: " + request.getDni());
        }

        Ciudadano ciudadano = new Ciudadano();
        ciudadano.setNombre(request.getNombre());
        ciudadano.setApellidos(request.getApellidos());
        ciudadano.setDni(request.getDni());
        ciudadano.setFechaNacimiento(request.getFechaNacimiento());
        ciudadano.setEmail(request.getEmail());
        ciudadano.setTelefono(request.getTelefono());
        ciudadano.setDireccion(request.getDireccion());
        ciudadano.setCodigoPostal(request.getCodigoPostal());
        ciudadano.setMunicipio(request.getMunicipio());
        ciudadano.setProvincia(request.getProvincia());
        ciudadano.setPais(request.getPais());
        ciudadano.setActivo(true);

        return ciudadanoRepository.save(ciudadano);
    }

    /**
     * Actualiza un ciudadano existente
     */
    public Optional<Ciudadano> actualizarCiudadano(Long id, CiudadanoRequestDTO request) {
        return ciudadanoRepository.findById(id)
                .map(existingCiudadano -> {
                    // Validar DNI si ha cambiado
                    if (!existingCiudadano.getDni().equals(request.getDni()) && 
                        ciudadanoRepository.existsByDni(request.getDni())) {
                        throw new IllegalArgumentException("Ya existe otro ciudadano con el DNI: " + request.getDni());
                    }

                    existingCiudadano.setNombre(request.getNombre());
                    existingCiudadano.setApellidos(request.getApellidos());
                    existingCiudadano.setDni(request.getDni());
                    existingCiudadano.setFechaNacimiento(request.getFechaNacimiento());
                    existingCiudadano.setEmail(request.getEmail());
                    existingCiudadano.setTelefono(request.getTelefono());
                    existingCiudadano.setDireccion(request.getDireccion());
                    existingCiudadano.setCodigoPostal(request.getCodigoPostal());
                    existingCiudadano.setMunicipio(request.getMunicipio());
                    existingCiudadano.setProvincia(request.getProvincia());
                    existingCiudadano.setPais(request.getPais());

                    return ciudadanoRepository.save(existingCiudadano);
                });
    }

    /**
     * Elimina lógicamente un ciudadano (marca como inactivo)
     */
    public boolean eliminarCiudadano(Long id) {
        return ciudadanoRepository.findById(id)
                .map(ciudadano -> {
                    ciudadano.setActivo(false);
                    ciudadanoRepository.save(ciudadano);
                    return true;
                })
                .orElse(false);
    }

    /**
     * Busca ciudadanos por municipio
     */
    @Transactional(readOnly = true)
    public List<Ciudadano> findByMunicipio(String municipio) {
        return ciudadanoRepository.findByMunicipioAndActivoTrue(municipio);
    }

    /**
     * Busca ciudadanos por provincia
     */
    @Transactional(readOnly = true)
    public List<Ciudadano> findByProvincia(String provincia) {
        return ciudadanoRepository.findByProvinciaAndActivoTrue(provincia);
    }
}
