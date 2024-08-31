package frontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

import org.json.JSONObject;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(@SuppressWarnings("exports") Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws URISyntaxException, IOException {
        try {
            String urlString = "http://localhost:8080/api/v1/users";
            URI uri = new URI(urlString);
            URL url = uri.toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            }
            else{
                StringBuilder response = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    response.append(scanner.nextLine());
                }
                scanner.close();
                List<User> users = JsonReader.convertJsonToUsers(response.toString());

 for (User user : users) {
                System.out.println("Name: " + user.getName());
                System.out.println("Email: " + user.getEmail());
                System.out.println("Role: " + user.getRole());
                System.out.println("---");
            }
                // JSONObject jsonObject = new JSONObject(response);
                System.out.println(response.toString());
            
            }

        } catch (URISyntaxException | MalformedURLException e) {
            e.printStackTrace();
        }
        launch();
    }

}