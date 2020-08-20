package io.botcrafting.botcraft.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChatResponse {
    private long id;
    private @JsonProperty("first_name") String firstName;
    private @JsonProperty("last_name") String lastName;
    private @JsonProperty("username") String userName;
    private String title;
    private String type;
    private Boolean allMembersAreAdministrators;

    public ChatResponse(){}

    public long getId() {
        return id;
    }

    public static final class Builder{
        private int id;
        private String firstName;
        private String lastName;
        private String title;
        private String type;
        private Boolean allMembersAreAdministrators;

        public Builder withId(int id){
            this.id = id;
            return this;
        }

        public Builder withTitle(String title){
            this.title = title;
            return this;
        }

        public Builder withType(String type){
            this.type = type;
            return this;
        }

        public Builder withAllMembersAreAdministrators(Boolean allMembersAreAdministrators){
            this.allMembersAreAdministrators = allMembersAreAdministrators;
            return this;
        }

        public ChatResponse build(){
            ChatResponse chatResponse = new ChatResponse();
            chatResponse.id = this.id;
            chatResponse.firstName = this.firstName;
            chatResponse.lastName = this.lastName;
            chatResponse.title = this.title;
            chatResponse.type = this.type;
            chatResponse.allMembersAreAdministrators = this.allMembersAreAdministrators;

            return chatResponse;
        }
    }
}
