package io.botcrafting.botcraft.core.message.repliers;

import static io.botcrafting.botcraft.configuration.constant.CommandConstant.COMMAND_EASTER_EGG_LIST;
import static io.botcrafting.botcraft.configuration.constant.MessageConstant.EASTER_EGG_SHOW_COMMANDS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import io.botcrafting.botcraft.core.message.MessageReplier;
import io.botcrafting.botcraft.core.model.Message;
import io.botcrafting.botcraft.core.service.MessageSenderService;

@Component
@Order(2)
public class EasterEggListCommandReplier implements MessageReplier{
	
	@Autowired
	private MessageSenderService service;

	@Override
	public boolean processMessage(Message message) {
		if(message.getLoweredText().equals(COMMAND_EASTER_EGG_LIST)) {
			service.sendMessageText(message.getChatId(), String.format(EASTER_EGG_SHOW_COMMANDS, message.getFullName()));
			return true;
		}
		return false;
	}

}
