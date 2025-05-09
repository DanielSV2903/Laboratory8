package controller.elementaryController;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import util.Utility;

import java.awt.event.ActionEvent;

public class ImprovedBubbleSortController
{
    @javafx.fxml.FXML
    private TextField arrayLengthTextField;
    @javafx.fxml.FXML
    private TextField lowBoundTextField;
    @javafx.fxml.FXML
    private TextField highBoundTextFIeld;
    @javafx.fxml.FXML
    private TextField changesTextField;
    @javafx.fxml.FXML
    private TextField iterationsTextField;

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
    public void createButtonOnAction(javafx.event.ActionEvent actionEvent) {
        if (!Utility.validarEntradasArray(arrayLengthTextField, lowBoundTextField, highBoundTextFIeld))
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

    public void randomizeOnAction(javafx.event.ActionEvent actionEvent) {
    }

    public void startOnAction(javafx.event.ActionEvent actionEvent) {
    }

    public void clearOnAction(javafx.event.ActionEvent actionEvent) {
    }
}