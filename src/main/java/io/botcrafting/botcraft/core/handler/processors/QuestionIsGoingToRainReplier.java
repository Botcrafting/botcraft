package io.botcrafting.botcraft.core.handler.processors;

import static io.botcrafting.botcraft.configuration.constant.MessageConstant.ANSWER_IS_GOING_TO_RAIN;
import static io.botcrafting.botcraft.configuration.constant.MessageConstant.BOTCRAFT_NAME;
import static io.botcrafting.botcraft.configuration.constant.MessageConstant.QUESTION_IS_GOING_TO_RAIN;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import io.botcrafting.botcraft.core.handler.MessageReplierChain;
import io.botcrafting.botcraft.core.model.Message;
import io.botcrafting.botcraft.core.service.MessageSenderService;

@Component
public class QuestionIsGoingToRainReplier implements MessageReplier{
	private final MessageSenderService service;

	@Autowired
	private Environment currentEnvironment;

	@Autowired
	public QuestionIsGoingToRainReplier(MessageReplierChain chain, MessageSenderService service) {
		this.service = service;
		chain.registerProcessor(this);
	}

	@Override
	public boolean processMessage(Message message) {
		if (message.getLoweredText().contains(BOTCRAFT_NAME) && message.getLoweredText().contains(QUESTION_IS_GOING_TO_RAIN)) {
			service.sendPhoto(
					message.getChatId(),
					String.format("%s%s", currentEnvironment.getProperty("bot_images_url"), "rain_today.jpg"),
					String.format(ANSWER_IS_GOING_TO_RAIN, message.getFullName())
			);
			return true;
        }
		return false;
	}
}
