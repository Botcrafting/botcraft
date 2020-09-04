package io.botcrafting.botcraft.infra.service.api.telegram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.botcrafting.botcraft.infra.telegram.outbound.TelegramMessagePhoto;
import io.botcrafting.botcraft.infra.telegram.outbound.TelegramMessageText;
import io.botcrafting.botcraft.infra.telegram.outbound.TelegramMessageVideo;

import static io.botcrafting.botcraft.configuration.constant.UrlConstant.API_TELEGRAM_BASE_URL;

@Service
public class TelegramApi {

    @Autowired
    private RestTemplate restTemplate;

    public void sendMessageText(TelegramMessageText request) {
        String url = API_TELEGRAM_BASE_URL + System.getenv("botToken") + "/sendMessage";
        System.out.println("I'm calling rest template on: " + url);
        restTemplate.postForObject(url, request, String.class);
    }

    public void sendMessageVideo(TelegramMessageVideo request) {
        String url = API_TELEGRAM_BASE_URL + System.getenv("botToken") + "/sendVideo";
        System.out.println("I'm calling rest template on: " + url);
        restTemplate.postForObject(url, request, String.class);
    }

    public void sendMessagePhoto(TelegramMessagePhoto request) {
        String url = API_TELEGRAM_BASE_URL + System.getenv("botToken") + "/sendPhoto";
        System.out.println("I'm calling rest template on: " + url);
        restTemplate.postForObject(url, request, String.class);
    }
}