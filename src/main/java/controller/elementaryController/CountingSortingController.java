package controller.elementaryController;

import domain.Complex;
import domain.Elementary;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
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
    private TableView<ObservableList<SimpleIntegerProperty>> noSortedArrayTableView;
    @javafx.fxml.FXML
    private TableView counterArrayTableView;
    private int[] noSortedArray;
    private int[] sortedArray;
    @javafx.fxml.FXML
    private BorderPane bp;

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
            mostrarAlerta("Error al ingresar los datos");

        String lengthText = arrayLengthTextFIeld.getText();
        String lowBoundText = lowBoundTextField.getText();
        String highBoundText = highBoundTextField.getText();

       noSortedArray= Utility.createArray(lengthText,lowBoundText,highBoundText);
        sortedArray=Utility.copyArray(noSortedArray);

        crearTV(noSortedArrayTableView, Integer.parseInt(lengthText));
        updateTV(noSortedArrayTableView,noSortedArray);
        Elementary.countingSort(sortedArray);
        int[] contador = Elementary.getCounterArray(); // ejemplo

        crearTV(noSortedArrayTableView, noSortedArray.length);
        crearTV(sortedArrayTableView, sortedArray.length);
        crearTV(counterArrayTableView, contador.length);

        updateTV(noSortedArrayTableView, noSortedArray);
        updateTV(sortedArrayTableView, sortedArray);
        updateTV(counterArrayTableView, contador);

        mostrarAlerta("Valores ingresados correctamente");

    }

    private void updateTV(TableView<ObservableList<SimpleIntegerProperty>> tableView, int[] arreglo) {
        tableView.getItems().clear();

        ObservableList<SimpleIntegerProperty> fila = javafx.collections.FXCollections.observableArrayList();
        for (int value : arreglo) {
            fila.add(new SimpleIntegerProperty(value));
        }

        tableView.getItems().add(fila);
    }

    private static void mostrarAlerta(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Error de validación");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    private void crearTV(TableView<ObservableList<SimpleIntegerProperty>> tableView, int length) {
        tableView.getColumns().clear();

        for (int i = 0; i < length; i++) {
            final int colIndex = i;
            TableColumn<ObservableList<SimpleIntegerProperty>, Number> column =
                    new TableColumn<>("[" + i + "]");
            column.setCellValueFactory(data -> data.getValue().get(colIndex));
            tableView.getColumns().add(column);
        }
    }
}