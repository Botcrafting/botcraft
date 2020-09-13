package io.botcrafting.botcraft.core.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import io.botcrafting.botcraft.core.handler.processors.MessageReplier;
import io.botcrafting.botcraft.core.model.Message;

@Component
public class MessageReplierChain {
	private List<MessageReplier> processors =  new ArrayList<>();
	
	public void registerProcessor(MessageReplier processor) {
		this.processors.add(processor);
	}
	
	public void processMessage(Message message) {
		for(MessageReplier processor : processors) {
			if(processor.processMessage(message)) {
				break;
			}
				
		}
	}
}
