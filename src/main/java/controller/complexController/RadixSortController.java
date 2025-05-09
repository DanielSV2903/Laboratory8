package controller.complexController;

import domain.Complex;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import util.Utility;

public class RadixSortController
{
    @javafx.fxml.FXML
    private TextField arrayLengthTextField;
    @javafx.fxml.FXML
    private TableView<ObservableList<SimpleIntegerProperty>> sortedArrayTableView;
    @javafx.fxml.FXML
    private TextField highBoundTextField;
    @javafx.fxml.FXML
    private TableView<ObservableList<SimpleIntegerProperty>> noSortedArrayTableView;
    @javafx.fxml.FXML
    private TextField lowBoundTextField;
    @javafx.fxml.FXML
    private TableView<ObservableList<SimpleIntegerProperty>> counterArrayTableView;
    private Alert alert;
    private int[] noSortedArray;
    private int[] sortedArray;

    @javafx.fxml.FXML
    public void initialize() {
        alert = new Alert(Alert.AlertType.INFORMATION);
    }

    @javafx.fxml.FXML
    public void startOnAction(ActionEvent actionEvent) {
        Complex.radixSort(sortedArray,50);
        int[] copy=Utility.copyArray(sortedArray);
        crearTV(sortedArrayTableView,50);
        updateTV(sortedArrayTableView,sortedArray);
        int[] counter=Complex.getCounterRadix();
        crearTV(counterArrayTableView,counter.length);
        updateTV(counterArrayTableView,counter);
    }

    @javafx.fxml.FXML
    public void createOnAction(ActionEvent actionEvent) {
        if (!Utility.validarEntradasArray(arrayLengthTextField, lowBoundTextField, highBoundTextField))
            mostrarAlerta("Error al ingresar los valores");
        else{
            if(!notEmpty()){
                int length = Integer.parseInt(arrayLengthTextField.getText());
                int low = Integer.parseInt(lowBoundTextField.getText());
                int high = Integer.parseInt(highBoundTextField.getText());
                noSortedArray=Utility.createArray(length, low, high);
                sortedArray=Utility.copyArray(noSortedArray);

                crearTV(noSortedArrayTableView,length);
                updateTV(noSortedArrayTableView,noSortedArray);
        }
        }
    }

    private boolean notEmpty() {
        return this.arrayLengthTextField.getText().isEmpty()&&this.lowBoundTextField.getText().isEmpty()&&this.highBoundTextField.getText().isEmpty();
    }

    private void mostrarAlerta(String mensaje) {
        alert.setTitle("Error de validación");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @javafx.fxml.FXML
    public void clearOnAction(ActionEvent actionEvent) {
        noSortedArray=null;
        sortedArray=null;
        this.arrayLengthTextField.clear();
        this.lowBoundTextField.clear();
        this.highBoundTextField.clear();
        this.noSortedArrayTableView.getColumns().clear();
        this.sortedArrayTableView.getColumns().clear();
        this.counterArrayTableView.getColumns().clear();
    }

    @javafx.fxml.FXML
    public void randomizeOnAction(ActionEvent actionEvent) {
        this.sortedArrayTableView.getColumns().clear();
        this.counterArrayTableView.getColumns().clear();
        int lenght=Utility.random(200);
        int low=Utility.random(0,lenght);
        int high=Utility.random(low+1,lenght);
        noSortedArray=Utility.createArray(lenght,low,high);
        sortedArray=Utility.copyArray(noSortedArray);
        crearTV(noSortedArrayTableView, lenght);
        updateTV(noSortedArrayTableView,noSortedArray);
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
    private void updateTV(TableView<ObservableList<SimpleIntegerProperty>> tableView, int[] arreglo) {
        tableView.getItems().clear();
        ObservableList<SimpleIntegerProperty> fila = javafx.collections.FXCollections.observableArrayList();
        for (int value : arreglo) {
            fila.add(new SimpleIntegerProperty(value));
        }

        tableView.getItems().add(fila);
    }
}