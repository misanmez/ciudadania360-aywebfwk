package es.valencia.ciudadania360.ws.ciudadano.service;

import es.valencia.ciudadania360.ws.ciudadano.entity.Ciudadano;
import es.valencia.ciudadania360.ws.ciudadano.entity.CiudadanoRequest;
import es.valencia.ciudadania360.ws.ciudadano.entity.CiudadanoResponse;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

/**
 * Tests unitarios para CiudadanoService
 */
@ExtendWith(MockitoExtension.class)
class CiudadanoServiceTest {

    @Mock
    private CiudadanoMapper ciudadanoMapper;

    @InjectMocks
    private CiudadanoService ciudadanoService;

    private Ciudadano ciudadano;
    private CiudadanoRequest request;
    private CiudadanoResponse response;

    @BeforeEach
    void setUp() {
        // Setup Ciudadano entity
        ciudadano = new Ciudadano();
        ciudadano.setId(1L);
        ciudadano.setNombre("Ana");
        ciudadano.setApellidos("Sánchez Torres");
        ciudadano.setDni("55667788E");
        ciudadano.setFechaNacimiento(LocalDate.of(1988, 12, 5));
        ciudadano.setEmail("ana.sanchez@email.com");
        ciudadano.setTelefono("600111222");
        ciudadano.setDireccion("Calle de la Paz 789");
        ciudadano.setCodigoPostal("46004");
        ciudadano.setMunicipio("Valencia");
        ciudadano.setProvincia("Valencia");
        ciudadano.setPais("España");
        ciudadano.setActivo(true);

        // Setup CiudadanoRequest
        request = new CiudadanoRequest();
        request.setNombre("Ana");
        request.setApellidos("Sánchez Torres");
        request.setDni("55667788E");
        request.setFechaNacimiento(LocalDate.of(1988, 12, 5));
        request.setEmail("ana.sanchez@email.com");
        request.setTelefono("600111222");
        request.setDireccion("Calle de la Paz 789");
        request.setCodigoPostal("46004");
        request.setMunicipio("Valencia");
        request.setProvincia("Valencia");
        request.setPais("España");

        // Setup CiudadanoResponse
        response = new CiudadanoResponse();
        response.setId(1L);
        response.setNombre("Ana");
        response.setApellidos("Sánchez Torres");
        response.setDni("55667788E");
        response.setFechaNacimiento(LocalDate.of(1988, 12, 5));
        response.setEmail("ana.sanchez@email.com");
        response.setTelefono("600111222");
        response.setDireccion("Calle de la Paz 789");
        response.setCodigoPostal("46004");
        response.setMunicipio("Valencia");
        response.setProvincia("Valencia");
        response.setPais("España");
        response.setActivo(true);
    }

    @Test
    void testFindAll() {
        // Given
        List<Ciudadano> ciudadanos = Arrays.asList(ciudadano);
        List<CiudadanoResponse> responses = Arrays.asList(response);

        when(ciudadanoMapper.toResponse(any(Ciudadano.class))).thenReturn(response);

        // When
        List<CiudadanoResponse> result = ciudadanoService.findAll();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(response.getId(), result.get(0).getId());
        assertEquals(response.getNombre(), result.get(0).getNombre());
        verify(ciudadanoMapper, times(1)).toResponse(any(Ciudadano.class));
    }

    @Test
    void testFindById_ExistingId() {
        // Given
        Long id = 1L;
        when(ciudadanoMapper.toResponse(any(Ciudadano.class))).thenReturn(response);

        // When
        Optional<CiudadanoResponse> result = ciudadanoService.findById(id);

        // Then
        assertTrue(result.isPresent());
        assertEquals(response.getId(), result.get().getId());
        assertEquals(response.getNombre(), result.get().getNombre());
        verify(ciudadanoMapper, times(1)).toResponse(any(Ciudadano.class));
    }

    @Test
    void testFindById_NonExistingId() {
        // Given
        Long id = 999L;

        // When
        Optional<CiudadanoResponse> result = ciudadanoService.findById(id);

        // Then
        assertFalse(result.isPresent());
        verify(ciudadanoMapper, never()).toResponse(any(Ciudadano.class));
    }

    @Test
    void testSave() {
        // Given
        when(ciudadanoMapper.toEntity(any(CiudadanoRequest.class))).thenReturn(ciudadano);
        when(ciudadanoMapper.toResponse(any(Ciudadano.class))).thenReturn(response);

        // When
        CiudadanoResponse result = ciudadanoService.save(request);

        // Then
        assertNotNull(result);
        assertEquals(response.getId(), result.getId());
        assertEquals(response.getNombre(), result.getNombre());
        verify(ciudadanoMapper, times(1)).toEntity(request);
        verify(ciudadanoMapper, times(1)).toResponse(any(Ciudadano.class));
    }

    @Test
    void testUpdate_ExistingId() {
        // Given
        Long id = 1L;
        when(ciudadanoMapper.toResponse(any(Ciudadano.class))).thenReturn(response);

        // When
        Optional<CiudadanoResponse> result = ciudadanoService.update(id, request);

        // Then
        assertTrue(result.isPresent());
        assertEquals(response.getId(), result.get().getId());
        assertEquals(response.getNombre(), result.get().getNombre());
        verify(ciudadanoMapper, times(1)).updateEntity(eq(request), any(Ciudadano.class));
        verify(ciudadanoMapper, times(1)).toResponse(any(Ciudadano.class));
    }

    @Test
    void testUpdate_NonExistingId() {
        // Given
        Long id = 999L;

        // When
        Optional<CiudadanoResponse> result = ciudadanoService.update(id, request);

        // Then
        assertFalse(result.isPresent());
        verify(ciudadanoMapper, never()).updateEntity(any(CiudadanoRequest.class), any(Ciudadano.class));
        verify(ciudadanoMapper, never()).toResponse(any(Ciudadano.class));
    }

    @Test
    void testDelete_ExistingId() {
        // Given
        Long id = 1L;

        // When
        boolean result = ciudadanoService.delete(id);

        // Then
        assertTrue(result);
    }

    @Test
    void testDelete_NonExistingId() {
        // Given
        Long id = 999L;

        // When
        boolean result = ciudadanoService.delete(id);

        // Then
        assertFalse(result);
    }
}
