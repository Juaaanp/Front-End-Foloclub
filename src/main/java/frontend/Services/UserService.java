package frontend.Services;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

import org.json.JSONObject;

import frontend.JsonReader;
import frontend.models.User;

public class UserService {
    private JSONObject jsonObject;

    public List <User> fetchData() throws IOException, URISyntaxException {
        String urlString = "http://localhost:8080/api/v1/users";
        URI uri = new URI(urlString);
        URL url = uri.toURL();
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            throw new RuntimeException("HttpResponseCode: " + responseCode);
        } else {
            StringBuilder response = new StringBuilder();
            Scanner scanner = new Scanner(url.openStream());
            while (scanner.hasNext()) {
                response.append(scanner.nextLine());
            }
            scanner.close();
            List<User> users = JsonReader.convertJsonToUsers(response.toString());

            for (User user : users) {
                System.out.println("Id: " + user.getId());
                System.out.println("Name: " + user.getName());
                System.out.println("Email: " + user.getEmail());
                System.out.println("Role: " + user.getRole());
                System.out.println("---");
            }
            return users;
        }
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }
}
