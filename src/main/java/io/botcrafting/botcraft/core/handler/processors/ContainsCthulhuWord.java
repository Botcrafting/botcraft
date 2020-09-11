package io.botcrafting.botcraft.core.handler.processors;

import static io.botcrafting.botcraft.configuration.constant.CommandConstant.COMMAND_HELP_EXCEPTION;
import static io.botcrafting.botcraft.configuration.constant.CommandConstant.EASTER_EGG_CTHULHU_AWAKE;
import static io.botcrafting.botcraft.configuration.constant.CommandConstant.EASTER_EGG_HELLO_WORLD;
import static io.botcrafting.botcraft.configuration.constant.MessageConstant.WORD_CTHULHU;
import static io.botcrafting.botcraft.configuration.constant.UrlConstant.BOTCRAFT_API_BASE_IMAGES_URL;

import io.botcrafting.botcraft.core.model.Message;
import io.botcrafting.botcraft.core.service.BookService;
import io.botcrafting.botcraft.core.service.MessageSenderService;

public class ContainsCthulhuWord extends MessageProcessor{

	public ContainsCthulhuWord(Message message, MessageSenderService msgService, BookService bookService) {
		super(message, msgService, bookService);
	}

	@Override
	public void processMessage() {
		if (!text.equals(EASTER_EGG_CTHULHU_AWAKE) && !text.contains(COMMAND_HELP_EXCEPTION) && !text.contains("/")
                && (text.equals(EASTER_EGG_HELLO_WORLD) || text.contains(WORD_CTHULHU))) {
			msgService.sendGif(message.getChatId(), String.format("%s%s", BOTCRAFT_API_BASE_IMAGES_URL, "cthulhu.gif.mp4"));
			return;
		}
		nextProcessor.processMessage();
	}

}
