package ui.pantallas.principal;


import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import lombok.extern.log4j.Log4j2;
import ui.pantallas.common.BasePantallaController;
import ui.pantallas.common.Pantallas;

import java.io.IOException;

@Log4j2
public class PrincipalController {

    private final Alert alert;
    @FXML
    public BorderPane root;
    // objeto especial para DI
    Instance<Object> instance;
    @FXML
    private Menu menuHelp;
    @FXML
    private MenuBar menuPrincipal;
    private Stage primaryStage;
    private Pane pantallaBienvenida;


    @Inject
    public PrincipalController(Instance<Object> instance) {
        this.instance = instance;
        alert = new Alert(Alert.AlertType.NONE);


    }
    public void setStage(Stage stage) {
        primaryStage = stage;

    }
    private void cargarPantalla(Pantallas pantalla) {
        cambioPantalla(cargarPantalla(pantalla.getRuta()));
    }


    public void sacarAlertError(String mensaje) {
        alert.setAlertType(Alert.AlertType.ERROR);
        alert.setContentText(mensaje);
        alert.getDialogPane().setId("alert");
        alert.getDialogPane().lookupButton(ButtonType.OK).setId("btn-ok");
        //alert.getDialogPane().lookupButton(ButtonType.CANCEL).setId("btn-cancel");
        alert.showAndWait();
    }


    private Pane cargarPantalla(String ruta) {
        Pane panePantalla = null;
        try {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(controller -> instance.select(controller).get());
            panePantalla = fxmlLoader.load(getClass().getResourceAsStream(ruta));
            BasePantallaController pantallaController = fxmlLoader.getController();
            pantallaController.setPrincipalController(this);
            pantallaController.principalCargado();


        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return panePantalla;
    }


    private void cambioPantalla(Pane pantallaNueva) {

        root.setCenter(pantallaNueva);
    }


    public void initialize() {
        menuPrincipal.setVisible(true);
        cargarPantalla(Pantallas.LISTADO);

    }


    public void help(ActionEvent actionEvent) {
        Alert alertHelp = new Alert(Alert.AlertType.INFORMATION);
        alertHelp.setTitle("Ayuda");
        alertHelp.setHeaderText("Ayuda");
        alertHelp.setContentText("Este es un mensaje de ayuda");
        alertHelp.showAndWait();
    }


    @FXML
    private void menuClick(ActionEvent actionEvent) {
        switch (((MenuItem) actionEvent.getSource()).getId()) {
            case "menuItemListado":
                cargarPantalla(Pantallas.LISTADO);
                break;

        }


    }


}
