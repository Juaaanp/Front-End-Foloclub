package frontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URISyntaxException;
import frontend.Services.UserService;

public class App extends Application {
    @Override
    public void start(@SuppressWarnings("exports") Stage stage) throws IOException, URISyntaxException {
        UserService userService = new UserService();
        userService.fetchData();
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}