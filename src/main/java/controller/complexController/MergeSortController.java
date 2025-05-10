package controller.complexController;

import domain.Complex;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import util.Utility;

import java.util.Arrays;

public class MergeSortController
{
    @javafx.fxml.FXML
    private TextField arrayLengthTextField;
    @javafx.fxml.FXML
    private TextField recursiveCallsTextField;
    @javafx.fxml.FXML
    private TableView sortedArrayTableView;
    @javafx.fxml.FXML
    private TableView tempArrayTableView;
    @javafx.fxml.FXML
    private TextField highBoundTextField;
    @javafx.fxml.FXML
    private TextField lowTextField;
    @javafx.fxml.FXML
    private TextField lowBoundTextField;
    @javafx.fxml.FXML
    private TextField highTextField;
    @javafx.fxml.FXML
    private TableView noSortedArrayTableView;

    private int[] sortedArray;
    private int[] noSortedArray;
    private int[] tempArray;
    @javafx.fxml.FXML
    private BorderPane bp;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void startOnAction(ActionEvent actionEvent) {
        crearTV(sortedArrayTableView, sortedArray.length);
        updateTV(sortedArrayTableView, sortedArray);

        crearTV(tempArrayTableView, tempArray.length);
        updateTV(tempArrayTableView, noSortedArray);

        lowTextField.setText(Arrays.toString(Complex.getLowArray()));
        highTextField.setText(Arrays.toString(Complex.getHighArray()));
        recursiveCallsTextField.setText(Complex.getRecursiveCount()+"");
    }

    @javafx.fxml.FXML
    public void createOnAction(ActionEvent actionEvent) {
        if (!Utility.validarEntradasArray(arrayLengthTextField, lowBoundTextField, highBoundTextField))
            return;

        noSortedArrayTableView.getItems().clear();
        noSortedArrayTableView.getColumns().clear();
        sortedArrayTableView.getItems().clear();
        sortedArrayTableView.getColumns().clear();
        lowTextField.clear();
        highTextField.clear();
        recursiveCallsTextField.clear();

        int lengthText = Integer.parseInt(arrayLengthTextField.getText());
        int lowBoundText = Integer.parseInt(lowBoundTextField.getText());
        int highBoundText = Integer.parseInt(highBoundTextField.getText());

        noSortedArray = Utility.createArray(lengthText,lowBoundText,highBoundText);
        sortedArray = Utility.copyArray(noSortedArray);
        tempArray = new int[noSortedArray.length];

        crearTV(noSortedArrayTableView, noSortedArray.length);
        updateTV(noSortedArrayTableView, noSortedArray);

        Complex.initArrays(noSortedArray.length);
        Complex.mergeSort(sortedArray, tempArray, 0, noSortedArray.length-1, 0);

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
        noSortedArrayTableView.getItems().clear();
        noSortedArrayTableView.getColumns().clear();
        sortedArrayTableView.getItems().clear();
        sortedArrayTableView.getColumns().clear();
        arrayLengthTextField.clear();
        recursiveCallsTextField.clear();
        lowTextField.clear();
        highTextField.clear();
        lowBoundTextField.clear();
        highBoundTextField.clear();
        tempArrayTableView.getItems().clear();
        tempArrayTableView.getColumns().clear();
    }

    @javafx.fxml.FXML
    public void randomizeOnAction(ActionEvent actionEvent) {
        int lengthText = Utility.random(1, 200);
        int lowBoundText = Utility.random(0, 50);
        int highBoundText = Utility.random(lowBoundText+1, 100);

        noSortedArrayTableView.getItems().clear();
        noSortedArrayTableView.getColumns().clear();
        sortedArrayTableView.getItems().clear();
        sortedArrayTableView.getColumns().clear();
        lowTextField.clear();
        highTextField.clear();
        recursiveCallsTextField.clear();

        noSortedArray = Utility.createArray(lengthText,lowBoundText,highBoundText);
        sortedArray = Utility.copyArray(noSortedArray);
        tempArray = new int[noSortedArray.length];

        crearTV(noSortedArrayTableView, noSortedArray.length);
        updateTV(noSortedArrayTableView, noSortedArray);

        Complex.initArrays(noSortedArray.length);
        Complex.mergeSort(sortedArray, tempArray, 0, noSortedArray.length-1, 0);
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