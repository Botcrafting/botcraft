package io.botcrafting.botcraft.core.handler.processors;

import static io.botcrafting.botcraft.configuration.constant.CommandConstant.EASTER_EGG_CTHULHU_AWAKE;
import static io.botcrafting.botcraft.configuration.constant.MessageConstant.CTHULHU_AWAKEN;
import static io.botcrafting.botcraft.configuration.constant.UrlConstant.BOTCRAFT_API_BASE_IMAGES_URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.botcrafting.botcraft.core.handler.MessageChain;
import io.botcrafting.botcraft.core.model.Message;
import io.botcrafting.botcraft.core.service.MessageSenderService;

@Component
public class EasterEggCthulhuAwake implements MessageReplier{

	private MessageChain chain;
	private MessageSenderService service;

	@Autowired
	public EasterEggCthulhuAwake(MessageChain chain, MessageSenderService service) {
		this.chain = chain;
		this.service = service;
		chain.registerProcessor(this);
	}

	@Override
	public boolean processMessage(Message message) {
		if (message.getLoweredText().equals(EASTER_EGG_CTHULHU_AWAKE)) {
			service.sendGif(message.getChatId(), String.format("%s%s", BOTCRAFT_API_BASE_IMAGES_URL, "cthulhu.gif.mp4"));
			service.sendMessageText(message.getChatId(), CTHULHU_AWAKEN);
			return true;
		}
		return false;
	}

}
