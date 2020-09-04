package io.botcrafting.botcraft.infra.telegram.inbound;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TelegramMessage {
    private @JsonProperty("from") TelegramUser receivedUser;
    private @JsonProperty("chat") TelegramChat receivedChat;
    private @JsonProperty("messageId") int messageId;
    private @JsonProperty("date") int date;
    private @JsonProperty("text") String text;
}
