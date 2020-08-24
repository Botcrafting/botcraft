package io.botcrafting.botcraft.model.mapper;

import io.botcrafting.botcraft.model.Chat;
import io.botcrafting.botcraft.model.Message;
import io.botcrafting.botcraft.model.User;
import io.botcrafting.botcraft.model.response.MessageResponse;

public class MessageMapper {
    public static Message map(MessageResponse response) {
        Message message = new Message();
        message.setId(response.getMessageId());
        message.setText((response.getText() != null) ? response.getText() : "");
        message.setDate(response.getDate());
        message.setChat((response.getChat() != null) ? ChatMapper.map(response.getChat()) : new Chat());
        message.setUser((response.getUser() != null) ? UserMapper.map(response.getUser()) : new User());
        return message;
    }
}
