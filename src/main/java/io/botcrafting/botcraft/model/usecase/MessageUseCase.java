package io.botcrafting.botcraft.model.usecase;

import io.botcrafting.botcraft.model.Update;
import io.botcrafting.botcraft.model.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import static io.botcrafting.botcraft.configuration.constant.CommandConstant.*;
import static io.botcrafting.botcraft.configuration.constant.MessageConstant.*;
import static io.botcrafting.botcraft.configuration.constant.UrlConstant.GIF_CTHULHU;

public class MessageUseCase {

    @Autowired
    private MessageService messageService;

    public void handle(Update telegramUpdate) {
        if (telegramUpdate.getMessageText() != null) {
            long chatId = telegramUpdate.getMessage().getChat().getId();
            String fullName = telegramUpdate.getMessage().getUser().getFirstName() + " " +  telegramUpdate.getMessage().getUser().getLastName();
            if (telegramUpdate.getMessageText().toLowerCase().equals(COMMAND_HELP)) {
                messageService.sendMessageText(CTHULHU_AWAKEN, chatId);
            } else if ((telegramUpdate.getMessageText().toLowerCase().equals(EASTER_EGG_HELLO_WORLD)
                    || telegramUpdate.getMessageText().toLowerCase().contains(WORLD_CTHULHU))
                    && !telegramUpdate.getMessageText().toLowerCase().contains(COMMAND_HELP_EXCEPTION)
            ) {
                messageService.sendMessageVideo(GIF_CTHULHU, chatId);
            } else if (telegramUpdate.getMessageText().toLowerCase().contains(BOTCRAFT_NAME)) {
                messageService.sendMessageText(String.format(ANSWER_BOTCRAFT_MENTION, fullName), chatId);
            }
        }
    }
}
