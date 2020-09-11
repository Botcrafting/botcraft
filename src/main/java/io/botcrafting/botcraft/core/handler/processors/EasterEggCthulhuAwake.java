package io.botcrafting.botcraft.core.handler.processors;

import static io.botcrafting.botcraft.configuration.constant.CommandConstant.EASTER_EGG_CTHULHU_AWAKE;
import static io.botcrafting.botcraft.configuration.constant.MessageConstant.CTHULHU_AWAKEN;
import static io.botcrafting.botcraft.configuration.constant.UrlConstant.BOTCRAFT_API_BASE_IMAGES_URL;

import io.botcrafting.botcraft.core.model.Message;
import io.botcrafting.botcraft.core.service.BookService;
import io.botcrafting.botcraft.core.service.MessageSenderService;

public class EasterEggCthulhuAwake extends MessageProcessor{

	public EasterEggCthulhuAwake(Message message, MessageSenderService msgService, BookService bookService) {
		super(message, msgService, bookService);
	}

	@Override
	public void processMessage() {
		if (text.equals(EASTER_EGG_CTHULHU_AWAKE)) {
			msgService.sendGif(message.getChatId(), String.format("%s%s", BOTCRAFT_API_BASE_IMAGES_URL, "cthulhu.gif.mp4"));
			msgService.sendMessageText(message.getChatId(), CTHULHU_AWAKEN);
			return;
		}
		nextProcessor.processMessage();
	}

}
