package frontend.models;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.annotation.JsonAlias;

public class User{
    @JsonProperty("_id")
    @JsonAlias("id")
    public String id;
    
    public String name;
    public String email;
    public String role;
    
    @JsonProperty("_class")
    public String className;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getClassName() {
        return className;
    }

}