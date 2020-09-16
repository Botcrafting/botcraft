package io.botcrafting.botcraft.core.handler.processors;

import static io.botcrafting.botcraft.configuration.constant.MessageConstant.ANSWER_WHERE_TO_GO;
import static io.botcrafting.botcraft.configuration.constant.MessageConstant.BOTCRAFT_NAME;
import static io.botcrafting.botcraft.configuration.constant.MessageConstant.QUESTION_WHERE_TO_GO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import io.botcrafting.botcraft.core.handler.MessageReplierChain;
import io.botcrafting.botcraft.core.model.Message;
import io.botcrafting.botcraft.core.service.MessageSenderService;

@Component
public class QuestionWhereToGoReplier implements MessageReplier{
	private final MessageSenderService service;

	@Autowired
	private Environment currentEnvironment;

	@Autowired
	public QuestionWhereToGoReplier(MessageReplierChain chain, MessageSenderService service) {
		this.service = service;
		chain.registerProcessor(this);
	}

	@Override
	public boolean processMessage(Message message) {
		 if (message.getLoweredText().contains(BOTCRAFT_NAME) && message.getLoweredText().contains(QUESTION_WHERE_TO_GO)) {
			 service.sendPhoto(
			 		message.getChatId(),
					 String.format("%s%s", currentEnvironment.getProperty("bot_images_url"), "sk_wheretogo.jpg"),
					 String.format(ANSWER_WHERE_TO_GO, message.getFullName())
			 );
            return true;
		 }
		 return false;
	}
}
