package frontend;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import frontend.models.LocalUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.Window;

public class SignUpController implements Initializable{
    @FXML
    private TextField idTF;
    @FXML
    private TextField nameTF;
    @FXML
    private TextField emailTF;
    @FXML
    private TextField roleTF;
    @FXML
    private Button back;
    @FXML
    private Button signUp;

    private ObservableList<LocalUser> localUsers;

    @FXML
    private void signUp(ActionEvent event){
        String name = nameTF.getText();
        String id = idTF.getText();
        String email = emailTF.getText();
        String role = emailTF.getText();

        LocalUser localUser = new LocalUser(id, name, email, role);
        localUsers.add(localUser);
        localUser.save(localUsers);
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Info");
        alert.setContentText("You have been registered succesfull");
        alert.showAndWait();

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        localUsers = FXCollections.observableArrayList();
    }
}
