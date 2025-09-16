package es.valencia.ciudadania360.common.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO base para todas las requests del sistema
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseRequestDTO {

    private String usuario;
    private String sessionId;
    private LocalDateTime timestamp;
    private String traceId;
    private String version;
}
