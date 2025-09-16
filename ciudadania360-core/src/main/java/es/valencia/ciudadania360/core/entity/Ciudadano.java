package es.valencia.ciudadania360.core.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Entidad JPA para Ciudadano
 * Mapea con la tabla ciudadano.ciudadanos
 * Esta entidad pertenece al módulo core (lógica de negocio compartida)
 */
@Entity
@Table(name = "ciudadanos", schema = "ciudadano")
@Data
@SuperBuilder
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
    
    @Column(name = "dni", nullable = false, length = 9, unique = true)
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
    private Boolean activo = true;
    
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
    
    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;
    
    @PrePersist
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now();
        fechaModificacion = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        fechaModificacion = LocalDateTime.now();
    }
}
