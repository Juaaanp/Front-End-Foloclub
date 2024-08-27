package frontend;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.Window;

public class AdminController {
    
    @FXML
    private Button sports;
    @FXML
    private Button trainingSessions;
    @FXML
    private Button trainers;
    @FXML
    private Button back;

    @FXML
    private void eventOnAction(ActionEvent event) throws IOException{
        Object obj = event.getSource();
        if (obj.equals(sports)) {
            loadStage("sports.fxml", event);
        }
        else if (obj.equals(trainingSessions)) {
            loadStage("trainingSessions.fxml", event);
        }
        else if (obj.equals(trainers)) {
            loadStage("trainers.fxml", event);
        }
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

     @FXML
    public void backOnAction(@SuppressWarnings("exports") ActionEvent event) throws IOException{
        loadStage("login.fxml", event);
    }
}
