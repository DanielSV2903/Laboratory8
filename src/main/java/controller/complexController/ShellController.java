package controller.complexController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import util.Utility;

public class ShellController
{
    @javafx.fxml.FXML
    private TextField gapSubArray1TextField;
    @javafx.fxml.FXML
    private TextField arrayLengthTextField;
    @javafx.fxml.FXML
    private TableView sortedArrayTableVIew;
    @javafx.fxml.FXML
    private TextField gapSubArray3TextField;
    @javafx.fxml.FXML
    private TableView noSortedArrayTableVIew;
    @javafx.fxml.FXML
    private TextField lowBoundTextField;
    @javafx.fxml.FXML
    private TextField gapSubArray2TextField;
    @javafx.fxml.FXML
    private TextField gapNTextField;
    @FXML
    private TextField highBoundTextField;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void startOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void createOnAction(ActionEvent actionEvent) {
        if (!Utility.validarEntradasArray(arrayLengthTextField, lowBoundTextField, highBoundTextField))
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

    @javafx.fxml.FXML
    public void clearOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void randomizeOnAction(ActionEvent actionEvent) {
    }
}