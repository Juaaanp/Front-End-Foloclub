package frontend;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.control.Alert.AlertType;

public class LoginController {
    @FXML
    private Button logIn;
    @FXML
    private TextField idTF;
    @FXML
    private Hyperlink signUp;

    @FXML
    public void eventOnAction(@SuppressWarnings("exports") ActionEvent event){
        Object evt = event.getSource();
        Alert alert = new Alert(AlertType.ERROR);

        if (evt.equals(logIn)) {
            if (!idTF.getText().isEmpty()) {
                
            }
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
    private void signUpOnAction(Event event){
        if(event.getSource().equals(signUp)){
            loadStage("signUp.fxml", event);
        }
    }

}
