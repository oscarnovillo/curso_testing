package ui.pantallas.listado;


import jakarta.inject.Inject;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import ui.pantallas.common.BasePantallaController;

public class ListadoController extends BasePantallaController {


    private final ListadoViewModel viewModel;

//    @FXML
//    private TableColumn<Cromo, String> colCol;
//    @FXML
//    private TableColumn<Cromo, String> colDes;
//    @FXML
//    private TableColumn<Cromo, Integer> colNum;
//
//    @FXML
//    private TableView<Cromo> tablaNormal;


    @Inject
    public ListadoController(ListadoViewModel viewModel) {
        this.viewModel = viewModel;


    }

    @FXML
    private void ver(ActionEvent actionEvent) {
//        asyncConCompletableFuture();
////        //getPrincipalController().sacarAlertError("ahora con single");
//        //      asynConSingle();
//
//        getPrincipalController().root.setCursor(Cursor.WAIT);
////        viewModel.llamadaRetrofitAsyncEnViewModel();

    }


    public void initialize() {
// tabla materialFX

//        colCol.setCellValueFactory(new PropertyValueFactory<>("dni"));
//        colDes.setCellValueFactory(new PropertyValueFactory<>("nombre"));

//
//        tablaNormal.getSelectionModel().getSelectedItems().addListener((ListChangeListener<? super Cromo>) change -> {
//            txtNombre.setText("" + change.getList().get(0).getNumero());
//        });
//        tabla.getSelectionModel().selectionProperty().addListener((observableValue, cromo, cromoNew) -> {
//            if (cromoNew != null) {
//                txtNombre.setText(cromoNew.values().stream().findFirst().get().getCollecion());
//            }
//        });
//
//        tabla.getTableColumns().addAll(collecionColumn, descriptionColumn, numberColumn);

        cambiosEstado();

        // tabla.setItems(viewModel.getState().get().getPersonas());
    }


    @Override
    public void principalCargado() {
        viewModel.loadCromos();
    }

    private void cambiosEstado() {
        viewModel.getState().addListener((observableValue, listadoState, listadoStateNew) -> {
            Platform.runLater(() -> {
                if (listadoStateNew != null && listadoStateNew.getError() != null) {
                    getPrincipalController().sacarAlertError(listadoStateNew.getError());
                }
//                if (listadoStateNew.getCromos() != null) {
//
////                    tabla.getItems().clear();
////                    tabla.getItems().addAll(listadoStateNew.getCromos());
////                    tablaNormal.getItems().clear();
////                    tablaNormal.getItems().addAll(listadoStateNew.getCromos());
//                }


                getPrincipalController().root.setCursor(Cursor.DEFAULT);
            });

        });
    }


}
