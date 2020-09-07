package io.botcrafting.botcraft.infra.telegram.inbound;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.botcrafting.botcraft.core.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TelegramUser {
    private @JsonProperty("id") int id;
    private @JsonProperty("is_bot") Boolean isBot;
    private @JsonProperty("first_name") String firstName;
    private @JsonProperty("last_name") String lastName;
    private @JsonProperty("username") String userName;
    private @JsonProperty("language_code") String languageCode;
    
    
    public static User toCoreUser(TelegramUser response) {
        User user = new User();
        user.setFirstName((response.getFirstName() != null) ? response.getFirstName() : "");
        user.setLastName((response.getLastName() != null) ? response.getLastName() : "");
        return user;
    }
}
