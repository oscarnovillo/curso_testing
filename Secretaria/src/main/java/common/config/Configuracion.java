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

    private String ruta;
    private String user;
    private String password;
    private String driver;

    public Configuracion() {

        try {
            Properties p = new Properties();
            p.load(getClass().getClassLoader()
                    .getResourceAsStream(Constantes.PATH_TO_CONFIG_FILE));
            this.ruta = p.getProperty("ruta");
            this.user = p.getProperty("user");
            this.password = p.getProperty("password");
            this.driver = p.getProperty("driver");

        } catch (IOException e) {
           log.error(e.getMessage(),e);
        }
    }



}
