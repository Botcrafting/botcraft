package io.botcrafting.botcraft.model.service.api;

import io.botcrafting.botcraft.model.request.TelegramMessageTextRequest;
import io.botcrafting.botcraft.model.request.TelegramMessageVideoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import static io.botcrafting.botcraft.configuration.constant.UrlConstant.API_TELEGRAM_BASE_URL;

@Service
public class TelegramApi {

    @Autowired
    private RestTemplate restTemplate;

    public void sendMessageText(TelegramMessageTextRequest request) {
        //String url = API_TELEGRAM_BASE_URL + System.getenv("botToken") + "/sendMessage";
        String url = API_TELEGRAM_BASE_URL + System.getenv("botBarzaiToken") + "/sendMessage";
        System.out.println("I'm calling rest template on: " + url);
        restTemplate.postForObject(url, request, String.class);
    }

    public void sendMessageVideo(TelegramMessageVideoRequest request) {
        String url = API_TELEGRAM_BASE_URL + System.getenv("botToken") + "/sendVideo";
        System.out.println("I'm calling rest template on: " + url);
        restTemplate.postForObject(url, request, String.class);
    }
}