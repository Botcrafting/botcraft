package io.botcrafting.botcraft.core.message.repliers;

import static io.botcrafting.botcraft.configuration.constant.MessageConstant.ANSWER_BOTCRAFT_MENTION;
import static io.botcrafting.botcraft.configuration.constant.MessageConstant.BOTCRAFT_NAME;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import io.botcrafting.botcraft.core.message.MessageReplier;
import io.botcrafting.botcraft.core.model.Message;
import io.botcrafting.botcraft.core.service.MessageSenderService;

@Component
@Order(13)
public class BotcraftMentionReplier implements MessageReplier{
	
	@Autowired
	private MessageSenderService service;

	@Override
	public boolean processMessage(Message message) {
		if (message.getLoweredText().contains(BOTCRAFT_NAME)) {
            service.sendMessageText(message.getChatId(), String.format(ANSWER_BOTCRAFT_MENTION, message.getFullName()));
            return true;
        }
		return false;
	}

}
