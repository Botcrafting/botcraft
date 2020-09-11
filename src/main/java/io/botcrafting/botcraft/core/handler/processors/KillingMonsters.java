package io.botcrafting.botcraft.core.handler.processors;

import org.springframework.stereotype.Component;

import static io.botcrafting.botcraft.configuration.constant.MessageConstant.*;
import static io.botcrafting.botcraft.configuration.constant.UrlConstant.BOTCRAFT_API_BASE_IMAGES_URL;

import io.botcrafting.botcraft.core.handler.chain.Chain;
import io.botcrafting.botcraft.core.model.Message;
import io.botcrafting.botcraft.core.service.MessageSenderService;

@Component
public class KillingMonsters implements MessageProcessor{
	
	private Chain chain;
	private MessageSenderService service;
	
	public KillingMonsters(Chain chain, MessageSenderService service) {
		this.chain = chain;
		this.service = service;
		chain.registerProcessor(this);
	}

	@Override
	public boolean processMessage(Message message) {
		if(message.getLoweredText().contains(KILLING_MONSTERS)) {
			service.sendGif(message.getChatId(), String.format("%s%s", BOTCRAFT_API_BASE_IMAGES_URL, "killingmonsters.mp4"));
		}
		return false;
	}

}
