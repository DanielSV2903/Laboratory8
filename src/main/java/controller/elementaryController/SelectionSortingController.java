package controller.elementaryController;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import util.Utility;

public class SelectionSortingController
{
    @javafx.fxml.FXML
    private TextField minValueTextField;
    @javafx.fxml.FXML
    private TextField iterationTextField;
    @javafx.fxml.FXML
    private TextField changesTextField;
    @javafx.fxml.FXML
    private TextField arrayLengthTextField;
    @javafx.fxml.FXML
    private TextField maxValueTextField;
    @javafx.fxml.FXML
    private TableView sortedArrayTableView;
    @javafx.fxml.FXML
    private TableView noSortedArrayTableVIew;
    @javafx.fxml.FXML
    private TextField highBoundTextField;
    @javafx.fxml.FXML
    private TextField lowBoundTextField;
    @javafx.fxml.FXML
    private BorderPane bp;
    @javafx.fxml.FXML
    private ScrollPane scrollPaneNSA;
    @javafx.fxml.FXML
    private ScrollPane scrollPaneSA;

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
        noSortedArrayTableVIew.getItems().clear();
        noSortedArrayTableVIew.getColumns().clear();

        for (int i = 0; i < Integer.parseInt(arrayLengthTextField.getText().trim()); i++) {
            noSortedArrayTableVIew.getColumns().add(new javafx.scene.control.TableColumn<>("[" + i + "]"));
            
        }

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