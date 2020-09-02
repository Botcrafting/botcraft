package io.botcrafting.botcraft.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TelegramMessageVideoRequest {
    private @JsonProperty("chat_id") final long chatId;
    private @JsonProperty("video") final String video;
}
