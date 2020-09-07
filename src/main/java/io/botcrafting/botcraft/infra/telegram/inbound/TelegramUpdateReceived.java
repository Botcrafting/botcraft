package io.botcrafting.botcraft.infra.telegram.inbound;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TelegramUpdateReceived {
    private @JsonProperty("updateId") int updateId;
    private @JsonProperty("message") TelegramMessage receivedMessage;
}
