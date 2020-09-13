package io.botcrafting.botcraft.core.handler.processors;

import static io.botcrafting.botcraft.configuration.constant.CommandConstant.COMMAND_EASTER_EGG_LIST;
import static io.botcrafting.botcraft.configuration.constant.MessageConstant.EASTER_EGG_SHOW_COMMANDS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.botcrafting.botcraft.core.handler.MessageChain;
import io.botcrafting.botcraft.core.model.Message;
import io.botcrafting.botcraft.core.service.MessageSenderService;

@Component
public class EasterEggListCommand implements MessageReplier{
	
	private MessageChain chain;
	private MessageSenderService service;
	
	@Autowired
	public EasterEggListCommand(MessageChain chain, MessageSenderService service) {
		this.chain = chain;
		this.service = service;
		chain.registerProcessor(this);
	}
	
	@Override
	public boolean processMessage(Message message) {
		if(message.getLoweredText().equals(COMMAND_EASTER_EGG_LIST)) {
			service.sendMessageText(message.getChatId(), String.format(EASTER_EGG_SHOW_COMMANDS, message.getFullName()));
			return true;
		}
		return false;
	}

}
