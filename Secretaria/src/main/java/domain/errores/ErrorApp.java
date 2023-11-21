package domain.errores;



import lombok.Data;

import java.time.LocalDateTime;

@Data
public  abstract sealed class ErrorApp permits ErrorNoConection,ErrorIntegrityConstraint{

    private final String mensaje;
    private final LocalDateTime fecha;

    protected ErrorApp(String mensaje) {
        this.mensaje = mensaje;
        this.fecha = LocalDateTime.now();
    }

    protected ErrorApp(String mensaje, LocalDateTime fecha) {
        this.mensaje = mensaje;
        this.fecha = fecha;
    }



}
