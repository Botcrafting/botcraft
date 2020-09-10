package io.botcrafting.botcraft.core.handler.processors;

import io.botcrafting.botcraft.core.model.Message;
import static io.botcrafting.botcraft.configuration.constant.CommandConstant.COMMAND_EASTER_EGG_LIST;

public class CommandEasterEggList extends MessageProcessor{

	public CommandEasterEggList(Message message) {
		super(message);
	}

	@Override
	public void processMessage() {
		if(message.equals(COMMAND_EASTER_EGG_LIST)) {
			msgService.sendMessageText(message.getChatId(), message.getText());
			return;
		}
		nextProcessor.processMessage();
	}

}
