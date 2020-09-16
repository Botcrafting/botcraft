package io.botcrafting.botcraft.core.message.repliers;

import static io.botcrafting.botcraft.configuration.constant.CommandConstant.EASTER_EGG_HELLO_WORLD;
import static io.botcrafting.botcraft.configuration.constant.UrlConstant.BOTCRAFT_API_BASE_IMAGES_URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import io.botcrafting.botcraft.core.message.MessageReplier;
import io.botcrafting.botcraft.core.model.Message;
import io.botcrafting.botcraft.core.service.MessageSenderService;

@Component
@Order(5)
public class HelloWorldPhraseReplier implements MessageReplier{

	@Autowired
	private MessageSenderService service;

	@Override
	public boolean processMessage(Message message) {
		if (message.getLoweredText().equals(EASTER_EGG_HELLO_WORLD)) {
			service.sendGif(message.getChatId(), String.format("%s%s", BOTCRAFT_API_BASE_IMAGES_URL, "cthulhu.gif.mp4"));
			return true;
		}
		return false;
	}

}