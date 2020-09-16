package io.botcrafting.botcraft.core.message.repliers;

import static io.botcrafting.botcraft.configuration.constant.CommandConstant.COMMAND_HELP;
import static io.botcrafting.botcraft.configuration.constant.MessageConstant.HELP_SHOW_COMMANDS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import io.botcrafting.botcraft.core.message.MessageReplier;
import io.botcrafting.botcraft.core.model.Message;
import io.botcrafting.botcraft.core.service.MessageSenderService;

@Component
@Order(1)
public class HelpCommandReplier implements MessageReplier{
	
	@Autowired
	private MessageSenderService service;

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
