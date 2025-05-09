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
    private Alert alert;

    @javafx.fxml.FXML
    public void initialize() {
        alert = new Alert(Alert.AlertType.INFORMATION);
    }

    @javafx.fxml.FXML
    public void startOnAction(ActionEvent actionEvent) {
        Elementary.countingSort(sortedArray);
        int[] contador = Elementary.getCounterArray();

        crearTV(noSortedArrayTableView, noSortedArray.length);
        crearTV(sortedArrayTableView, sortedArray.length);
        crearTV(counterArrayTableView, contador.length);

        updateTV(noSortedArrayTableView, noSortedArray);
        updateTV(sortedArrayTableView, sortedArray);
        updateTV(counterArrayTableView, contador);
    }

    @javafx.fxml.FXML
    public void clearOnAction(ActionEvent actionEvent) {
        this.sortedArray=null;
        this.noSortedArray=null;
        this.lowBoundTextField.clear();
        this.highBoundTextField.clear();
        this.arrayLengthTextFIeld.clear();
        clearTVs();

    }

    private void clearTVs() {
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

    @javafx.fxml.FXML
    public void createButtonOnAction(ActionEvent actionEvent) {
        if (!Utility.validarEntradasArray(arrayLengthTextFIeld, lowBoundTextField, highBoundTextField))
            mostrarAlerta("Error al ingresar los datos");
        else{
            if (!notEmpty()){
        int lengthText = Integer.parseInt(arrayLengthTextFIeld.getText());
        int lowBoundText = Integer.parseInt(lowBoundTextField.getText());
        int highBoundText = Integer.parseInt(highBoundTextField.getText());

       noSortedArray= Utility.createArray(lengthText,lowBoundText,highBoundText);
        sortedArray=Utility.copyArray(noSortedArray);
        crearTV(noSortedArrayTableView, lengthText);
        updateTV(noSortedArrayTableView,noSortedArray);
            }else mostrarAlerta("Ingrese todos los valores");
        }
    }

    private boolean notEmpty() {
        return this.arrayLengthTextFIeld.getText().isEmpty() && this.lowBoundTextField.getText().isEmpty() && this.highBoundTextField.getText().isEmpty();
    }

    private void updateTV(TableView<ObservableList<SimpleIntegerProperty>> tableView, int[] arreglo) {
        tableView.getItems().clear();

        ObservableList<SimpleIntegerProperty> fila = javafx.collections.FXCollections.observableArrayList();
        for (int value : arreglo) {
            fila.add(new SimpleIntegerProperty(value));
        }

        tableView.getItems().add(fila);
    }

    private void mostrarAlerta(String mensaje) {
        alert.setTitle("Error de validación");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
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