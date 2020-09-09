package io.botcrafting.botcraft.core.handler;

import static io.botcrafting.botcraft.configuration.constant.CommandConstant.COMMAND_EASTER_EGG_LIST;
import static io.botcrafting.botcraft.configuration.constant.CommandConstant.COMMAND_HELP;
import static io.botcrafting.botcraft.configuration.constant.CommandConstant.COMMAND_HELP_EXCEPTION;
import static io.botcrafting.botcraft.configuration.constant.CommandConstant.COMMAND_SEARCH_BOOK;
import static io.botcrafting.botcraft.configuration.constant.CommandConstant.EASTER_EGG_CTHULHU_AWAKE;
import static io.botcrafting.botcraft.configuration.constant.CommandConstant.EASTER_EGG_HELLO_WORLD;
import static io.botcrafting.botcraft.configuration.constant.MessageConstant.*;
import static io.botcrafting.botcraft.configuration.constant.UrlConstant.BOTCRAFT_API_BASE_IMAGES_URL;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.botcrafting.botcraft.configuration.constant.MessageConstant;
import io.botcrafting.botcraft.core.model.Book;
import io.botcrafting.botcraft.core.model.Message;
import io.botcrafting.botcraft.core.service.BookService;
import io.botcrafting.botcraft.core.service.MessageSenderService;

@Service
public class MessageHandler {
    
    @Autowired(required = false)
    private MessageSenderService messageSender;

    @Autowired(required = false)
    private BookService bookService;

    public void handle(Message message) {
        long chatId = message.getChatId();
        String fullName = message.getUser().getFirstName() + " " +  message.getUser().getLastName();
        String text = message.getText().toLowerCase();
        if (text.startsWith("/")) {
            handleCommand(chatId, fullName, text);
        } else {
            handleOthers(chatId, fullName, text);
        }
    }

    private void handleCommand(long chatId, String fullName, String message) {
        if (message.contains(COMMAND_HELP)) {
        	messageSender.sendMessageText(chatId, String.format(HELP_SHOW_COMMANDS, fullName));
        } else if (message.contains(COMMAND_SEARCH_BOOK)) {
            searchBook(chatId, fullName, message);
        } else if (message.equals(COMMAND_EASTER_EGG_LIST)) {
        	messageSender.sendMessageText(chatId, String.format(EASTER_EGG_SHOW_COMMANDS, fullName));
        }
    }

    private void handleOthers(long chatId, String fullName, String message) {
        if (!message.equals(EASTER_EGG_CTHULHU_AWAKE) && !message.contains(COMMAND_HELP_EXCEPTION) && !message.contains("/")
                && (message.equals(EASTER_EGG_HELLO_WORLD) || message.contains(WORD_CTHULHU))) {
        	messageSender.sendGif(chatId, String.format("%s%s", BOTCRAFT_API_BASE_IMAGES_URL, "cthulhu.gif.mp4"));
        } else if (message.equals(EASTER_EGG_CTHULHU_AWAKE)) {
        	messageSender.sendGif(chatId, String.format("%s%s", BOTCRAFT_API_BASE_IMAGES_URL, "cthulhu.gif.mp4"));
        	messageSender.sendMessageText(chatId, CTHULHU_AWAKEN);
        } else{
            if (message.contains(BOTCRAFT_NAME)) {
                handleBotMention(chatId, fullName, message);
            }
        }
    }

    private void handleBotMention(long chatId, String fullName, String message) {
        if (message.contains(QUESTION_IS_GOING_TO_RAIN)) {
            messageSender.sendPhoto(chatId, String.format("%s%s", BOTCRAFT_API_BASE_IMAGES_URL, "rain_today.jpg"), String.format(ANSWER_IS_GOING_TO_RAIN, fullName));
        } else if (message.contains(QUESTION_WHERE_TO_GO)) {
            messageSender.sendPhoto(chatId, String.format("%s%s", BOTCRAFT_API_BASE_IMAGES_URL, "sk_wheretogo.jpg"), String.format(ANSWER_WHERE_TO_GO, fullName));
        } else if (message.contains(QUESTION_STEPHEN_KING)) {
            messageSender.sendPhoto(chatId, String.format("%s%s", BOTCRAFT_API_BASE_IMAGES_URL, "sk_works.jpg"), String.format(ANSWER_STEPHEN_KING, fullName));
        } else {
            if (!message.contains("/")) {
                messageSender.sendMessageText(chatId, String.format(ANSWER_BOTCRAFT_MENTION, fullName));
            }
        }
    }

    private void searchBook(long chatId, String fullName, String message) {
        String searchText = message.replace(COMMAND_SEARCH_BOOK, "").replace("-", "");
        if (!searchText.isBlank() && !searchText.isEmpty() && !searchText.equals(" ")) {
            try {
                Optional<Book> foundBook = bookService.searchBook(chatId, fullName, searchText);
                StringBuilder bookMessage = new StringBuilder();
                if(foundBook.isPresent()) {
                    Book book = foundBook.get();
                    bookMessage.append(String.format(MessageConstant.TITLE_BOOK, book.getTitle()));
                    bookMessage.append(String.format(MessageConstant.AUTHOR_BOOK, book.getAuthor()));
                    bookMessage.append(String.format(MessageConstant.PUBLISHER_BOOK, book.getPublisher()));
                    bookMessage.append(String.format(MessageConstant.PUBLISH_DATE_BOOK, book.getPublishDate()));
                    bookMessage.append(String.format(MessageConstant.ISBN13_BOOK, book.getIsbn13()));
                    bookMessage.append(String.format(MessageConstant.ISBN10_BOOK, book.getIsbn10()));
                    messageSender.sendMessageText(chatId, String.format(ANSWER_BOOK_FOUND, fullName));
                    messageSender.sendPhoto(chatId, book.getImageUrl(), bookMessage.toString());
                    messageSender.sendMessageText(chatId, String.format(DESCRIPTION_BOOK, book.getDescription()));
                } else {
                    messageSender.sendMessageText(chatId, String.format(ANSWER_NO_BOOK_FOUND, fullName));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                messageSender.sendMessageText(chatId, String.format(MessageConstant.ANSWER_ERROR_SEARCH_BOOK, fullName));
            }
        } else {
            messageSender.sendMessageText(chatId, String.format(ANSWER_EMPTY_SEARCH, fullName));
        }
    }
}
