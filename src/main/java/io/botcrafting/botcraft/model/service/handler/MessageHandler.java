package io.botcrafting.botcraft.model.service.handler;

import io.botcrafting.botcraft.model.UpdateResponse;
import io.botcrafting.botcraft.model.service.api.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static io.botcrafting.botcraft.configuration.constant.CommandConstant.*;
import static io.botcrafting.botcraft.configuration.constant.MessageConstant.*;
import static io.botcrafting.botcraft.configuration.constant.UrlConstant.GIF_CTHULHU;

@Service
public class MessageHandler {

    @Autowired(required = false)
    private MessageService messageService;

    public void handle(UpdateResponse telegramUpdateResponse) {
        if (telegramUpdateResponse.getMessageText() != null) {
            if (telegramUpdateResponse.getMessage() == null || telegramUpdateResponse.getMessage().getChat() == null) {
                System.out.println("Message or Chat is null");
                return;
            }
            long chatId = telegramUpdateResponse.getMessage().getChat().getId();
            String fullName = telegramUpdateResponse.getMessage().getUser().getFirstName() + " " +  telegramUpdateResponse.getMessage().getUser().getLastName();
            if (telegramUpdateResponse.getMessageText().toLowerCase().equals(COMMAND_HELP)) {
                messageService.sendMessageText(CTHULHU_AWAKEN, chatId);
            } else if ((telegramUpdateResponse.getMessageText().toLowerCase().equals(EASTER_EGG_HELLO_WORLD) ||
                    telegramUpdateResponse.getMessageText().toLowerCase().contains(WORLD_CTHULHU)) &&
                    !telegramUpdateResponse.getMessageText().toLowerCase().contains(COMMAND_HELP_EXCEPTION)
            ) {
                messageService.sendMessageVideo(GIF_CTHULHU, chatId);
            } else if (telegramUpdateResponse.getMessageText().toLowerCase().contains(BOTCRAFT_NAME)) {
                messageService.sendMessageText(String.format(ANSWER_BOTCRAFT_MENTION, fullName), chatId);
            }
        }
    }
}
