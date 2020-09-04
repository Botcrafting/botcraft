package io.botcrafting.botcraft.core.service.handler;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class CommonMessageProcessor {
	public CommonMessageProcessor nextProcessor;
	
	public abstract boolean processMessage(String message);
}
