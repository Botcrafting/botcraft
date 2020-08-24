package io.botcrafting.botcraft.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TelegramMessageTextRequest {
    private @JsonProperty("chat_id") long chatId;
    private @JsonProperty("text") String text;

    public TelegramMessageTextRequest() {
    }

    public TelegramMessageTextRequest(long chatId, String text) {
        this.chatId = chatId;
        this.text = text;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
