module frontend {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;

    opens frontend to javafx.fxml;
    exports frontend;
}
