package io.botcrafting.botcraft.core.message.repliers;

import static io.botcrafting.botcraft.configuration.constant.MessageConstant.KILLING_MONSTERS;
import static io.botcrafting.botcraft.configuration.constant.UrlConstant.BOTCRAFT_API_BASE_IMAGES_URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import io.botcrafting.botcraft.core.message.MessageReplier;
import io.botcrafting.botcraft.core.model.Message;
import io.botcrafting.botcraft.core.service.MessageSenderService;

@Component
@Order(7)
public class KillingMonstersPhraseReplier implements MessageReplier{
	
	@Autowired
	private MessageSenderService service;

	@Override
	public boolean processMessage(Message message) {
		if(message.getLoweredText().contains(KILLING_MONSTERS)) {
			service.sendGif(message.getChatId(), String.format("%s%s", BOTCRAFT_API_BASE_IMAGES_URL, "killingmonsters.mp4"));
			return true;
		}
		return false;
	}

}
