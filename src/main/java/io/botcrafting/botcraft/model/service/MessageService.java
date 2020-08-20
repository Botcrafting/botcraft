package io.botcrafting.botcraft.model.service;

import io.botcrafting.botcraft.model.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import static io.botcrafting.botcraft.configuration.constant.CommandConstant.*;
import static io.botcrafting.botcraft.configuration.constant.MessageConstant.*;
import static io.botcrafting.botcraft.configuration.constant.UrlConstant.*;

@Service
public class MessageService {

    @Autowired
    private RestTemplate restTemplate;

    public void handle(Update telegramUpdate) {

        if (telegramUpdate.getMessageText() != null) {
            if (telegramUpdate.getMessage() == null || telegramUpdate.getMessage().getChat() == null) {
                System.out.println("Message or Chat is null");
                return;
            }
            long chatId = telegramUpdate.getMessage().getChat().getId();
            String fullName = telegramUpdate.getMessage().getUser().getFirstName() + " " +  telegramUpdate.getMessage().getUser().getLastName();
            if (telegramUpdate.getMessageText().toLowerCase().equals(COMMAND_HELP)) {
                sendMessage(CTHULHU_AWAKEN, chatId);
            } else if ((telegramUpdate.getMessageText().toLowerCase().equals(EASTER_EGG_HELLO_WORLD) ||
                    telegramUpdate.getMessageText().toLowerCase().contains(WORLD_CTHULHU)) &&
                    !telegramUpdate.getMessageText().toLowerCase().contains(COMMAND_HELP_EXCEPTION)
            ) {
                sendVideo(GIF_CTHULHU, chatId);
            } else if (telegramUpdate.getMessageText().toLowerCase().contains(BOTCRAFT_NAME)) {
                sendMessage(String.format(ANSWER_BOTCRAFT_MENTION, fullName), chatId);
            }
        }
    }

    public void sendMessage(String text, long chatId) {
        String url = API_TELEGRAM_BASE_URL + System.getenv("botToken") + "/sendMessage";
        MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
        parts.add("chat_id", chatId);
        parts.add("text", text);
        System.out.println("I'm calling rest template on: " + url);
        restTemplate.postForObject(url, parts, String.class);
    }

    public void sendVideo(String videoUrl, long chatId) {
        String url = API_TELEGRAM_BASE_URL + System.getenv("botToken") + "/sendVideo";
        MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
        parts.add("chat_id", chatId);
        parts.add("video", videoUrl);
        System.out.println("I'm calling rest template on: " + url);
        restTemplate.postForObject(url, parts, String.class);
    }
}
