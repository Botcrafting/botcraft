package io.botcrafting.botcraft.infra.mapper;

import io.botcrafting.botcraft.core.model.User;
import io.botcrafting.botcraft.infra.telegram.inbound.TelegramUser;

public class UserMapper {
    public static User map(TelegramUser response) {
        User user = new User();
        user.setIsBot((response.getIsBot() != null) ? response.getIsBot() : false);
        user.setFirstName((response.getFirstName() != null) ? response.getFirstName() : "");
        user.setUserName((response.getUserName() != null) ? response.getUserName() : "");
        user.setLastName((response.getLastName() != null) ? response.getLastName() : "");
        user.setId(response.getId());
        user.setLanguageCode((response.getLanguageCode() != null) ? response.getLanguageCode() : "");
        return user;
    }
}
