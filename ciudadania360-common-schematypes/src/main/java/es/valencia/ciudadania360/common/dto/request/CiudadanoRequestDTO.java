package es.valencia.ciudadania360.common.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

/**
 * DTO para requests de Ciudadano
 * Validaciones incluidas para servicios SOAP
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CiudadanoRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
    private String nombre;

    @NotBlank(message = "Los apellidos son obligatorios")
    @Size(max = 200, message = "Los apellidos no pueden exceder 200 caracteres")
    private String apellidos;

    @NotBlank(message = "El DNI es obligatorio")
    @Pattern(regexp = "^[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKE]$", message = "Formato de DNI inválido")
    private String dni;

    private LocalDate fechaNacimiento;

    @Email(message = "Formato de email inválido")
    @Size(max = 255, message = "El email no puede exceder 255 caracteres")
    private String email;

    @Pattern(regexp = "^[+]?[0-9\\s\\-()]{9,20}$", message = "Formato de teléfono inválido")
    private String telefono;

    @Size(max = 500, message = "La dirección no puede exceder 500 caracteres")
    private String direccion;

    @Pattern(regexp = "^[0-9]{5}$", message = "Formato de código postal inválido")
    private String codigoPostal;

    @Size(max = 100, message = "El municipio no puede exceder 100 caracteres")
    private String municipio;

    @Size(max = 100, message = "La provincia no puede exceder 100 caracteres")
    private String provincia;

    @Size(max = 100, message = "El país no puede exceder 100 caracteres")
    private String pais;
}
