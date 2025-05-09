package controller.elementaryController;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import util.Utility;

public class CountingSortingController
{
    @javafx.fxml.FXML
    private TextField arrayLengthTextFIeld;
    @javafx.fxml.FXML
    private TextField highBoundTextField;
    @javafx.fxml.FXML
    private TextField lowBoundTextField;
    @javafx.fxml.FXML
    private TableView sortedArrayTableView;
    @javafx.fxml.FXML
    private TableView noSortedArrayTableView;
    @javafx.fxml.FXML
    private TableView counterArrayTableView;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void startOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void clearOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void randomizeOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void createButtonOnAction(ActionEvent actionEvent) {
        if (!Utility.validarEntradasArray(arrayLengthTextFIeld, lowBoundTextField, highBoundTextField))
            return;

        mostrarAlerta("Valores ingresados correctamente");

    }
    private static void mostrarAlerta(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Error de validación");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}