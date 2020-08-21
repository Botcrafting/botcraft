package io.botcrafting.botcraft.model.service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import static io.botcrafting.botcraft.configuration.constant.UrlConstant.API_TELEGRAM_BASE_URL;

@Service
public class MessageService {

    @Autowired
    private RestTemplate restTemplate;

    public void sendMessageText(String text, long chatId) {
        String url = API_TELEGRAM_BASE_URL + System.getenv("botToken") + "/sendMessage";
        MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
        parts.add("chat_id", chatId);
        parts.add("text", text);
        System.out.println("I'm calling rest template on: " + url);
        restTemplate.postForObject(url, parts, String.class);
    }

    public void sendMessageVideo(String videoUrl, long chatId) {
        String url = API_TELEGRAM_BASE_URL + System.getenv("botToken") + "/sendVideo";
        MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
        parts.add("chat_id", chatId);
        parts.add("video", videoUrl);
        System.out.println("I'm calling rest template on: " + url);
        restTemplate.postForObject(url, parts, String.class);
    }
}
