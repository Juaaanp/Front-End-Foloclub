package frontend;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import frontend.Services.UserService;
import frontend.models.User;
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

public class LoginController {
    @FXML
    private Button logIn;
    @FXML
    private TextField idTF;
    @FXML
    private Hyperlink signUp;

    UserService userService = new UserService();

    @FXML
    public void eventOnAction(@SuppressWarnings("exports") Event event) throws IOException, URISyntaxException {
        List<User> userList = userService.fetchData();
        Object evt = event.getSource();
        Alert alert = new Alert(Alert.AlertType.ERROR);

        if (evt.equals(logIn)) {
        }
        if (!idTF.getText().isEmpty()) {
            if (idTF.getText().equals("12345")) {
                loadStage("admin.fxml", event);
            } else {
                for (User user : userList) {
                    if (user.getId().equals(idTF.getText())) {
                        if (user.getRole().equals("ADULT_MEMBER")) {
                            loadStage("user.fxml", event);
                        }
                    } else {
                        alert.setContentText("ERROR, the id provided is unknown!");
                        alert.show();
                    }
                }

            }

        } else {
            alert.setContentText("id field void, type your id");
            alert.show();
        }
    }

    @FXML
    private void loadStage(String url, Event event) {
        try {
            Window window = ((javafx.scene.Node) (event.getSource())).getScene().getWindow();
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
    private void signUpOnAction(Event event) {
        if (event.getSource().equals(signUp)) {
            loadStage("signUp.fxml", event);
        }
    }

}
