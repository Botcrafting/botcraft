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
        message.setChat((response.getChatResponse() != null) ? ChatMapper.map(response.getChatResponse()) : new Chat());
        message.setUser((response.getUserResponse() != null) ? UserMapper.map(response.getUserResponse()) : new User());
        return message;
    }
}
