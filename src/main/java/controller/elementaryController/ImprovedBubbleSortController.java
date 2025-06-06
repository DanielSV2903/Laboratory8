package controller.elementaryController;

import domain.Elementary;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import util.Utility;

import javafx.event.ActionEvent;

public class ImprovedBubbleSortController
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
    @FXML
    private TableView SortedTableView;
    @FXML
    private TableView noSortedTableView;

    private int[] noSortedArray;
    private int[] sortedArray;
    @FXML
    private BorderPane bp;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @FXML
    public void startOnAction(ActionEvent actionEvent) {
        if (noSortedArray == null || noSortedArray.length == 0) {
            mostrarAlerta("Primero debes crear un arreglo.");
            return;
        }

        Elementary.improvedBubbleSort(sortedArray);

        crearTV(SortedTableView, sortedArray.length);
        updateTV(SortedTableView, sortedArray);

        iterationsTextField.setText(Elementary.getTotalIterations()+"");
        changesTextField.setText(Elementary.getTotalChanges()+"");
    }

    @FXML
    public void createButtonOnAction(javafx.event.ActionEvent actionEvent) {
        if (!Utility.validarEntradasArray(arrayLengthTextField, lowBoundTextField, highBoundTextFIeld))
            return;

        noSortedTableView.getItems().clear();
        noSortedTableView.getColumns().clear();
        SortedTableView.getItems().clear();
        SortedTableView.getColumns().clear();
        iterationsTextField.clear();
        changesTextField.clear();

        int lengthText = Integer.parseInt(arrayLengthTextField.getText());
        int lowBoundText = Integer.parseInt(lowBoundTextField.getText());
        int highBoundText = Integer.parseInt(highBoundTextFIeld.getText());

        noSortedArray = Utility.createArray(lengthText,lowBoundText,highBoundText);
        sortedArray = Utility.copyArray(noSortedArray);

        crearTV(noSortedTableView, noSortedArray.length);
        updateTV(noSortedTableView, noSortedArray);

        mostrarAlerta("Arreglo generado correctamente.");
    }

    @FXML
    public void clearOnAction(ActionEvent actionEvent) {
        arrayLengthTextField.clear();
        lowBoundTextField.clear();
        highBoundTextFIeld.clear();
        changesTextField.clear();
        iterationsTextField.clear();
        noSortedTableView.getItems().clear();
        noSortedTableView.getColumns().clear();
        SortedTableView.getItems().clear();
        SortedTableView.getColumns().clear();
    }

    @FXML
    public void randomizeOnAction(ActionEvent actionEvent) {
        int lengthText = Utility.random(1, 200);
        int lowBoundText = Utility.random(0, 25);
        int highBoundText = Utility.random(lowBoundText+1, 200);

        noSortedTableView.getItems().clear();
        noSortedTableView.getColumns().clear();
        SortedTableView.getItems().clear();
        SortedTableView.getColumns().clear();
        iterationsTextField.clear();
        changesTextField.clear();

        noSortedArray = Utility.createArray(lengthText,lowBoundText,highBoundText);
        sortedArray = Utility.copyArray(noSortedArray);

        crearTV(noSortedTableView, noSortedArray.length);
        updateTV(noSortedTableView, noSortedArray);
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

    private static void mostrarAlerta(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Error de validación");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}