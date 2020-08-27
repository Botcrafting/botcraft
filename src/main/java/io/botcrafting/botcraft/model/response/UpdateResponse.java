package io.botcrafting.botcraft.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateResponse {
    private @JsonProperty("updateId") int updateId;
    private @JsonProperty("message") MessageResponse messageResponse;

    public UpdateResponse(){}

    public int getUpdateId() {
        return updateId;
    }

    public MessageResponse getMessage() {
        return messageResponse;
    }
}
