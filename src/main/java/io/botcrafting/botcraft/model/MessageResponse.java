package io.botcrafting.botcraft.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageResponse {
    private @JsonProperty("from") UserResponse userResponse;
    private @JsonProperty("chat") ChatResponse chatResponse;
    private int messageId;
    private int date;
    private String text;

    public MessageResponse(){}

    public String getText(){
        return this.text;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public UserResponse getUser() {
        return userResponse;
    }

    public void setUser(UserResponse userResponse) {
        this.userResponse = userResponse;
    }

    public ChatResponse getChat() {
        return chatResponse;
    }

    public void setChat(ChatResponse chatResponse) {
        this.chatResponse = chatResponse;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static final class Builder{
        private int messageId;
        private @JsonProperty("from")
        UserResponse userResponse;
        private ChatResponse chatResponse;
        private int date;
        private String text;

        public Builder withMessageId(int messageId){
            this.messageId = messageId;
            return this;
        }

        public Builder withUser(UserResponse userResponse){
            this.userResponse = userResponse;
            return this;
        }

        public Builder withChat(ChatResponse chatResponse){
            this.chatResponse = chatResponse;
            return this;
        }

        public Builder withDate(int date){
            this.date = date;
            return this;
        }

        public Builder withText(String text){
            this.text = text;
            return this;
        }

        public MessageResponse build(){
            MessageResponse messageResponse = new MessageResponse();
            messageResponse.messageId = this.messageId;
            messageResponse.userResponse = this.userResponse;
            messageResponse.chatResponse = this.chatResponse;
            messageResponse.date = this.date;
            messageResponse.text = this.text;
            return messageResponse;
        }
    }
}
