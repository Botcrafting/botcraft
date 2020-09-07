package io.botcrafting.botcraft.infra.telegram.outbound;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TelegramMessageVideo {
    private @JsonProperty("chat_id") final long chatId;
    private @JsonProperty("video") final String video;
}
