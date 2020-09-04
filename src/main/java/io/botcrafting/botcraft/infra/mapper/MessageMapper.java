package io.botcrafting.botcraft.infra.mapper;

import io.botcrafting.botcraft.core.model.Chat;
import io.botcrafting.botcraft.core.model.Message;
import io.botcrafting.botcraft.core.model.User;
import io.botcrafting.botcraft.infra.telegram.inbound.TelegramMessage;

public class MessageMapper {
    public static Message map(TelegramMessage telegramReceivedMessage) {
        Message message = new Message();
        message.setId(telegramReceivedMessage.getMessageId());
        message.setText((telegramReceivedMessage.getText() != null) ? telegramReceivedMessage.getText() : "");
        message.setDate(telegramReceivedMessage.getDate());
        message.setChat((telegramReceivedMessage.getReceivedChat() != null) ? ChatMapper.map(telegramReceivedMessage.getReceivedChat() ) : new Chat());
        message.setUser((telegramReceivedMessage.getReceivedUser() != null) ? UserMapper.map(telegramReceivedMessage.getReceivedUser()) : new User());
        return message;
    }
}
