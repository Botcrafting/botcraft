package io.botcrafting.botcraft.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TelegramMessageVideoRequest {
    private @JsonProperty("chat_id") final long chatId;
    private @JsonProperty("video") final String video;

    public TelegramMessageVideoRequest(long chatId, String video) {
        this.chatId = chatId;
        this.video = video;
    }
}
