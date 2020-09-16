package io.botcrafting.botcraft.core.message.repliers;

import static io.botcrafting.botcraft.configuration.constant.MessageConstant.ANSWER_STEPHEN_KING;
import static io.botcrafting.botcraft.configuration.constant.MessageConstant.BOTCRAFT_NAME;
import static io.botcrafting.botcraft.configuration.constant.MessageConstant.QUESTION_STEPHEN_KING;
import static io.botcrafting.botcraft.configuration.constant.UrlConstant.BOTCRAFT_API_BASE_IMAGES_URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import io.botcrafting.botcraft.core.message.MessageReplier;
import io.botcrafting.botcraft.core.model.Message;
import io.botcrafting.botcraft.core.service.MessageSenderService;

@Component
@Order(10)
public class StephenKingQuestionReplier implements MessageReplier{
	
	@Autowired
	private MessageSenderService service;
	
	@Override
	public boolean processMessage(Message message) {
		if (message.getLoweredText().contains(BOTCRAFT_NAME) && message.getLoweredText().contains(QUESTION_STEPHEN_KING)) {
			service.sendPhoto(message.getChatId(), String.format("%s%s", BOTCRAFT_API_BASE_IMAGES_URL, "sk_works.jpg"), String.format(ANSWER_STEPHEN_KING, message.getFullName()));
            return true;
        }
		return false;
	}

}
