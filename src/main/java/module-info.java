module frontend {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;

    opens frontend to javafx.fxml;
    exports frontend;
}
