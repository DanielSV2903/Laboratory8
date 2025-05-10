package controller.elementaryController;

import domain.Elementary;
import javafx.beans.Observable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import util.Utility;

import java.util.Arrays;

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

    private int[] noSortedArray;
    private int[] sortedArray;

    @javafx.fxml.FXML
    public void initialize() {

    }

    @javafx.fxml.FXML
    public void startOnAction(ActionEvent actionEvent) {
        crearTV(sortedArrayTableView, sortedArray.length);
        updateTV(sortedArrayTableView, sortedArray);

        minValueTextField.setText(Arrays.toString(Elementary.getMinValueArray()));
        maxValueTextField.setText(Arrays.toString(Elementary.getMinIndexArray()));
        iterationTextField.setText(Elementary.getTotalIterations()+"");
        changesTextField.setText(Elementary.getTotalChanges()+"");
    }

    @javafx.fxml.FXML
    public void createOnAction(ActionEvent actionEvent) {
        if (!Utility.validarEntradasArray(arrayLengthTextField, lowBoundTextField, highBoundTextField))
            return;
        noSortedArrayTableVIew.getItems().clear();
        noSortedArrayTableVIew.getColumns().clear();
        sortedArrayTableView.getItems().clear();
        sortedArrayTableView.getColumns().clear();
        iterationTextField.clear();
        changesTextField.clear();
        arrayLengthTextField.clear();
        maxValueTextField.clear();
        minValueTextField.clear();

        int lengthText = Integer.parseInt(arrayLengthTextField.getText());
        int lowBoundText = Integer.parseInt(lowBoundTextField.getText());
        int highBoundText = Integer.parseInt(highBoundTextField.getText());

        noSortedArray = Utility.createArray(lengthText,lowBoundText,highBoundText);
        sortedArray = Utility.copyArray(noSortedArray);

        crearTV(noSortedArrayTableVIew, noSortedArray.length);
        updateTV(noSortedArrayTableVIew, noSortedArray);

        mostrarAlerta("Arreglo ingresado","Valores ingresados correctamente");
    }
    private static void mostrarAlerta(String Titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(Titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    @javafx.fxml.FXML
    public void clearOnAction(ActionEvent actionEvent) {
        noSortedArrayTableVIew.getItems().clear();
        noSortedArrayTableVIew.getColumns().clear();
        sortedArrayTableView.getItems().clear();
        sortedArrayTableView.getColumns().clear();
        minValueTextField.clear();
        iterationTextField.clear();
        changesTextField.clear();
        arrayLengthTextField.clear();
        maxValueTextField.clear();
        lowBoundTextField.clear();
        highBoundTextField.clear();
    }

    @javafx.fxml.FXML
    public void randomizeOnAction(ActionEvent actionEvent) {
        int arrayLength = Utility.random(1, 200);
        int lowBound = Utility.random(0, 50);
        int highBound = Utility.random(lowBound+1, 100);

        noSortedArrayTableVIew.getItems().clear();
        noSortedArrayTableVIew.getColumns().clear();
        sortedArrayTableView.getItems().clear();
        sortedArrayTableView.getColumns().clear();
        iterationTextField.clear();
        changesTextField.clear();
        arrayLengthTextField.clear();
        maxValueTextField.clear();
        minValueTextField.clear();

        noSortedArray = Utility.createArray(arrayLength,lowBound,highBound);
        sortedArray = Utility.copyArray(noSortedArray);

        crearTV(noSortedArrayTableVIew, noSortedArray.length);
        updateTV(noSortedArrayTableVIew, noSortedArray);

        Elementary.selectionSort(sortedArray);
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