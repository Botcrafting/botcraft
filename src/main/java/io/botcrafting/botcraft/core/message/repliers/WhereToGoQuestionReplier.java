package io.botcrafting.botcraft.core.message.repliers;

import static io.botcrafting.botcraft.configuration.constant.MessageConstant.ANSWER_WHERE_TO_GO;
import static io.botcrafting.botcraft.configuration.constant.MessageConstant.BOTCRAFT_NAME;
import static io.botcrafting.botcraft.configuration.constant.MessageConstant.QUESTION_WHERE_TO_GO;
import static io.botcrafting.botcraft.configuration.constant.UrlConstant.BOTCRAFT_API_BASE_IMAGES_URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import io.botcrafting.botcraft.core.message.MessageReplier;
import io.botcrafting.botcraft.core.model.Message;
import io.botcrafting.botcraft.core.service.MessageSenderService;

@Component
@Order(11)
public class WhereToGoQuestionReplier implements MessageReplier{
	
	@Autowired
	private MessageSenderService service;
	
	@Override
	public boolean processMessage(Message message) {
		 if (message.getLoweredText().contains(BOTCRAFT_NAME) && message.getLoweredText().contains(QUESTION_WHERE_TO_GO)) {
			 service.sendPhoto(message.getChatId(), String.format("%s%s", BOTCRAFT_API_BASE_IMAGES_URL, "sk_wheretogo.jpg"), String.format(ANSWER_WHERE_TO_GO, message.getFullName()));
            return true;
		 }
		 return false;
	}
}
