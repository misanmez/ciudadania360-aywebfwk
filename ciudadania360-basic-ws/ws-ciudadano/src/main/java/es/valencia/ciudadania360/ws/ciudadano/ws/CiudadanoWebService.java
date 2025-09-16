package es.valencia.ciudadania360.ws.ciudadano.ws;

import es.valencia.ciudadania360.core.entity.Ciudadano;
import es.valencia.ciudadania360.core.service.CiudadanoService;
import es.valencia.ciudadania360.common.dto.request.CiudadanoRequestDTO;
import es.valencia.ciudadania360.common.dto.response.CiudadanoResponseDTO;
import es.valencia.ciudadania360.core.mapper.CiudadanoMapper;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio Web SOAP para Ciudadano
 * Esta es la capa de servicios web que expone la funcionalidad del backend
 */
@WebService(serviceName = "CiudadanoWebService", 
           portName = "CiudadanoWebServicePort",
           targetNamespace = "http://ws.ciudadania360.valencia.es/")
@Service
public class CiudadanoWebService {

    @Autowired
    private CiudadanoService ciudadanoService;

    @Autowired
    private CiudadanoMapper ciudadanoMapper;

    @WebMethod(operationName = "listarCiudadanos")
    public List<CiudadanoResponseDTO> listarCiudadanos() {
        List<Ciudadano> ciudadanos = ciudadanoService.findAllActivos();
        return ciudadanos.stream()
                .map(ciudadanoMapper::toResponseDTO)
                .toList();
    }

    @WebMethod(operationName = "obtenerCiudadano")
    public CiudadanoResponseDTO obtenerCiudadano(@WebParam(name = "id") Long id) {
        Optional<Ciudadano> ciudadano = ciudadanoService.findActivoById(id);
        if (ciudadano.isPresent()) {
            return ciudadanoMapper.toResponseDTO(ciudadano.get());
        } else {
            throw new RuntimeException("Ciudadano no encontrado con ID: " + id);
        }
    }

    @WebMethod(operationName = "obtenerCiudadanoPorDni")
    public CiudadanoResponseDTO obtenerCiudadanoPorDni(@WebParam(name = "dni") String dni) {
        Optional<Ciudadano> ciudadano = ciudadanoService.findActivoByDni(dni);
        if (ciudadano.isPresent()) {
            return ciudadanoMapper.toResponseDTO(ciudadano.get());
        } else {
            throw new RuntimeException("Ciudadano no encontrado con DNI: " + dni);
        }
    }

    @WebMethod(operationName = "crearCiudadano")
    public CiudadanoResponseDTO crearCiudadano(@WebParam(name = "ciudadanoRequest") CiudadanoRequestDTO request) {
        try {
            Ciudadano ciudadano = ciudadanoService.crearCiudadano(request);
            return ciudadanoMapper.toResponseDTO(ciudadano);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error al crear ciudadano: " + e.getMessage());
        }
    }

    @WebMethod(operationName = "actualizarCiudadano")
    public CiudadanoResponseDTO actualizarCiudadano(@WebParam(name = "id") Long id, 
                                                   @WebParam(name = "ciudadanoRequest") CiudadanoRequestDTO request) {
        try {
            Optional<Ciudadano> ciudadano = ciudadanoService.actualizarCiudadano(id, request);
            if (ciudadano.isPresent()) {
                return ciudadanoMapper.toResponseDTO(ciudadano.get());
            } else {
                throw new RuntimeException("Ciudadano no encontrado con ID: " + id);
            }
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error al actualizar ciudadano: " + e.getMessage());
        }
    }

    @WebMethod(operationName = "eliminarCiudadano")
    public boolean eliminarCiudadano(@WebParam(name = "id") Long id) {
        return ciudadanoService.eliminarCiudadano(id);
    }

    @WebMethod(operationName = "buscarCiudadanosPorMunicipio")
    public List<CiudadanoResponseDTO> buscarCiudadanosPorMunicipio(@WebParam(name = "municipio") String municipio) {
        List<Ciudadano> ciudadanos = ciudadanoService.findByMunicipio(municipio);
        return ciudadanos.stream()
                .map(ciudadanoMapper::toResponseDTO)
                .toList();
    }

    @WebMethod(operationName = "buscarCiudadanosPorProvincia")
    public List<CiudadanoResponseDTO> buscarCiudadanosPorProvincia(@WebParam(name = "provincia") String provincia) {
        List<Ciudadano> ciudadanos = ciudadanoService.findByProvincia(provincia);
        return ciudadanos.stream()
                .map(ciudadanoMapper::toResponseDTO)
                .toList();
    }
}