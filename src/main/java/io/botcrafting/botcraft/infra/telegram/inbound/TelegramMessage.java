package io.botcrafting.botcraft.infra.telegram.inbound;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.botcrafting.botcraft.core.model.Message;
import io.botcrafting.botcraft.core.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TelegramMessage {
    private @JsonProperty("from") TelegramUser receivedUser;
    private @JsonProperty("chat") TelegramChat receivedChat;
    private @JsonProperty("messageId") int messageId;
    private @JsonProperty("date") int date;
    private @JsonProperty("text") String text;
    
    
    public static Message toCoreMessage(TelegramMessage telegramReceivedMessage) {
        Message message = new Message();
    	message.setText((telegramReceivedMessage.getText() != null) ? telegramReceivedMessage.getText() : "");
        message.setDate(telegramReceivedMessage.getDate());
        message.setChatId(telegramReceivedMessage.getReceivedChat().getId());
        message.setUser((telegramReceivedMessage.getReceivedUser() != null) ? TelegramUser.toCoreUser(telegramReceivedMessage.getReceivedUser()) : new User());
        return message;
    }
}
