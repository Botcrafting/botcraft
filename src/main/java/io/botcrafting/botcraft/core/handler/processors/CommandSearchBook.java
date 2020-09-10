package io.botcrafting.botcraft.core.handler.processors;

import io.botcrafting.botcraft.core.model.Book;
import io.botcrafting.botcraft.core.model.Message;
import static io.botcrafting.botcraft.configuration.constant.CommandConstant.COMMAND_SEARCH_BOOK;
import static io.botcrafting.botcraft.configuration.constant.MessageConstant.ANSWER_BOOK_FOUND;
import static io.botcrafting.botcraft.configuration.constant.MessageConstant.ANSWER_EMPTY_SEARCH;
import static io.botcrafting.botcraft.configuration.constant.MessageConstant.ANSWER_NO_BOOK_FOUND;
import static io.botcrafting.botcraft.configuration.constant.MessageConstant.DESCRIPTION_BOOK;

import java.util.Optional;

import io.botcrafting.botcraft.configuration.constant.MessageConstant;

public class CommandSearchBook extends MessageProcessor{

	public CommandSearchBook(Message message) {
		super(message);
	}

	@Override
	public void processMessage() {
		if(message.getText().startsWith("/") && message.getText().contains(COMMAND_SEARCH_BOOK)) {
			processBookComand(message.getChatId(), this.fullName, message.getText());
			return;
		}
		nextProcessor.processMessage();
	}
	
	private void processBookComand(long chatId, String fullName, String message) {
        String searchText = message.replace(COMMAND_SEARCH_BOOK, "").replace("-", "");
        if(searchText.isBlank() || searchText.isEmpty() || searchText.equals(" ")) {
        	handleEmptyCommand();
        	return;
        }
        if (!searchText.isBlank() && !searchText.isEmpty() && !searchText.equals(" ")) {
            try {
            		Optional<Book> foundBook = searchBook(chatId, fullName, searchText);
	            	if(!foundBook.isEmpty()) {
		            	String bookAnswerMessage = createBookAnswerMessage(foundBook.get());
		                msgService.sendMessageText(chatId, String.format(ANSWER_BOOK_FOUND, fullName));
	                    msgService.sendPhoto(chatId, foundBook.get().getImageUrl(), bookAnswerMessage);
	                    msgService.sendMessageText(chatId, String.format(DESCRIPTION_BOOK, foundBook.get().getDescription()));
	                    return;
	                } 
	            	handleBookNotFound();
            } catch (Exception ex) {
                ex.printStackTrace();
                msgService.sendMessageText(chatId, String.format(MessageConstant.ANSWER_ERROR_SEARCH_BOOK, fullName));
            }
        }
    }
	
	private void handleEmptyCommand() {
		msgService.sendMessageText(message.getChatId(), String.format(ANSWER_EMPTY_SEARCH, fullName));
	}
	
	private void handleBookNotFound() {
		msgService.sendMessageText(message.getChatId(), String.format(ANSWER_NO_BOOK_FOUND, fullName));
	}
	
	private Optional<Book> searchBook(long chatId, String fullName, String searchText) {
		return bookService.searchBook(chatId, fullName, searchText);
	}
	
	private String createBookAnswerMessage(Book foundBook) {
		StringBuilder bookMessage = new StringBuilder();
        bookMessage.append(String.format(MessageConstant.TITLE_BOOK, foundBook.getTitle()));
        bookMessage.append(String.format(MessageConstant.AUTHOR_BOOK, foundBook.getAuthor()));
        bookMessage.append(String.format(MessageConstant.PUBLISHER_BOOK, foundBook.getPublisher()));
        bookMessage.append(String.format(MessageConstant.PUBLISH_DATE_BOOK, foundBook.getPublishDate()));
        bookMessage.append(String.format(MessageConstant.ISBN13_BOOK, foundBook.getIsbn13()));
        bookMessage.append(String.format(MessageConstant.ISBN10_BOOK, foundBook.getIsbn10()));
        return bookMessage.toString();

	}

}
