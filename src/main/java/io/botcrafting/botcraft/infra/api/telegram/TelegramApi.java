package io.botcrafting.botcraft.infra.api.telegram;

import static io.botcrafting.botcraft.configuration.constant.UrlConstant.API_TELEGRAM_BASE_URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.botcrafting.botcraft.core.service.MessageSenderService;
import io.botcrafting.botcraft.infra.telegram.outbound.TelegramMessagePhoto;
import io.botcrafting.botcraft.infra.telegram.outbound.TelegramMessageText;
import io.botcrafting.botcraft.infra.telegram.outbound.TelegramMessageVideo;

@Service
public class TelegramApi implements MessageSenderService{

    @Autowired
    private RestTemplate restTemplate;


	@Override
	public void sendMessageText(long chatId, String messageText) {
		String url = API_TELEGRAM_BASE_URL + System.getenv("botToken") + "/sendMessage";
		System.out.println("I'm calling rest template on: " + url);
		restTemplate.postForObject(url, new TelegramMessageText(chatId, messageText), String.class);
		
	}

	@Override
	public void sendPhoto(long chatId, String imageUrl, String messageText) {
		String url = API_TELEGRAM_BASE_URL + System.getenv("botToken") + "/sendPhoto";
        System.out.println("I'm calling rest template on: " + url);
        restTemplate.postForObject(url, new TelegramMessagePhoto(chatId, imageUrl, messageText), String.class);
		
	}
	
	@Override
	public void sendGif(long chatId, String gifUrl) {
		String url = API_TELEGRAM_BASE_URL + System.getenv("botToken") + "/sendVideo";
		System.out.println("I'm calling rest template on: " + url);
		restTemplate.postForObject(url, new TelegramMessageVideo(chatId, gifUrl), String.class);
		
	}
}