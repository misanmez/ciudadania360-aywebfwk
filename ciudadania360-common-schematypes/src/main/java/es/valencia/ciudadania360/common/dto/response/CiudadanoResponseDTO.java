package es.valencia.ciudadania360.common.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

/**
 * DTO para responses de Ciudadano
 * Incluye todos los campos del ciudadano para respuestas SOAP
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CiudadanoResponseDTO {

    private Long id;
    private String nombre;
    private String apellidos;
    private String dni;
    private LocalDate fechaNacimiento;
    private String email;
    private String telefono;
    private String direccion;
    private String codigoPostal;
    private String municipio;
    private String provincia;
    private String pais;
    private boolean activo;
}
