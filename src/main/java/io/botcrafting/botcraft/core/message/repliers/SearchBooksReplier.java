package io.botcrafting.botcraft.core.message.repliers;

import static io.botcrafting.botcraft.configuration.constant.CommandConstant.COMMAND_SEARCH_BOOK;
import static io.botcrafting.botcraft.configuration.constant.MessageConstant.ANSWER_BOOK_FOUND;
import static io.botcrafting.botcraft.configuration.constant.MessageConstant.ANSWER_EMPTY_SEARCH;
import static io.botcrafting.botcraft.configuration.constant.MessageConstant.ANSWER_NO_BOOK_FOUND;
import static io.botcrafting.botcraft.configuration.constant.MessageConstant.DESCRIPTION_BOOK;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import io.botcrafting.botcraft.configuration.constant.MessageConstant;
import io.botcrafting.botcraft.core.message.MessageReplier;
import io.botcrafting.botcraft.core.model.Book;
import io.botcrafting.botcraft.core.model.Message;
import io.botcrafting.botcraft.core.service.BookService;
import io.botcrafting.botcraft.core.service.MessageSenderService;

@Component
@Order(12)
public class SearchBooksReplier implements MessageReplier{

	@Autowired
	private MessageSenderService msgService;
	
	@Autowired
	private BookService bookService;
	
	@Override
	public boolean processMessage(Message message) {
		if(message.getLoweredText().startsWith("/") && message.getLoweredText().contains(COMMAND_SEARCH_BOOK)) {
			processBookComand(message);
			return true;
		}
		return false;
	}
	
	private void processBookComand(Message message) {
        String searchText = message.getText().replace(COMMAND_SEARCH_BOOK, "").replace("-", "");
        if(searchText.isBlank() || searchText.isEmpty() || searchText.equals(" ")) {
        	handleEmptyCommand(message);
        	return;
        }
        if (!searchText.isBlank() && !searchText.isEmpty() && !searchText.equals(" ")) {
            try {
            		Optional<Book> foundBook = searchBook(message.getChatId(), message.getFullName(), searchText);
	            	if(!foundBook.isEmpty()) {
		            	String bookAnswerMessage = createBookAnswerMessage(foundBook.get());
		                msgService.sendMessageText(message.getChatId(), String.format(ANSWER_BOOK_FOUND, message.getFullName()));
	                    msgService.sendPhoto(message.getChatId(), foundBook.get().getImageUrl(), bookAnswerMessage);
	                    msgService.sendMessageText(message.getChatId(), String.format(DESCRIPTION_BOOK, foundBook.get().getDescription()));
	                    return;
	                } 
	            	handleBookNotFound(message);
            } catch (Exception ex) {
                ex.printStackTrace();
                msgService.sendMessageText(message.getChatId(), String.format(MessageConstant.ANSWER_ERROR_SEARCH_BOOK, message.getFullName()));
            }
        }
    }
	
	private void handleEmptyCommand(Message message) {
		msgService.sendMessageText(message.getChatId(), String.format(ANSWER_EMPTY_SEARCH, message.getFullName()));
	}
	
	private void handleBookNotFound(Message message) {
		msgService.sendMessageText(message.getChatId(), String.format(ANSWER_NO_BOOK_FOUND, message.getFullName()));
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
