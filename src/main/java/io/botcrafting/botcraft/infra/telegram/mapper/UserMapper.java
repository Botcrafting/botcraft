package io.botcrafting.botcraft.infra.telegram.mapper;

import io.botcrafting.botcraft.core.model.User;
import io.botcrafting.botcraft.infra.telegram.inbound.TelegramUser;

public class UserMapper {
    public static User map(TelegramUser response) {
        User user = new User();
        user.setFirstName((response.getFirstName() != null) ? response.getFirstName() : "");
        user.setLastName((response.getLastName() != null) ? response.getLastName() : "");
        return user;
    }
}
