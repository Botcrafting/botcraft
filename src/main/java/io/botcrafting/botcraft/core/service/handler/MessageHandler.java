package io.botcrafting.botcraft.core.service.handler;

import static io.botcrafting.botcraft.configuration.constant.CommandConstant.COMMAND_EASTER_EGG_LIST;
import static io.botcrafting.botcraft.configuration.constant.CommandConstant.COMMAND_HELP;
import static io.botcrafting.botcraft.configuration.constant.CommandConstant.COMMAND_HELP_EXCEPTION;
import static io.botcrafting.botcraft.configuration.constant.CommandConstant.COMMAND_SEARCH_BOOK;
import static io.botcrafting.botcraft.configuration.constant.CommandConstant.EASTER_EGG_CTHULHU_AWAKE;
import static io.botcrafting.botcraft.configuration.constant.CommandConstant.EASTER_EGG_HELLO_WORLD;
import static io.botcrafting.botcraft.configuration.constant.MessageConstant.ANSWER_BOOK_FOUND;
import static io.botcrafting.botcraft.configuration.constant.MessageConstant.ANSWER_BOTCRAFT_MENTION;
import static io.botcrafting.botcraft.configuration.constant.MessageConstant.ANSWER_EMPTY_SEARCH;
import static io.botcrafting.botcraft.configuration.constant.MessageConstant.ANSWER_IS_GOING_TO_RAIN;
import static io.botcrafting.botcraft.configuration.constant.MessageConstant.ANSWER_NO_BOOK_FOUND;
import static io.botcrafting.botcraft.configuration.constant.MessageConstant.ANSWER_STEPHEN_KING;
import static io.botcrafting.botcraft.configuration.constant.MessageConstant.ANSWER_WHERE_TO_GO;
import static io.botcrafting.botcraft.configuration.constant.MessageConstant.BOTCRAFT_NAME;
import static io.botcrafting.botcraft.configuration.constant.MessageConstant.CTHULHU_AWAKEN;
import static io.botcrafting.botcraft.configuration.constant.MessageConstant.DESCRIPTION_BOOK;
import static io.botcrafting.botcraft.configuration.constant.MessageConstant.EASTER_EGG_SHOW_COMMANDS;
import static io.botcrafting.botcraft.configuration.constant.MessageConstant.HELP_SHOW_COMMANDS;
import static io.botcrafting.botcraft.configuration.constant.MessageConstant.QUESTION_IS_GOING_TO_RAIN;
import static io.botcrafting.botcraft.configuration.constant.MessageConstant.QUESTION_STEPHEN_KING;
import static io.botcrafting.botcraft.configuration.constant.MessageConstant.QUESTION_WHERE_TO_GO;
import static io.botcrafting.botcraft.configuration.constant.MessageConstant.WORLD_CTHULHU;
import static io.botcrafting.botcraft.configuration.constant.UrlConstant.BOTCRAFT_API_BASE_IMAGES_URL;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.botcrafting.botcraft.configuration.constant.MessageConstant;
import io.botcrafting.botcraft.core.model.Book;
import io.botcrafting.botcraft.core.model.Update;
import io.botcrafting.botcraft.core.service.BookService;
import io.botcrafting.botcraft.core.service.MessageSenderService;
import io.botcrafting.botcraft.infra.service.api.telegram.TelegramApi;
import io.botcrafting.botcraft.infra.telegram.outbound.TelegramMessagePhoto;
import io.botcrafting.botcraft.infra.telegram.outbound.TelegramMessageText;
import io.botcrafting.botcraft.infra.telegram.outbound.TelegramMessageVideo;

@Service
public class MessageHandler {

    @Autowired(required = false)
    private TelegramApi telegramApi;
    
    @Autowired(required = false)
    private MessageSenderService messageSender;

    @Autowired(required = false)
    private BookService bookService;

    public void handle(Update telegramUpdate) {
        long chatId = telegramUpdate.getMessage().getChat().getId();
        String fullName = telegramUpdate.getMessage().getUser().getFirstName() + " " +  telegramUpdate.getMessage().getUser().getLastName();
        String message = telegramUpdate.getMessage().getText().toLowerCase();
        if (message.startsWith("/")) {
            handleCommand(chatId, fullName, message);
        } else {
            handleOthers(chatId, fullName, message);
        }
    }

    private void handleCommand(long chatId, String fullName, String message) {
        if (message.contains(COMMAND_HELP)) {
            sendMessageText(chatId, String.format(HELP_SHOW_COMMANDS, fullName));
        } else if (message.contains(COMMAND_SEARCH_BOOK)) {
            searchBook(chatId, fullName, message);
        } else if (message.equals(COMMAND_EASTER_EGG_LIST)) {
            sendMessageText(chatId, String.format(EASTER_EGG_SHOW_COMMANDS, fullName));
        }
    }

    private void handleOthers(long chatId, String fullName, String message) {
        if (!message.equals(EASTER_EGG_CTHULHU_AWAKE) && !message.contains(COMMAND_HELP_EXCEPTION) && !message.contains("/")
                && (message.equals(EASTER_EGG_HELLO_WORLD) || message.contains(WORLD_CTHULHU))) {
            sendCthulhuGif(chatId);
        } else if (message.equals(EASTER_EGG_CTHULHU_AWAKE)) {
            sendCthulhuGif(chatId);
            sendMessageText(chatId, CTHULHU_AWAKEN);
        } else if (message.contains(BOTCRAFT_NAME)) {
            if (message.contains(QUESTION_IS_GOING_TO_RAIN)) {
                sendInternalPhoto(chatId, fullName, "rain_today.jpg", ANSWER_IS_GOING_TO_RAIN);
            } else if(message.contains(QUESTION_WHERE_TO_GO)) {
                sendInternalPhoto(chatId, fullName, "sk_wheretogo.jpg", ANSWER_WHERE_TO_GO);
            } else if(message.contains(QUESTION_STEPHEN_KING)) {
                sendInternalPhoto(chatId, fullName, "sk_works.jpg", ANSWER_STEPHEN_KING);
            } else {
                if (!message.contains("/")) {
                    sendMessageText(chatId, String.format(ANSWER_BOTCRAFT_MENTION, fullName));
                }
            }
        }
    }

    private void searchBook(long chatId, String fullName, String message) {
        String searchText = message.replace(COMMAND_SEARCH_BOOK, "").replace("-", "");
        if (!searchText.isBlank() && !searchText.isEmpty() && !searchText.equals(" ")) {
            try {
            		Optional<Book> foundBook = bookService.searchBook(chatId, fullName, message, searchText);
	            	StringBuilder bookMessage = new StringBuilder();
	            	if(!foundBook.isEmpty()) {
	            	Book book = foundBook.get();
	                bookMessage.append(String.format(MessageConstant.TITLE_BOOK, book.getTitle()));
	                bookMessage.append(String.format(MessageConstant.AUTHOR_BOOK, book.getAuthor()));
	                bookMessage.append(String.format(MessageConstant.PUBLISHER_BOOK, book.getPublisher()));
	                bookMessage.append(String.format(MessageConstant.PUBLISH_DATE_BOOK, book.getPublishDate()));
	                bookMessage.append(String.format(MessageConstant.ISBN13_BOOK, book.getIsbn13()));
	                bookMessage.append(String.format(MessageConstant.ISBN10_BOOK, book.getIsbn10()));
	                sendMessageText(chatId, String.format(ANSWER_BOOK_FOUND, fullName));
                    sendPhoto(chatId, book.getImageUrl(), bookMessage.toString());
                    sendMessageText(chatId, String.format(DESCRIPTION_BOOK, book.getDescription()));
                } else {
                    sendMessageText(chatId, String.format(ANSWER_NO_BOOK_FOUND, fullName));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                sendMessageText(chatId, String.format(MessageConstant.ANSWER_ERROR_SEARCH_BOOK, fullName));
            }
        } else {
            sendMessageText(chatId, String.format(ANSWER_EMPTY_SEARCH, fullName));
        }
    }

    private void sendMessageText(long chatId, String cthulhuAwaken) {
        telegramApi.sendMessageText(new TelegramMessageText(chatId, cthulhuAwaken));
    }

    private void sendInternalPhoto(long chatId, String fullName, String fileName, String messageText) {
        sendPhoto(chatId, String.format("%s%s", BOTCRAFT_API_BASE_IMAGES_URL, fileName), String.format(messageText, fullName));
    }

    private void sendPhoto(long chatId, String imageUrl, String messageText) {
        telegramApi.sendMessagePhoto(new TelegramMessagePhoto(chatId, imageUrl, messageText));
    }

    private void sendCthulhuGif(long chatId) {
        telegramApi.sendMessageVideo(
                new TelegramMessageVideo(
                        chatId,
                        String.format("%s%s", BOTCRAFT_API_BASE_IMAGES_URL, "cthulhu.gif.mp4")
                )
        );
    }
}
