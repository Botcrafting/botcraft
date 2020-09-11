package io.botcrafting.botcraft.core.handler.processors;

import static io.botcrafting.botcraft.configuration.constant.MessageConstant.*;
import static io.botcrafting.botcraft.configuration.constant.UrlConstant.BOTCRAFT_API_BASE_IMAGES_URL;

import io.botcrafting.botcraft.core.model.Message;
import io.botcrafting.botcraft.core.service.BookService;
import io.botcrafting.botcraft.core.service.MessageSenderService;

public class QuestionWhereToGo extends MessageProcessor{
	
	public QuestionWhereToGo(Message message, MessageSenderService msgService, BookService bookService) {
		super(message, msgService, bookService);
	}

	@Override
	public void processMessage() {
		 if (text.contains(BOTCRAFT_NAME) && text.contains(QUESTION_WHERE_TO_GO)) {
			 msgService.sendPhoto(message.getChatId(), String.format("%s%s", BOTCRAFT_API_BASE_IMAGES_URL, "sk_wheretogo.jpg"), String.format(ANSWER_WHERE_TO_GO, fullName));
            return;
		 }
		 nextProcessor.processMessage();
	}
}
