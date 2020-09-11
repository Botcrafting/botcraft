package io.botcrafting.botcraft.core.handler.processors;

import static io.botcrafting.botcraft.configuration.constant.CommandConstant.COMMAND_HELP_EXCEPTION;
import static io.botcrafting.botcraft.configuration.constant.CommandConstant.EASTER_EGG_CTHULHU_AWAKE;
import static io.botcrafting.botcraft.configuration.constant.CommandConstant.EASTER_EGG_HELLO_WORLD;
import static io.botcrafting.botcraft.configuration.constant.MessageConstant.WORD_CTHULHU;
import static io.botcrafting.botcraft.configuration.constant.UrlConstant.BOTCRAFT_API_BASE_IMAGES_URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.botcrafting.botcraft.core.handler.chain.Chain;
import io.botcrafting.botcraft.core.model.Message;
import io.botcrafting.botcraft.core.service.MessageSenderService;

@Component
public class ContainsCthulhuWord implements MessageProcessor{

	private Chain chain;
	private MessageSenderService service;

	@Autowired
	public ContainsCthulhuWord(Chain chain, MessageSenderService service) {
		this.chain = chain;
		this.service = service;
		chain.registerProcessor(this);
	}

	@Override
	public boolean processMessage(Message message) {
		if (!message.getLoweredText().equals(EASTER_EGG_CTHULHU_AWAKE) && !message.getLoweredText().contains(COMMAND_HELP_EXCEPTION) && !message.getLoweredText().contains("/")
                && (message.getLoweredText().equals(EASTER_EGG_HELLO_WORLD) || message.getLoweredText().contains(WORD_CTHULHU))) {
			service.sendGif(message.getChatId(), String.format("%s%s", BOTCRAFT_API_BASE_IMAGES_URL, "cthulhu.gif.mp4"));
			return true;
		}
		return false;
	}

}
