package io.botcrafting.botcraft.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Chat {
    private long id;
    private @JsonProperty("first_name") String firstName;
    private @JsonProperty("last_name") String lastName;
    private @JsonProperty("username") String userName;
    private String title;
    private String type;
    private Boolean allMembersAreAdministrators;

    public Chat(){}

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

        public Chat build(){
            Chat chat = new Chat();
            chat.id = this.id;
            chat.firstName = this.firstName;
            chat.lastName = this.lastName;
            chat.title = this.title;
            chat.type = this.type;
            chat.allMembersAreAdministrators = this.allMembersAreAdministrators;

            return chat;
        }
    }
}
