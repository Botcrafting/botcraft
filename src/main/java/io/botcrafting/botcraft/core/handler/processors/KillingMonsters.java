package io.botcrafting.botcraft.core.handler.processors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import static io.botcrafting.botcraft.configuration.constant.MessageConstant.*;

import io.botcrafting.botcraft.core.handler.MessageReplierChain;
import io.botcrafting.botcraft.core.model.Message;
import io.botcrafting.botcraft.core.service.MessageSenderService;

@Component
public class KillingMonsters implements MessageReplier{
	private final MessageSenderService service;

	@Autowired
	private Environment currentEnvironment;

	@Autowired
	public KillingMonsters(MessageReplierChain chain, MessageSenderService service) {
		this.service = service;
		chain.registerProcessor(this);
	}

	@Override
	public boolean processMessage(Message message) {
		if(message.getLoweredText().contains(KILLING_MONSTERS)) {
			service.sendGif(
					message.getChatId(),
					String.format("%s%s", currentEnvironment.getProperty("bot_images_url"), "killingmonsters.mp4")
			);
			return true;
		}
		return false;
	}
}
