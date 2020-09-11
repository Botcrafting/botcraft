package io.botcrafting.botcraft.core.handler.processors;

import static io.botcrafting.botcraft.configuration.constant.MessageConstant.ANSWER_IS_GOING_TO_RAIN;
import static io.botcrafting.botcraft.configuration.constant.MessageConstant.BOTCRAFT_NAME;
import static io.botcrafting.botcraft.configuration.constant.MessageConstant.QUESTION_IS_GOING_TO_RAIN;
import static io.botcrafting.botcraft.configuration.constant.UrlConstant.BOTCRAFT_API_BASE_IMAGES_URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.botcrafting.botcraft.core.handler.chain.Chain;
import io.botcrafting.botcraft.core.model.Message;
import io.botcrafting.botcraft.core.service.MessageSenderService;

@Component
public class QuestionIsGoingToRain implements MessageProcessor{
	
	private Chain chain;
	private MessageSenderService service;

	@Autowired
	public QuestionIsGoingToRain(Chain chain, MessageSenderService service) {
		this.chain = chain;
		this.service = service;
		chain.registerProcessor(this);
	}

	@Override
	public boolean processMessage(Message message) {
		if (message.getLoweredText().contains(BOTCRAFT_NAME) && message.getLoweredText().contains(QUESTION_IS_GOING_TO_RAIN)) {
			service.sendPhoto(message.getChatId(), String.format("%s%s", BOTCRAFT_API_BASE_IMAGES_URL, "rain_today.jpg"), String.format(ANSWER_IS_GOING_TO_RAIN, message.getFullName()));
			return true;
        }
		return false;
	}

}
