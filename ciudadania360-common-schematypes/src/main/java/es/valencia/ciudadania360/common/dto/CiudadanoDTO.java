package es.valencia.ciudadania360.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * DTO compartido para Ciudadano
 * Utilizado en todos los módulos del sistema
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CiudadanoDTO {

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
