package frontend;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.Window;

public class UserController {

    @FXML
    private Button inscribe;

    @FXML
    private Button cancel;

    @FXML
    public void inscribe(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Info");
                alert.setContentText("You have been inscribed successfull");
                alert.showAndWait();
    }
    @FXML
    public void cancel(ActionEvent event){
        loadStage("login.fxml", event);
    }

    @FXML
    private void loadStage(String url, Event event){
        try {
            Window window = ((javafx.scene.Node)(event.getSource())).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource(url));
            Scene scene = new Scene(root);
            Stage newStage = (Stage) window;
            newStage.setScene(scene);
            newStage.show();
            
        } catch (Exception e) {
            new Exception("Error al cambiar de escena");
        }
    }    
}
