package io.botcrafting.botcraft.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import static io.botcrafting.botcraft.configuration.constant.ValueConstant.VALUE_PARSE_MODE_MARKDOWN;

public class TelegramMessagePhotoRequest {
    private @JsonProperty("chat_id") final long chatId;
    private @JsonProperty("photo") final String photo;
    private @JsonProperty("caption") final String caption;
    private @JsonProperty("parse_mode") final String parseMode;

    public TelegramMessagePhotoRequest(long chatId, String photo, String caption) {
        this.chatId = chatId;
        this.photo = photo;
        this.caption = caption;
        this.parseMode = VALUE_PARSE_MODE_MARKDOWN;
    }
}
