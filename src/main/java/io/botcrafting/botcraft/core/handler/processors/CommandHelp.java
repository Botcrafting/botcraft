package io.botcrafting.botcraft.core.handler.processors;

import static io.botcrafting.botcraft.configuration.constant.MessageConstant.HELP_SHOW_COMMANDS;
import static io.botcrafting.botcraft.configuration.constant.CommandConstant.COMMAND_HELP;

import io.botcrafting.botcraft.core.model.Message;

public class CommandHelp extends MessageProcessor{
	
	public CommandHelp(Message message) {
		super(message);
	}

	@Override
	public void processMessage() {
		if(message.getText().startsWith("/") && message.getText().contains(COMMAND_HELP)) {
			msgService.sendMessageText(message.getChatId(), 
					String.format(HELP_SHOW_COMMANDS, fullName));
			return;
		}
		nextProcessor.processMessage();
	}
}
