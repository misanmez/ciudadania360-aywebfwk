package es.valencia.ciudadania360.ws.ciudadano.config;

import es.valencia.ciudadania360.ws.ciudadano.ws.CiudadanoWebService;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración de endpoints de servicios web SOAP
 */
@Configuration
public class CxfWebServiceConfig {

    @Autowired
    private Bus bus;

    @Autowired
    private CiudadanoWebService ciudadanoWebService;

    @Bean
    public EndpointImpl ciudadanoEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, ciudadanoWebService);
        endpoint.publish("/CiudadanoWebService");
        return endpoint;
    }
}
