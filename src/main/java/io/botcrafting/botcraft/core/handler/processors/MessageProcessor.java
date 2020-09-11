package io.botcrafting.botcraft.core.handler.processors;

import io.botcrafting.botcraft.core.model.Message;
import io.botcrafting.botcraft.core.service.BookService;
import io.botcrafting.botcraft.core.service.MessageSenderService;

public abstract class MessageProcessor {
	
	protected MessageSenderService msgService;
	protected BookService bookService;
	
	protected MessageProcessor nextProcessor;
	protected Message message;
	protected String fullName;
	protected String text;
	
	public MessageProcessor(Message message, MessageSenderService msgService, BookService bookService) {
		this.message = message;
		this.fullName = message.getUser().getFirstName() + " " + message.getUser().getLastName();
		this.text = message.getText().toLowerCase();
		this.msgService = msgService;
		this.bookService = bookService;
	}
	
	public void processMessage() {};
	public void setNextProcessor(MessageProcessor nextProcessor) {
		this.nextProcessor = nextProcessor;
	}
}
