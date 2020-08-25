package io.botcrafting.botcraft.model.mapper;

import io.botcrafting.botcraft.model.Chat;
import io.botcrafting.botcraft.model.response.ChatResponse;

public class ChatMapper {
    public static Chat map(ChatResponse response) {
        Chat chat = new Chat();
        chat.setId(response.getId());
        chat.setFirstName((response.getFirstName() != null) ? response.getFirstName() : "");
        chat.setLastName((response.getLastName() != null) ? response.getLastName() : "");
        chat.setAllMembersAreAdministrators((response.getAllMembersAreAdministrators() != null) ? response.getAllMembersAreAdministrators() : false);
        chat.setTitle((response.getTitle() != null) ? response.getTitle() : "");
        chat.setType((response.getType() != null) ? response.getType() : "");
        chat.setUserName((response.getUserName() != null) ? response.getUserName() : "");
        return chat;
    }
}
