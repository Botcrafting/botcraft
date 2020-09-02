package io.botcrafting.botcraft.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import static io.botcrafting.botcraft.configuration.constant.ValueConstant.VALUE_PARSE_MODE_MARKDOWN;

public class TelegramMessageTextRequest {
    private @JsonProperty("chat_id") final long chatId;
    private @JsonProperty("text") final String text;
    private @JsonProperty("parse_mode") final String parseMode;

    public TelegramMessageTextRequest(long chatId, String text) {
        this.chatId = chatId;
        this.text = text;
        this.parseMode = VALUE_PARSE_MODE_MARKDOWN;
    }
}
