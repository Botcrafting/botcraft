package io.botcrafting.botcraft.core.handler.processors;

import org.springframework.stereotype.Component;

import static io.botcrafting.botcraft.configuration.constant.MessageConstant.*;
import static io.botcrafting.botcraft.configuration.constant.UrlConstant.BOTCRAFT_API_BASE_IMAGES_URL;

import io.botcrafting.botcraft.core.handler.chain.MessageChain;
import io.botcrafting.botcraft.core.model.Message;
import io.botcrafting.botcraft.core.service.MessageSenderService;

@Component
public class ForTheHorde implements MessageProcessor{
	
	private MessageChain chain;
	private MessageSenderService service;
	
	public ForTheHorde(MessageChain chain, MessageSenderService service) {
		this.chain = chain;
		this.service = service;
		chain.registerProcessor(this);
	}

	@Override
	public boolean processMessage(Message message) {
		if(message.getLoweredText().contains(FOR_THE_HORDE)) {
			service.sendGif(message.getChatId(), String.format("%s%s", BOTCRAFT_API_BASE_IMAGES_URL, "forthehorde.mp4"));
			return true;
		}
		return false;
	}

}
