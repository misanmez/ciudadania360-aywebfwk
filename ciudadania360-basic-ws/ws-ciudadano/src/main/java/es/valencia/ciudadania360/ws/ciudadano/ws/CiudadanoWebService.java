package es.valencia.ciudadania360.ws.ciudadano.ws;

import es.valencia.ciudadania360.ws.ciudadano.entity.CiudadanoRequest;
import es.valencia.ciudadania360.ws.ciudadano.entity.CiudadanoResponse;
import es.valencia.ciudadania360.ws.ciudadano.service.CiudadanoService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio Web SOAP para Ciudadano
 */
@WebService(serviceName = "CiudadanoWebService", 
           portName = "CiudadanoWebServicePort",
           targetNamespace = "http://ws.ciudadania360.valencia.es/")
@Service
public class CiudadanoWebService {

    @Autowired
    private CiudadanoService ciudadanoService;

    @WebMethod(operationName = "listarCiudadanos")
    public List<CiudadanoResponse> listarCiudadanos() {
        return ciudadanoService.findAll();
    }

    @WebMethod(operationName = "obtenerCiudadano")
    public CiudadanoResponse obtenerCiudadano(@WebParam(name = "id") Long id) {
        Optional<CiudadanoResponse> ciudadano = ciudadanoService.findById(id);
        if (ciudadano.isPresent()) {
            return ciudadano.get();
        } else {
            throw new RuntimeException("Ciudadano no encontrado con ID: " + id);
        }
    }

    @WebMethod(operationName = "crearCiudadano")
    public CiudadanoResponse crearCiudadano(@WebParam(name = "ciudadanoRequest") CiudadanoRequest request) {
        return ciudadanoService.save(request);
    }

    @WebMethod(operationName = "actualizarCiudadano")
    public CiudadanoResponse actualizarCiudadano(@WebParam(name = "id") Long id, 
                                                @WebParam(name = "ciudadanoRequest") CiudadanoRequest request) {
        Optional<CiudadanoResponse> ciudadano = ciudadanoService.update(id, request);
        if (ciudadano.isPresent()) {
            return ciudadano.get();
        } else {
            throw new RuntimeException("Ciudadano no encontrado con ID: " + id);
        }
    }

    @WebMethod(operationName = "eliminarCiudadano")
    public boolean eliminarCiudadano(@WebParam(name = "id") Long id) {
        return ciudadanoService.delete(id);
    }
}
