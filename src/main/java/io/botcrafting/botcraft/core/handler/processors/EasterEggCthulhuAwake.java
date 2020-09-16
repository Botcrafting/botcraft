package io.botcrafting.botcraft.core.handler.processors;

import static io.botcrafting.botcraft.configuration.constant.CommandConstant.EASTER_EGG_CTHULHU_AWAKE;
import static io.botcrafting.botcraft.configuration.constant.MessageConstant.CTHULHU_AWAKEN;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import io.botcrafting.botcraft.core.handler.MessageReplierChain;
import io.botcrafting.botcraft.core.model.Message;
import io.botcrafting.botcraft.core.service.MessageSenderService;

@Component
public class EasterEggCthulhuAwake implements MessageReplier{
	private final MessageSenderService service;

	@Autowired
	private Environment currentEnvironment;

	@Autowired
	public EasterEggCthulhuAwake(MessageReplierChain chain, MessageSenderService service) {
		this.service = service;
		chain.registerProcessor(this);
	}

	@Override
	public boolean processMessage(Message message) {
		if (message.getLoweredText().equals(EASTER_EGG_CTHULHU_AWAKE)) {
			service.sendGif(
					message.getChatId(),
					String.format("%s%s", currentEnvironment.getProperty("bot_images_url"), "cthulhu.gif.mp4")
			);
			service.sendMessageText(message.getChatId(), CTHULHU_AWAKEN);
			return true;
		}
		return false;
	}
}
