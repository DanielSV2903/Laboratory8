package controller.elementaryController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import util.Utility;

import java.awt.event.ActionEvent;

public class BubbleSortController
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
    private TableView<ObservableList<Integer>> sortedArrayTableView;
    @javafx.fxml.FXML
    private TableView<ObservableList<Integer>> noSortedArrayTableView;
    private int[] originalArray;


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

        int length = Integer.parseInt(arrayLengthTextField.getText().trim());
        int low = Integer.parseInt(lowBoundTextField.getText().trim());
        int high = Integer.parseInt(highBoundTextFIeld.getText().trim());

        originalArray = new int[length];
        for (int i = 0; i < length; i++) {
            originalArray[i] = low + (int)(Math.random() * (high - low + 1));
        }

        updateTableView(noSortedArrayTableView, length);
        noSortedArrayTableView.getItems().clear();
        noSortedArrayTableView.getItems().add(toObservableRow(originalArray));

        mostrarAlerta("Arreglo generado correctamente.");

    }

    private void updateTableView(TableView<ObservableList<Integer>> tableView, int length) {
        tableView.getColumns().clear();
        for (int i = 0; i < length; i++) {
            int colIndex = i;
            TableColumn<ObservableList<Integer>, Integer> col = new TableColumn<>("[" + colIndex + "]");
            col.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().get(colIndex)));
            tableView.getColumns().add(col);
        }
    }

    private ObservableList<Integer> toObservableRow(int[] array) {
        ObservableList<Integer> row = FXCollections.observableArrayList();
        for (int val : array) {
            row.add(val);
        }
        return row;
    }

    private static void mostrarAlerta(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Importante");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    public void randomizeOnAction(javafx.event.ActionEvent actionEvent) {
    }

    public void startOnAction(javafx.event.ActionEvent actionEvent) {
        if (originalArray == null || originalArray.length == 0) {
            mostrarAlerta("Primero debes crear un arreglo.");
            return;
        }

        int[] array = Utility.copyArray(originalArray);
        int changes = 0;
        int iterations = 0;

        for (int i = 0; i < array.length - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < array.length - 1 - i; j++) {
                iterations++;
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    changes++;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }

        updateTableView(sortedArrayTableView, array.length);
        sortedArrayTableView.getItems().clear();
        sortedArrayTableView.getItems().add(toObservableRow(array));

        changesTextField.setText(String.valueOf(changes));
        iterationsTextField.setText(String.valueOf(iterations));
    }

    public void clearOnAction(javafx.event.ActionEvent actionEvent) {
        arrayLengthTextField.clear();
        lowBoundTextField.clear();
        highBoundTextFIeld.clear();
        changesTextField.clear();
        iterationsTextField.clear();
        noSortedArrayTableView.getItems().clear();
        noSortedArrayTableView.getColumns().clear();
        sortedArrayTableView.getItems().clear();
        sortedArrayTableView.getColumns().clear();
        originalArray = null;
    }
}