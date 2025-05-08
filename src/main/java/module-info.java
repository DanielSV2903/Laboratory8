module ucr.laboratory8 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens ucr.laboratory8 to javafx.fxml;
    exports ucr.laboratory8;
    exports controller;
    opens controller to javafx.fxml;
    opens domain to javafx.fxml;
    exports domain;
}