package io.botcrafting.botcraft.core.handler.chain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import io.botcrafting.botcraft.core.handler.processors.MessageProcessor;
import io.botcrafting.botcraft.core.model.Message;

@Component
public class MessageChain {
	private List<MessageProcessor> processors =  new ArrayList<>();
	
	public void registerProcessor(MessageProcessor processor) {
		this.processors.add(processor);
	}
	
	public void processMessage(Message message) {
		for(MessageProcessor processor : processors) {
			if(processor.processMessage(message)) {
				break;
			}
				
		}
	}
}
