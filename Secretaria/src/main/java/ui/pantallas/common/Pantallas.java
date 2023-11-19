package ui.pantallas.common;

import lombok.Getter;

@Getter
public enum Pantallas {


    LISTADO (ConstantesPantallas.FXML_PANTALLA_LISTADO);
    private final String ruta;
    Pantallas(String ruta) {
        this.ruta=ruta;
    }

}
