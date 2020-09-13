package io.botcrafting.botcraft.core.handler.processors;

import io.botcrafting.botcraft.core.model.Message;
import io.botcrafting.botcraft.core.service.BookService;
import io.botcrafting.botcraft.core.service.MessageSenderService;

public interface MessageReplier {

	public boolean processMessage(Message message);
}
