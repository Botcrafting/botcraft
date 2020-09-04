package io.botcrafting.botcraft.core.service.handler;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class CommandMessageProcessor {
	public CommandMessageProcessor nextProcessor;
	
	public abstract boolean processMessage(String message);
}
