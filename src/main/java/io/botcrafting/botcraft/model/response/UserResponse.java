package io.botcrafting.botcraft.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserResponse {
    private @JsonProperty("id") int id;
    private @JsonProperty("is_bot") Boolean isBot;
    private @JsonProperty("first_name") String firstName;
    private @JsonProperty("last_name") String lastName;
    private @JsonProperty("username") String userName;
    private @JsonProperty("language_code") String languageCode;

    public UserResponse(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getBot() {
        return isBot;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getLanguageCode() {
        return languageCode;
    }
}
