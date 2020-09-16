package io.botcrafting.botcraft.core.message.repliers;

import static io.botcrafting.botcraft.configuration.constant.MessageConstant.FOR_THE_HORDE;
import static io.botcrafting.botcraft.configuration.constant.UrlConstant.BOTCRAFT_API_BASE_IMAGES_URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import io.botcrafting.botcraft.core.message.MessageReplier;
import io.botcrafting.botcraft.core.model.Message;
import io.botcrafting.botcraft.core.service.MessageSenderService;

@Component
@Order(6)
public class ForTheHordePhraseReplier implements MessageReplier{
	
	@Autowired
	private MessageSenderService service;
	
	@Override
	public boolean processMessage(Message message) {
		if(message.getLoweredText().contains(FOR_THE_HORDE)) {
			service.sendGif(message.getChatId(), String.format("%s%s", BOTCRAFT_API_BASE_IMAGES_URL, "forthehorde.mp4"));
			return true;
		}
		return false;
	}

}
