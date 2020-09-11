package io.botcrafting.botcraft.core.handler.processors;

import static io.botcrafting.botcraft.configuration.constant.CommandConstant.COMMAND_HELP;
import static io.botcrafting.botcraft.configuration.constant.MessageConstant.HELP_SHOW_COMMANDS;

import io.botcrafting.botcraft.core.model.Message;
import io.botcrafting.botcraft.core.service.BookService;
import io.botcrafting.botcraft.core.service.MessageSenderService;

public class CommandHelp extends MessageProcessor{

	public CommandHelp(Message message, MessageSenderService msgService, BookService bookService) {
		super(message, msgService, bookService);
	}

	@Override
	public void processMessage() {
		if(text.startsWith("/") && message.getText().contains(COMMAND_HELP)) {
			msgService.sendMessageText(message.getChatId(), 
					String.format(HELP_SHOW_COMMANDS, fullName));
			return;
		}
		nextProcessor.processMessage();
	}
}
