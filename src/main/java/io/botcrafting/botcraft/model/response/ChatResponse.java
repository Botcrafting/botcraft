package io.botcrafting.botcraft.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChatResponse {
    private @JsonProperty("id") long id;
    private @JsonProperty("first_name") String firstName;
    private @JsonProperty("last_name") String lastName;
    private @JsonProperty("username") String userName;
    private @JsonProperty("title") String title;
    private @JsonProperty("type") String type;
    private @JsonProperty("allMembersAreAdministrators") Boolean allMembersAreAdministrators;
}
