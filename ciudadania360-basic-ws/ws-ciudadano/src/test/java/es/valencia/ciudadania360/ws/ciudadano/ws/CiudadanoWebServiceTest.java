package es.valencia.ciudadania360.ws.ciudadano.ws;

import es.valencia.ciudadania360.ws.ciudadano.entity.CiudadanoRequest;
import es.valencia.ciudadania360.ws.ciudadano.entity.CiudadanoResponse;
import es.valencia.ciudadania360.ws.ciudadano.service.CiudadanoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * Tests unitarios para CiudadanoWebService
 */
@ExtendWith(MockitoExtension.class)
class CiudadanoWebServiceTest {

    @Mock
    private CiudadanoService ciudadanoService;

    @InjectMocks
    private CiudadanoWebService ciudadanoWebService;

    private CiudadanoRequest request;
    private CiudadanoResponse response;

    @BeforeEach
    void setUp() {
        // Setup CiudadanoRequest
        request = new CiudadanoRequest();
        request.setNombre("Luis");
        request.setApellidos("Fernández Moreno");
        request.setDni("33445566F");
        request.setFechaNacimiento(LocalDate.of(1995, 7, 18));
        request.setEmail("luis.fernandez@email.com");
        request.setTelefono("600333444");
        request.setDireccion("Calle Colón 321");
        request.setCodigoPostal("46005");
        request.setMunicipio("Valencia");
        request.setProvincia("Valencia");
        request.setPais("España");

        // Setup CiudadanoResponse
        response = new CiudadanoResponse();
        response.setId(1L);
        response.setNombre("Luis");
        response.setApellidos("Fernández Moreno");
        response.setDni("33445566F");
        response.setFechaNacimiento(LocalDate.of(1995, 7, 18));
        response.setEmail("luis.fernandez@email.com");
        response.setTelefono("600333444");
        response.setDireccion("Calle Colón 321");
        response.setCodigoPostal("46005");
        response.setMunicipio("Valencia");
        response.setProvincia("Valencia");
        response.setPais("España");
        response.setActivo(true);
    }

    @Test
    void testListarCiudadanos() {
        // Given
        List<CiudadanoResponse> responses = Arrays.asList(response);
        when(ciudadanoService.findAll()).thenReturn(responses);

        // When
        List<CiudadanoResponse> result = ciudadanoWebService.listarCiudadanos();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(response.getId(), result.get(0).getId());
        assertEquals(response.getNombre(), result.get(0).getNombre());
        verify(ciudadanoService, times(1)).findAll();
    }

    @Test
    void testObtenerCiudadano_ExistingId() {
        // Given
        Long id = 1L;
        when(ciudadanoService.findById(id)).thenReturn(Optional.of(response));

        // When
        CiudadanoResponse result = ciudadanoWebService.obtenerCiudadano(id);

        // Then
        assertNotNull(result);
        assertEquals(response.getId(), result.getId());
        assertEquals(response.getNombre(), result.getNombre());
        verify(ciudadanoService, times(1)).findById(id);
    }

    @Test
    void testObtenerCiudadano_NonExistingId() {
        // Given
        Long id = 999L;
        when(ciudadanoService.findById(id)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(RuntimeException.class, () -> {
            ciudadanoWebService.obtenerCiudadano(id);
        });
        verify(ciudadanoService, times(1)).findById(id);
    }

    @Test
    void testCrearCiudadano() {
        // Given
        when(ciudadanoService.save(any(CiudadanoRequest.class))).thenReturn(response);

        // When
        CiudadanoResponse result = ciudadanoWebService.crearCiudadano(request);

        // Then
        assertNotNull(result);
        assertEquals(response.getId(), result.getId());
        assertEquals(response.getNombre(), result.getNombre());
        verify(ciudadanoService, times(1)).save(request);
    }

    @Test
    void testActualizarCiudadano_ExistingId() {
        // Given
        Long id = 1L;
        when(ciudadanoService.update(id, request)).thenReturn(Optional.of(response));

        // When
        CiudadanoResponse result = ciudadanoWebService.actualizarCiudadano(id, request);

        // Then
        assertNotNull(result);
        assertEquals(response.getId(), result.getId());
        assertEquals(response.getNombre(), result.getNombre());
        verify(ciudadanoService, times(1)).update(id, request);
    }

    @Test
    void testActualizarCiudadano_NonExistingId() {
        // Given
        Long id = 999L;
        when(ciudadanoService.update(id, request)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(RuntimeException.class, () -> {
            ciudadanoWebService.actualizarCiudadano(id, request);
        });
        verify(ciudadanoService, times(1)).update(id, request);
    }

    @Test
    void testEliminarCiudadano_ExistingId() {
        // Given
        Long id = 1L;
        when(ciudadanoService.delete(id)).thenReturn(true);

        // When
        boolean result = ciudadanoWebService.eliminarCiudadano(id);

        // Then
        assertTrue(result);
        verify(ciudadanoService, times(1)).delete(id);
    }

    @Test
    void testEliminarCiudadano_NonExistingId() {
        // Given
        Long id = 999L;
        when(ciudadanoService.delete(id)).thenReturn(false);

        // When
        boolean result = ciudadanoWebService.eliminarCiudadano(id);

        // Then
        assertFalse(result);
        verify(ciudadanoService, times(1)).delete(id);
    }
}
