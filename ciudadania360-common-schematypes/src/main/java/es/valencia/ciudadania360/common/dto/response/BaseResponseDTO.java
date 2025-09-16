package es.valencia.ciudadania360.common.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO base para todas las respuestas del sistema
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponseDTO<T> {

    private boolean exito;
    private String codigo;
    private String mensaje;
    private T datos;
    private List<String> errores;
    private LocalDateTime timestamp;
    private String traceId;

    public static <T> BaseResponseDTO<T> success(T datos) {
        return BaseResponseDTO.<T>builder()
                .exito(true)
                .codigo("200")
                .mensaje("Operación exitosa")
                .datos(datos)
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static <T> BaseResponseDTO<T> error(String codigo, String mensaje, List<String> errores) {
        return BaseResponseDTO.<T>builder()
                .exito(false)
                .codigo(codigo)
                .mensaje(mensaje)
                .errores(errores)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
