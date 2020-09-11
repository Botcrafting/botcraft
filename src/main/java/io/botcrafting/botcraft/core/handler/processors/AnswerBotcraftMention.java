package io.botcrafting.botcraft.core.handler.processors;

import static io.botcrafting.botcraft.configuration.constant.MessageConstant.ANSWER_BOTCRAFT_MENTION;
import static io.botcrafting.botcraft.configuration.constant.MessageConstant.BOTCRAFT_NAME;

import io.botcrafting.botcraft.core.model.Message;
import io.botcrafting.botcraft.core.service.BookService;
import io.botcrafting.botcraft.core.service.MessageSenderService;

public class AnswerBotcraftMention extends MessageProcessor{
	
	public AnswerBotcraftMention(Message message, MessageSenderService msgService, BookService bookService) {
		super(message, msgService, bookService);
	}

	@Override
	public void processMessage() {
		if (text.contains(BOTCRAFT_NAME) && !message.getText().contains("/")) {
            msgService.sendMessageText(message.getChatId(), String.format(ANSWER_BOTCRAFT_MENTION, fullName));
            return;
        }
	}

}
