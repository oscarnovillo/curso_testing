package common.config;


import common.Constantes;
import jakarta.inject.Singleton;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.Properties;

@Getter
@Log4j2
@Singleton
public class Configuracion {

    private String pathDatos;

    public Configuracion() {

        try {
            Properties p = new Properties();
            p.load(getClass().getClassLoader()
                    .getResourceAsStream(Constantes.PATH_TO_CONFIG_FILE));
            this.pathDatos = p.getProperty("pathDatos");

        } catch (IOException e) {
           log.error(e.getMessage(),e);
        }
    }


}
