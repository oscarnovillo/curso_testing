package ui.main;

import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;
import ui.pantallas.principal.PrincipalController;

import java.io.IOException;

@Log4j2
public class MainFX {


    FXMLLoader fxmlLoader;
    @Inject
    public MainFX(FXMLLoader fxmlLoader) {
        this.fxmlLoader = fxmlLoader;
    }

    public void start(@Observes @StartupScene Stage stage) throws IOException {
        try {

            Parent fxmlParent = fxmlLoader.load(getClass().getResourceAsStream("/fxml/principal.fxml"));
            PrincipalController controller = fxmlLoader.getController();
            controller.setStage(stage);

            stage.setScene(new Scene(fxmlParent));

            stage.show();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            System.exit(0);
        }
    }

}
