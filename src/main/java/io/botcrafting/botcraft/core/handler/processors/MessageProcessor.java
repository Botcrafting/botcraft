package io.botcrafting.botcraft.core.handler.processors;

import org.springframework.beans.factory.annotation.Autowired;

import io.botcrafting.botcraft.core.model.Message;
import io.botcrafting.botcraft.core.service.BookService;
import io.botcrafting.botcraft.core.service.MessageSenderService;

public abstract class MessageProcessor {
	
	@Autowired
	protected MessageSenderService msgService;
	
	@Autowired
	protected BookService bookService;
	
	protected MessageProcessor nextProcessor;
	protected Message message;
	protected String fullName;
	
	public MessageProcessor(Message message) {
		this.message = message;
		this.fullName = message.getUser().getFirstName() + " " + message.getUser().getLastName();
	}
	
	public abstract void processMessage();
	public void setNextProcessor(MessageProcessor nextProcessor) {
		this.nextProcessor = nextProcessor;
	}
}
