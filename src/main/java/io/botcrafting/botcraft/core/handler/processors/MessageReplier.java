package io.botcrafting.botcraft.core.handler.processors;

import io.botcrafting.botcraft.core.model.Message;

public interface MessageReplier {
	boolean processMessage(Message message);
}