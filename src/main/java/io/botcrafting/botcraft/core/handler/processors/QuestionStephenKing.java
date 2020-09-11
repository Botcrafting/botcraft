package io.botcrafting.botcraft.core.handler.processors;

import static io.botcrafting.botcraft.configuration.constant.MessageConstant.ANSWER_STEPHEN_KING;
import static io.botcrafting.botcraft.configuration.constant.MessageConstant.BOTCRAFT_NAME;
import static io.botcrafting.botcraft.configuration.constant.MessageConstant.QUESTION_STEPHEN_KING;
import static io.botcrafting.botcraft.configuration.constant.UrlConstant.BOTCRAFT_API_BASE_IMAGES_URL;

import io.botcrafting.botcraft.core.model.Message;
import io.botcrafting.botcraft.core.service.BookService;
import io.botcrafting.botcraft.core.service.MessageSenderService;

public class QuestionStephenKing extends MessageProcessor{
	
	public QuestionStephenKing(Message message, MessageSenderService msgService, BookService bookService) {
		super(message, msgService, bookService);
	}

	@Override
	public void processMessage() {
		if (text.contains(BOTCRAFT_NAME) && text.contains(QUESTION_STEPHEN_KING)) {
			msgService.sendPhoto(message.getChatId(), String.format("%s%s", BOTCRAFT_API_BASE_IMAGES_URL, "sk_works.jpg"), String.format(ANSWER_STEPHEN_KING, fullName));
            return;
        }
		nextProcessor.processMessage();
	}

}
