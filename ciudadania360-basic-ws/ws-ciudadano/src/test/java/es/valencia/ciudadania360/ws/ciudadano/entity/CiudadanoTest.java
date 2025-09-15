package es.valencia.ciudadania360.ws.ciudadano.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

/**
 * Tests unitarios para la entidad Ciudadano
 */
class CiudadanoTest {

    private Ciudadano ciudadano;

    @BeforeEach
    void setUp() {
        ciudadano = new Ciudadano();
    }

    @Test
    void testConstructor() {
        assertNotNull(ciudadano);
        assertNull(ciudadano.getId());
        assertNull(ciudadano.getNombre());
        assertNull(ciudadano.getApellidos());
        assertNull(ciudadano.getDni());
        assertNull(ciudadano.getFechaNacimiento());
        assertNull(ciudadano.getEmail());
        assertNull(ciudadano.getTelefono());
        assertNull(ciudadano.getDireccion());
        assertNull(ciudadano.getCodigoPostal());
        assertNull(ciudadano.getMunicipio());
        assertNull(ciudadano.getProvincia());
        assertNull(ciudadano.getPais());
        assertTrue(ciudadano.isActivo()); // @Builder.Default hace que sea true por defecto
    }

    @Test
    void testSettersAndGetters() {
        // Test ID
        ciudadano.setId(1L);
        assertEquals(1L, ciudadano.getId());

        // Test Nombre
        ciudadano.setNombre("Juan");
        assertEquals("Juan", ciudadano.getNombre());

        // Test Apellidos
        ciudadano.setApellidos("Pérez García");
        assertEquals("Pérez García", ciudadano.getApellidos());

        // Test DNI
        ciudadano.setDni("12345678A");
        assertEquals("12345678A", ciudadano.getDni());

        // Test Fecha de Nacimiento
        LocalDate fechaNacimiento = LocalDate.of(1990, 5, 15);
        ciudadano.setFechaNacimiento(fechaNacimiento);
        assertEquals(fechaNacimiento, ciudadano.getFechaNacimiento());

        // Test Email
        ciudadano.setEmail("juan.perez@email.com");
        assertEquals("juan.perez@email.com", ciudadano.getEmail());

        // Test Teléfono
        ciudadano.setTelefono("600123456");
        assertEquals("600123456", ciudadano.getTelefono());

        // Test Dirección
        ciudadano.setDireccion("Calle Mayor 123");
        assertEquals("Calle Mayor 123", ciudadano.getDireccion());

        // Test Código Postal
        ciudadano.setCodigoPostal("46001");
        assertEquals("46001", ciudadano.getCodigoPostal());

        // Test Municipio
        ciudadano.setMunicipio("Valencia");
        assertEquals("Valencia", ciudadano.getMunicipio());

        // Test Provincia
        ciudadano.setProvincia("Valencia");
        assertEquals("Valencia", ciudadano.getProvincia());

        // Test País
        ciudadano.setPais("España");
        assertEquals("España", ciudadano.getPais());

        // Test Activo
        ciudadano.setActivo(true);
        assertTrue(ciudadano.isActivo());
    }

    @Test
    void testToString() {
        ciudadano.setId(1L);
        ciudadano.setNombre("Juan");
        ciudadano.setApellidos("Pérez García");
        ciudadano.setDni("12345678A");

        String toString = ciudadano.toString();
        assertNotNull(toString);
        assertTrue(toString.contains("Juan"));
        assertTrue(toString.contains("Pérez García"));
        assertTrue(toString.contains("12345678A"));
    }

    @Test
    void testEqualsAndHashCode() {
        Ciudadano ciudadano1 = new Ciudadano();
        ciudadano1.setId(1L);
        ciudadano1.setDni("12345678A");

        Ciudadano ciudadano2 = new Ciudadano();
        ciudadano2.setId(1L);
        ciudadano2.setDni("12345678A");

        Ciudadano ciudadano3 = new Ciudadano();
        ciudadano3.setId(2L);
        ciudadano3.setDni("87654321B");

        // Test equals
        assertEquals(ciudadano1, ciudadano2);
        assertNotEquals(ciudadano1, ciudadano3);

        // Test hashCode
        assertEquals(ciudadano1.hashCode(), ciudadano2.hashCode());
        assertNotEquals(ciudadano1.hashCode(), ciudadano3.hashCode());
    }
}
