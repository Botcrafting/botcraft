package io.botcrafting.botcraft.core.handler.processors;

import static io.botcrafting.botcraft.configuration.constant.MessageConstant.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.botcrafting.botcraft.core.handler.chain.Chain;
import io.botcrafting.botcraft.core.model.Message;
import io.botcrafting.botcraft.core.service.MessageSenderService;

@Component
public class AnswerBotcraftMention implements MessageProcessor{
	
	private Chain chain;
	private MessageSenderService service;
	
	@Autowired
	public AnswerBotcraftMention(Chain chain, MessageSenderService service) {
		this.chain = chain;
		this.service = service;
		chain.registerProcessor(this);
	}

	@Override
	public boolean processMessage(Message message) {
		if (message.getLoweredText().contains(BOTCRAFT_NAME) && !message.getText().contains("/") && !message.getLoweredText().contains(QUESTION_WHERE_TO_GO)
				&& !message.getLoweredText().contains(QUESTION_STEPHEN_KING) && !message.getLoweredText().contains(QUESTION_IS_GOING_TO_RAIN)) {
            service.sendMessageText(message.getChatId(), String.format(ANSWER_BOTCRAFT_MENTION, message.getFullName()));
            return true;
        }
		return false;
	}

}
