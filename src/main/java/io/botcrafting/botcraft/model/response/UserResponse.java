package io.botcrafting.botcraft.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserResponse {
    private int id;
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

    public void setBot(Boolean bot) {
        isBot = bot;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public static final class Builder{
        private int id;
        private Boolean isBot;
        private String firstName;
        private String lastName;
        private String userName;
        private String languageCode;

        public Builder withId(int id){
            this.id = id;
            return this;
        }

        public Builder withIsBot(Boolean isBot){
            this.isBot = isBot;
            return this;
        }

        public Builder withFirstName(String firstName){
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName){
            this.lastName = lastName;
            return this;
        }

        public Builder withUsername(String userName){
            this.userName = userName;
            return this;
        }

        public Builder withLanguageCode(String languageCode){
            this.languageCode = languageCode;
            return this;
        }

        public UserResponse build(){
            UserResponse userResponse = new UserResponse();
            userResponse.id = this.id;
            userResponse.isBot = this.isBot;
            userResponse.firstName = this.firstName;
            userResponse.lastName = this.lastName;
            userResponse.userName = this.userName;
            userResponse.languageCode = this.languageCode;

            return userResponse;
        }
    }
}
