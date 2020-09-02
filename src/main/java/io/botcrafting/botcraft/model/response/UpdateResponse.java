package io.botcrafting.botcraft.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateResponse {
    private @JsonProperty("updateId") int updateId;
    private @JsonProperty("message") MessageResponse messageResponse;
}
