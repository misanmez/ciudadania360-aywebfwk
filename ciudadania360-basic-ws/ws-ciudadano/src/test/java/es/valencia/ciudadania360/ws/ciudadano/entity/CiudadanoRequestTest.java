package es.valencia.ciudadania360.ws.ciudadano.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

/**
 * Tests unitarios para CiudadanoRequest
 */
class CiudadanoRequestTest {

    private CiudadanoRequest request;

    @BeforeEach
    void setUp() {
        request = new CiudadanoRequest();
    }

    @Test
    void testConstructor() {
        assertNotNull(request);
        assertNull(request.getNombre());
        assertNull(request.getApellidos());
        assertNull(request.getDni());
        assertNull(request.getFechaNacimiento());
        assertNull(request.getEmail());
        assertNull(request.getTelefono());
        assertNull(request.getDireccion());
        assertNull(request.getCodigoPostal());
        assertNull(request.getMunicipio());
        assertNull(request.getProvincia());
        assertNull(request.getPais());
    }

    @Test
    void testSettersAndGetters() {
        // Test Nombre
        request.setNombre("María");
        assertEquals("María", request.getNombre());

        // Test Apellidos
        request.setApellidos("González López");
        assertEquals("González López", request.getApellidos());

        // Test DNI
        request.setDni("87654321B");
        assertEquals("87654321B", request.getDni());

        // Test Fecha de Nacimiento
        LocalDate fechaNacimiento = LocalDate.of(1985, 8, 22);
        request.setFechaNacimiento(fechaNacimiento);
        assertEquals(fechaNacimiento, request.getFechaNacimiento());

        // Test Email
        request.setEmail("maria.gonzalez@email.com");
        assertEquals("maria.gonzalez@email.com", request.getEmail());

        // Test Teléfono
        request.setTelefono("600987654");
        assertEquals("600987654", request.getTelefono());

        // Test Dirección
        request.setDireccion("Avenida del Puerto 456");
        assertEquals("Avenida del Puerto 456", request.getDireccion());

        // Test Código Postal
        request.setCodigoPostal("46002");
        assertEquals("46002", request.getCodigoPostal());

        // Test Municipio
        request.setMunicipio("Valencia");
        assertEquals("Valencia", request.getMunicipio());

        // Test Provincia
        request.setProvincia("Valencia");
        assertEquals("Valencia", request.getProvincia());

        // Test País
        request.setPais("España");
        assertEquals("España", request.getPais());
    }

    @Test
    void testToString() {
        request.setNombre("María");
        request.setApellidos("González López");
        request.setDni("87654321B");

        String toString = request.toString();
        assertNotNull(toString);
        assertTrue(toString.contains("María"));
        assertTrue(toString.contains("González López"));
        assertTrue(toString.contains("87654321B"));
    }

    @Test
    void testEqualsAndHashCode() {
        CiudadanoRequest request1 = new CiudadanoRequest();
        request1.setDni("87654321B");
        request1.setNombre("María");

        CiudadanoRequest request2 = new CiudadanoRequest();
        request2.setDni("87654321B");
        request2.setNombre("María");

        CiudadanoRequest request3 = new CiudadanoRequest();
        request3.setDni("12345678A");
        request3.setNombre("Juan");

        // Test equals
        assertEquals(request1, request2);
        assertNotEquals(request1, request3);

        // Test hashCode
        assertEquals(request1.hashCode(), request2.hashCode());
        assertNotEquals(request1.hashCode(), request3.hashCode());
    }
}
