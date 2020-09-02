package io.botcrafting.botcraft.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessageResponse {
    private @JsonProperty("from") UserResponse userResponse;
    private @JsonProperty("chat") ChatResponse chatResponse;
    private @JsonProperty("messageId") int messageId;
    private @JsonProperty("date") int date;
    private @JsonProperty("text") String text;
}
