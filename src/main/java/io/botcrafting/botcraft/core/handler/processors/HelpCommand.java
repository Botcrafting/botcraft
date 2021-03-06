package io.botcrafting.botcraft.core.handler.processors;

import static io.botcrafting.botcraft.configuration.constant.CommandConstant.COMMAND_HELP;
import static io.botcrafting.botcraft.configuration.constant.MessageConstant.HELP_SHOW_COMMANDS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.botcrafting.botcraft.core.handler.MessageReplierChain;
import io.botcrafting.botcraft.core.model.Message;
import io.botcrafting.botcraft.core.service.MessageSenderService;

@Component
public class HelpCommand implements MessageReplier{
	private final MessageSenderService service;

	@Autowired
	public HelpCommand(MessageReplierChain chain, MessageSenderService service) {
		this.service = service;
		chain.registerProcessor(this);
	}

	@Override
	public boolean processMessage(Message message) {
		if(message.getLoweredText().startsWith("/") && message.getText().contains(COMMAND_HELP)) {
			service.sendMessageText(message.getChatId(), 
					String.format(HELP_SHOW_COMMANDS, message.getFullName()));
			return true;
		}
		return false;
	}
}
