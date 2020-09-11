package io.botcrafting.botcraft.core.handler.processors;

import static io.botcrafting.botcraft.configuration.constant.CommandConstant.COMMAND_EASTER_EGG_LIST;
import static io.botcrafting.botcraft.configuration.constant.MessageConstant.EASTER_EGG_SHOW_COMMANDS;

import io.botcrafting.botcraft.core.model.Message;
import io.botcrafting.botcraft.core.service.BookService;
import io.botcrafting.botcraft.core.service.MessageSenderService;

public class CommandEasterEggList extends MessageProcessor{
	
	public CommandEasterEggList(Message message, MessageSenderService msgService, BookService bookService) {
		super(message, msgService, bookService);
	}

	@Override
	public void processMessage() {
		if(text.equals(COMMAND_EASTER_EGG_LIST)) {
			msgService.sendMessageText(message.getChatId(), String.format(EASTER_EGG_SHOW_COMMANDS, fullName));
			return;
		}
		nextProcessor.processMessage();
	}

}
