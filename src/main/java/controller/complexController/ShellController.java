package controller.complexController;
import domain.Complex;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import util.Utility;

import java.util.ArrayList;
import java.util.List;

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

    @FXML
    private TextField highBoundTextField;
    private Alert alert ;
    private static List<int[]> gapsList;
    private static List<Integer> gapValues;
    private int[] nonSortedArray;
    private int[] sortedArray;
    @FXML
    private TextField gapsN2TF;

    @javafx.fxml.FXML
    public void initialize() {
        alert = new Alert(Alert.AlertType.INFORMATION);
        gapsList = Complex.getGapsList();
        gapValues = Complex.getGapValues();
    }

    @javafx.fxml.FXML
    public void startOnAction(ActionEvent actionEvent) {
        String gaps = "";
        for (Integer gapValue : gapValues) {
            gaps += (gapValue + ",");
        }
        Complex.shellSort(sortedArray);
        crearTV(sortedArrayTableVIew,sortedArray.length);
        updateTV(sortedArrayTableVIew,sortedArray);
        String gap1 =Utility.arrayToString(gapsList.get(0),gapsList.get(0).length);
        String gap2=Utility.arrayToString(gapsList.get(1),gapsList.get(1).length);
        String gap3=Utility.arrayToString(gapsList.get(2),gapsList.get(2).length);
        this.gapsN2TF.setText(gaps);
        this.gapSubArray1TextField.setText(gap1);
        this.gapSubArray2TextField.setText(gap2);
        this.gapSubArray3TextField.setText(gap3);
    }

    @javafx.fxml.FXML
    public void createOnAction(ActionEvent actionEvent) {
        if (!Utility.validarEntradasArray(arrayLengthTextField, lowBoundTextField, highBoundTextField))
            mostrarAlerta("Error al ingresar los datos");
        else {
            if (!notEmpty()){
                int length = Integer.parseInt(arrayLengthTextField.getText());
                int low = Integer.parseInt(lowBoundTextField.getText());
                int high = Integer.parseInt(highBoundTextField.getText());
                nonSortedArray = Utility.createArray(length,low,high);
                sortedArray = Utility.copyArray(nonSortedArray);

                crearTV(noSortedArrayTableVIew,length);
                updateTV(noSortedArrayTableVIew,nonSortedArray);
            }
        }



    }
    private void mostrarAlerta(String mensaje) {
        alert= new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error de validación");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @javafx.fxml.FXML
    public void clearOnAction(ActionEvent actionEvent) {
        nonSortedArray=null;
        sortedArray=null;
        this.arrayLengthTextField.clear();
        this.lowBoundTextField.clear();
        this.highBoundTextField.clear();
        this.noSortedArrayTableVIew.getColumns().clear();
        this.sortedArrayTableVIew.getColumns().clear();
        this.gapSubArray1TextField.clear();
        this.gapSubArray2TextField.clear();
        this.gapSubArray3TextField.clear();
        this.gapsN2TF.clear();
    }

    @javafx.fxml.FXML
    public void randomizeOnAction(ActionEvent actionEvent) {
        this.sortedArrayTableVIew.getColumns().clear();
        int lenght=Utility.random(150);
        int low=Utility.random(0,lenght);
        int high=Utility.random(low+1,lenght);
        nonSortedArray=Utility.createArray(lenght,low,high);
        sortedArray=Utility.copyArray(nonSortedArray);
        crearTV(noSortedArrayTableVIew, lenght);
        updateTV(noSortedArrayTableVIew,nonSortedArray);
    }
    private boolean notEmpty() {
        return this.arrayLengthTextField.getText().isEmpty()&&this.lowBoundTextField.getText().isEmpty()&&this.highBoundTextField.getText().isEmpty();
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