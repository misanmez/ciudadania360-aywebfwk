package es.valencia.ciudadania360.ws.ciudadano.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * DTO para requests de Ciudadano
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CiudadanoRequest {

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
}
