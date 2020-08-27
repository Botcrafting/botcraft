package io.botcrafting.botcraft.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageResponse {
    private @JsonProperty("from") UserResponse userResponse;
    private @JsonProperty("chat") ChatResponse chatResponse;
    private @JsonProperty("messageId") int messageId;
    private @JsonProperty("date") int date;
    private @JsonProperty("text") String text;

    public MessageResponse(){}

    public String getText(){
        return this.text;
    }

    public int getMessageId() {
        return messageId;
    }

    public UserResponse getUser() {
        return userResponse;
    }

    public ChatResponse getChat() {
        return chatResponse;
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
}
