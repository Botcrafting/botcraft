package io.botcrafting.botcraft.model.service.handler;

import io.botcrafting.botcraft.model.Update;
import io.botcrafting.botcraft.model.request.TelegramMessageTextRequest;
import io.botcrafting.botcraft.model.request.TelegramMessageVideoRequest;
import io.botcrafting.botcraft.model.service.api.TelegramApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static io.botcrafting.botcraft.configuration.constant.CommandConstant.*;
import static io.botcrafting.botcraft.configuration.constant.MessageConstant.*;
import static io.botcrafting.botcraft.configuration.constant.UrlConstant.GIF_CTHULHU;

@Service
public class MessageHandler {

    @Autowired(required = false)
    private TelegramApi telegramApi;

    public void handle(Update telegramUpdate) {
        long chatId = telegramUpdate.getMessage().getChat().getId();
        String fullName = telegramUpdate.getMessage().getUser().getFirstName() + " " +  telegramUpdate.getMessage().getUser().getLastName();
        if (telegramUpdate.getMessage().getText().toLowerCase().equals(COMMAND_HELP)) {
            telegramApi.sendMessageText(new TelegramMessageTextRequest(chatId, CTHULHU_AWAKEN));
        } else if ((telegramUpdate.getMessage().getText().toLowerCase().equals(EASTER_EGG_HELLO_WORLD) ||
                telegramUpdate.getMessage().getText().toLowerCase().contains(WORLD_CTHULHU)) &&
                !telegramUpdate.getMessage().getText().toLowerCase().contains(COMMAND_HELP_EXCEPTION)
        ) {
            telegramApi.sendMessageVideo(new TelegramMessageVideoRequest(chatId, GIF_CTHULHU));
        } else if (telegramUpdate.getMessage().getText().toLowerCase().contains(BOTCRAFT_NAME)) {
            telegramApi.sendMessageText(new TelegramMessageTextRequest(chatId, String.format(ANSWER_BOTCRAFT_MENTION, fullName)));
        }
    }
}
