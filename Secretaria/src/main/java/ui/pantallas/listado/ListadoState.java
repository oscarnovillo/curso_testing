package ui.pantallas.listado;

import domain.modelo.Profesor;
import lombok.Data;

import java.util.List;

@Data
public class ListadoState {

    private final List<Profesor> profesores;
    private final String error;
    private final boolean cargando;
}
