package io.botcrafting.botcraft.core.handler.processors;

import static io.botcrafting.botcraft.configuration.constant.CommandConstant.COMMAND_HELP_EXCEPTION;
import static io.botcrafting.botcraft.configuration.constant.CommandConstant.EASTER_EGG_CTHULHU_AWAKE;
import static io.botcrafting.botcraft.configuration.constant.CommandConstant.EASTER_EGG_HELLO_WORLD;
import static io.botcrafting.botcraft.configuration.constant.MessageConstant.WORD_CTHULHU;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import io.botcrafting.botcraft.core.handler.MessageReplierChain;
import io.botcrafting.botcraft.core.model.Message;
import io.botcrafting.botcraft.core.service.MessageSenderService;

@Component
public class ContainsCthulhuWord implements MessageReplier{
	private final MessageSenderService service;

	@Autowired
	private Environment currentProfile;

	@Autowired
	public ContainsCthulhuWord(MessageReplierChain chain, MessageSenderService service) {
		this.service = service;
		chain.registerProcessor(this);
	}

	@Override
	public boolean processMessage(Message message) {
		if (!message.getLoweredText().equals(EASTER_EGG_CTHULHU_AWAKE)
				&& !message.getLoweredText().contains(COMMAND_HELP_EXCEPTION)
				&& !message.getLoweredText().contains("/")
                && (message.getLoweredText().equals(EASTER_EGG_HELLO_WORLD) || message.getLoweredText().contains(WORD_CTHULHU))
		) {
			service.sendGif(
					message.getChatId(),
					String.format("%s%s", currentProfile.getProperty("bot_url") + "/images/", "cthulhu.gif.mp4")
			);
			return true;
		}
		return false;
	}
}