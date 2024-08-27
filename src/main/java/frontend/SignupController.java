package frontend;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

public class SignUpController {
    @FXML
    private TextField id;
    @FXML
    private TextField name;
    @FXML
    private TextField email;
    @FXML
    private TextField role;
    @FXML
    private Button back;
    @FXML
    private Button signUp;
















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
        loadStage("admin.fxml", event);
    }
}
