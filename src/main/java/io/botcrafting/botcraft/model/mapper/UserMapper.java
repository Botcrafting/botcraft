package io.botcrafting.botcraft.model.mapper;

import io.botcrafting.botcraft.model.User;
import io.botcrafting.botcraft.model.response.UserResponse;

public class UserMapper {
    public static User map(UserResponse response) {
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
