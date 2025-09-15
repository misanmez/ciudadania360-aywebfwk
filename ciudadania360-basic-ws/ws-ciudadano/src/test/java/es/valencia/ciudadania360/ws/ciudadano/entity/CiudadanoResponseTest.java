package es.valencia.ciudadania360.ws.ciudadano.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

/**
 * Tests unitarios para CiudadanoResponse
 */
class CiudadanoResponseTest {

    private CiudadanoResponse response;

    @BeforeEach
    void setUp() {
        response = new CiudadanoResponse();
    }

    @Test
    void testConstructor() {
        assertNotNull(response);
        assertNull(response.getId());
        assertNull(response.getNombre());
        assertNull(response.getApellidos());
        assertNull(response.getDni());
        assertNull(response.getFechaNacimiento());
        assertNull(response.getEmail());
        assertNull(response.getTelefono());
        assertNull(response.getDireccion());
        assertNull(response.getCodigoPostal());
        assertNull(response.getMunicipio());
        assertNull(response.getProvincia());
        assertNull(response.getPais());
        assertFalse(response.isActivo());
    }

    @Test
    void testSettersAndGetters() {
        // Test ID
        response.setId(1L);
        assertEquals(1L, response.getId());

        // Test Nombre
        response.setNombre("Carlos");
        assertEquals("Carlos", response.getNombre());

        // Test Apellidos
        response.setApellidos("Martín Ruiz");
        assertEquals("Martín Ruiz", response.getApellidos());

        // Test DNI
        response.setDni("11223344C");
        assertEquals("11223344C", response.getDni());

        // Test Fecha de Nacimiento
        LocalDate fechaNacimiento = LocalDate.of(1992, 3, 10);
        response.setFechaNacimiento(fechaNacimiento);
        assertEquals(fechaNacimiento, response.getFechaNacimiento());

        // Test Email
        response.setEmail("carlos.martin@email.com");
        assertEquals("carlos.martin@email.com", response.getEmail());

        // Test Teléfono
        response.setTelefono("600555777");
        assertEquals("600555777", response.getTelefono());

        // Test Dirección
        response.setDireccion("Plaza del Ayuntamiento 1");
        assertEquals("Plaza del Ayuntamiento 1", response.getDireccion());

        // Test Código Postal
        response.setCodigoPostal("46003");
        assertEquals("46003", response.getCodigoPostal());

        // Test Municipio
        response.setMunicipio("Valencia");
        assertEquals("Valencia", response.getMunicipio());

        // Test Provincia
        response.setProvincia("Valencia");
        assertEquals("Valencia", response.getProvincia());

        // Test País
        response.setPais("España");
        assertEquals("España", response.getPais());

        // Test Activo
        response.setActivo(true);
        assertTrue(response.isActivo());
    }

    @Test
    void testToString() {
        response.setId(1L);
        response.setNombre("Carlos");
        response.setApellidos("Martín Ruiz");
        response.setDni("11223344C");
        response.setActivo(true);

        String toString = response.toString();
        assertNotNull(toString);
        assertTrue(toString.contains("Carlos"));
        assertTrue(toString.contains("Martín Ruiz"));
        assertTrue(toString.contains("11223344C"));
    }

    @Test
    void testEqualsAndHashCode() {
        CiudadanoResponse response1 = new CiudadanoResponse();
        response1.setId(1L);
        response1.setDni("11223344C");

        CiudadanoResponse response2 = new CiudadanoResponse();
        response2.setId(1L);
        response2.setDni("11223344C");

        CiudadanoResponse response3 = new CiudadanoResponse();
        response3.setId(2L);
        response3.setDni("99887766D");

        // Test equals
        assertEquals(response1, response2);
        assertNotEquals(response1, response3);

        // Test hashCode
        assertEquals(response1.hashCode(), response2.hashCode());
        assertNotEquals(response1.hashCode(), response3.hashCode());
    }
}
