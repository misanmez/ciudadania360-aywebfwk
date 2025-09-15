package es.valencia.ciudadania360.ws.ciudadano.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Entidad JPA para Ciudadano
 */
@Entity
@Table(name = "ciudadanos")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ciudadano {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "apellidos", nullable = false, length = 200)
    private String apellidos;

    @Column(name = "dni", nullable = false, unique = true, length = 9)
    private String dni;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "telefono", length = 20)
    private String telefono;

    @Column(name = "direccion", length = 500)
    private String direccion;

    @Column(name = "codigo_postal", length = 10)
    private String codigoPostal;

    @Column(name = "municipio", length = 100)
    private String municipio;

    @Column(name = "provincia", length = 100)
    private String provincia;

    @Column(name = "pais", length = 100)
    private String pais;

    @Column(name = "activo", nullable = false)
    @Builder.Default
    private boolean activo = true;
}
