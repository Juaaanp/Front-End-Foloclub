module frontend {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires org.json;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;

    opens frontend to javafx.fxml;
    exports frontend;
}
