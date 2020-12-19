package io.botcrafting.botcraft.infra.api.telegram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import lombok.extern.java.Log;
import static io.botcrafting.botcraft.configuration.constant.MessageConstant.STANDARD_ERROR_MESSAGE;
import io.botcrafting.botcraft.core.service.MessageSenderService;
import io.botcrafting.botcraft.infra.telegram.outbound.TelegramMessagePhoto;
import io.botcrafting.botcraft.infra.telegram.outbound.TelegramMessageText;
import io.botcrafting.botcraft.infra.telegram.outbound.TelegramMessageVideo;


@Service
@Log
public class TelegramApi implements MessageSenderService{
    @Autowired
    private RestTemplate restTemplate;

	@Autowired
	private Environment currentEnvironment;

	@Override
	public void sendMessageText(long chatId, String messageText) throws HttpClientErrorException{
		String url = currentEnvironment.getProperty("telegram_api_base_url") + "/sendMessage";
		log.info("I'm calling rest template on: " + url + " for sending message text");
		try {
			restTemplate.postForObject(url, new TelegramMessageText(chatId, messageText), String.class);
		} catch (HttpClientErrorException http) {
			restTemplate.postForObject(url, new TelegramMessageText(chatId, STANDARD_ERROR_MESSAGE), String.class);
			if (http.getMessage() != null) {
				throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, http.getMessage());
			}
		}
	}

	@Override
	public void sendPhoto(long chatId, String imageUrl, String messageText) throws HttpClientErrorException{
		String url = currentEnvironment.getProperty("telegram_api_base_url") + "/sendPhoto";
		log.info("I'm calling rest template on: " + url + " for sending message photo");
		log.info("Photo URL: " + imageUrl);
		try {
			restTemplate.postForObject(url, new TelegramMessagePhoto(chatId, imageUrl, messageText), String.class);
		} catch (HttpClientErrorException http) {
			restTemplate.postForObject(url, new TelegramMessageText(chatId, STANDARD_ERROR_MESSAGE), String.class);
			if (http.getMessage() != null) {
				throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, http.getMessage());
			}
		}
	}
	
	@Override
	public void sendGif(long chatId, String gifUrl) throws HttpClientErrorException{
		String url = currentEnvironment.getProperty("telegram_api_base_url") + "/sendVideo";
		log.info("I'm calling rest template on: " + url + " for sending message gif");
		log.info("Gif URL: " + gifUrl);
		try {
			restTemplate.postForObject(url, new TelegramMessageVideo(chatId, gifUrl), String.class);
		} catch (HttpClientErrorException http) {
			restTemplate.postForObject(url, new TelegramMessageText(chatId, STANDARD_ERROR_MESSAGE), String.class);
			if (http.getMessage() != null) {
				throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, http.getMessage());
			}
		}
	}
}