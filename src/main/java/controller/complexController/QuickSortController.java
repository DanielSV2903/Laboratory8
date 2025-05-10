package controller.complexController;

import domain.Complex;
import domain.Elementary;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import util.Utility;

import java.util.Arrays;

public class QuickSortController
{
    @javafx.fxml.FXML
    private TextField recursiveCallsTextFIeld;
    @javafx.fxml.FXML
    private TextField arrayLengthTextField;
    @javafx.fxml.FXML
    private TableView sortedArrayTableVIew;
    @javafx.fxml.FXML
    private TextField pivotTextField;
    @javafx.fxml.FXML
    private TableView noSortedArrayTableVIew;
    @javafx.fxml.FXML
    private TextField highBoundTextField;
    @javafx.fxml.FXML
    private TextField lowTextField;
    @javafx.fxml.FXML
    private TextField lowBoundTextField;
    @javafx.fxml.FXML
    private TextField highTextFIeld;

    private int[] sortedArray;
    private int[] noSortedArray;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void startOnAction(ActionEvent actionEvent) {
        crearTV(sortedArrayTableVIew, sortedArray.length);
        updateTV(sortedArrayTableVIew, sortedArray);

        pivotTextField.setText(Arrays.toString(Complex.getPivotArray()));
        lowTextField.setText(Arrays.toString(Complex.getLowArray()));
        highTextFIeld.setText(Arrays.toString(Complex.getHighArray()));
        recursiveCallsTextFIeld.setText(Complex.getRecursiveCount()+"");
    }

    @javafx.fxml.FXML
    public void createOnAction(ActionEvent actionEvent) {
        if (!Utility.validarEntradasArray(arrayLengthTextField, lowBoundTextField, highBoundTextField)) {
            return;
        }

        noSortedArrayTableVIew.getItems().clear();
        noSortedArrayTableVIew.getColumns().clear();
        sortedArrayTableVIew.getItems().clear();
        sortedArrayTableVIew.getColumns().clear();
        recursiveCallsTextFIeld.clear();
        pivotTextField.clear();
        lowTextField.clear();
        highTextFIeld.clear();

        int lengthText = Integer.parseInt(arrayLengthTextField.getText());
        int lowBoundText = Integer.parseInt(lowBoundTextField.getText());
        int highBoundText = Integer.parseInt(highBoundTextField.getText());

        noSortedArray = Utility.createArray(lengthText,lowBoundText,highBoundText);
        sortedArray = Utility.copyArray(noSortedArray);

        crearTV(noSortedArrayTableVIew, noSortedArray.length);
        updateTV(noSortedArrayTableVIew, noSortedArray);

        Complex.initArrays(noSortedArray.length);
        Complex.quickSort(sortedArray, 0, noSortedArray.length-1, 0, 0, 0);

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
        noSortedArrayTableVIew.getItems().clear();
        noSortedArrayTableVIew.getColumns().clear();
        sortedArrayTableVIew.getItems().clear();
        sortedArrayTableVIew.getColumns().clear();
        arrayLengthTextField.clear();
        lowTextField.clear();
        highTextFIeld.clear();
        lowBoundTextField.clear();
        recursiveCallsTextFIeld.clear();
        pivotTextField.clear();
        highBoundTextField.clear();
    }

    @javafx.fxml.FXML
    public void randomizeOnAction(ActionEvent actionEvent) {
        int lengthText = Utility.random(1, 200);
        int lowBoundText = Utility.random(0, 50);
        int highBoundText = Utility.random(lowBoundText+1, 100);

        noSortedArrayTableVIew.getItems().clear();
        noSortedArrayTableVIew.getColumns().clear();
        sortedArrayTableVIew.getItems().clear();
        sortedArrayTableVIew.getColumns().clear();
        recursiveCallsTextFIeld.clear();
        pivotTextField.clear();
        lowTextField.clear();
        highTextFIeld.clear();

        noSortedArray = Utility.createArray(lengthText,lowBoundText,highBoundText);
        sortedArray = Utility.copyArray(noSortedArray);

        crearTV(noSortedArrayTableVIew, noSortedArray.length);
        updateTV(noSortedArrayTableVIew, noSortedArray);

        Complex.initArrays(noSortedArray.length);
        Complex.quickSort(sortedArray, 0, noSortedArray.length-1, 0, 0, 0);
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