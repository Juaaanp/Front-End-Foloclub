package frontend;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import frontend.models.Sport;
import frontend.models.Trainer;
import frontend.models.User;
public class JsonReader {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static List<User> convertJsonToUsers(String response) throws IOException {
        return objectMapper.readValue(response, new TypeReference<List<User>>() {});
    }

    public static User convertJsonToUser(String jsonString) throws IOException {
        return objectMapper.readValue(jsonString, User.class);
    }

    public static List<Sport> convertJsonToSports(String response) throws IOException {
        return objectMapper.readValue(response, new TypeReference<List<Sport>>() {});
    }
    public static List<Trainer> convertJsonToTrainers(String response) throws IOException {
        return objectMapper.readValue(response, new TypeReference<List<Trainer>>() {});
    }
}