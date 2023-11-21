package ui.pantallas.listado;


import domain.modelo.Profesor;
import jakarta.inject.Inject;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.pantallas.common.BasePantallaController;

public class ProfesoresController extends BasePantallaController {


    private final ProfesoresViewModel viewModel;
    @FXML
    private TextField txtDni;

    @FXML
    private TextField txtNombre;

    @FXML
    private TableColumn<Profesor, String> colNombre;
    @FXML
    private TableColumn<Profesor, String> colDni;


    @FXML
    private TableView<Profesor> tablaProfesores;


    @Inject
    public ProfesoresController(ProfesoresViewModel viewModel) {
        this.viewModel = viewModel;


    }




    public void initialize() {
        cambiosEstado();
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDni.setCellValueFactory(new PropertyValueFactory<>("dni"));
    }


    @Override
    public void principalCargado() {
        viewModel.loadProfesores();

    }

    private void cambiosEstado() {
        viewModel.getState().addListener((observableValue, listadoState, listadoStateNew) -> {
            Platform.runLater(() -> {
                if (listadoStateNew != null) {

                    if (listadoStateNew.getError() != null)
                    {
                        getPrincipalController().sacarAlertError(listadoStateNew.getError());
                    }
                    if (listadoStateNew.getProfesores() != null) {

                        tablaProfesores.getItems().clear();
                        tablaProfesores.getItems().addAll(listadoStateNew.getProfesores());
                    }
                    if (listadoStateNew.isCargando()) {
                        getPrincipalController().root.setCursor(Cursor.WAIT);
                    }
                    else {
                        getPrincipalController().root.setCursor(Cursor.DEFAULT);
                    }

                }
            });

        });
    }

    @FXML
    private void addProfesor(ActionEvent actionEvent) {
        Profesor profesor = new Profesor(txtDni.getText(),txtNombre.getText());
        viewModel.addProfesor(profesor);

    }
}
