package io.botcrafting.botcraft.model.service.handler;

import io.botcrafting.botcraft.model.Book;
import io.botcrafting.botcraft.model.Update;
import io.botcrafting.botcraft.model.mapper.BookMapper;
import io.botcrafting.botcraft.model.request.GoogleBooksSearchRequest;
import io.botcrafting.botcraft.model.request.TelegramMessagePhotoRequest;
import io.botcrafting.botcraft.model.request.TelegramMessageTextRequest;
import io.botcrafting.botcraft.model.request.TelegramMessageVideoRequest;
import io.botcrafting.botcraft.model.response.GoogleBooks.GoogleBooksVolumesResponse;
import io.botcrafting.botcraft.model.service.api.GoogleBooksApi;
import io.botcrafting.botcraft.model.service.api.TelegramApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static io.botcrafting.botcraft.configuration.constant.CommandConstant.*;
import static io.botcrafting.botcraft.configuration.constant.MessageConstant.*;
import static io.botcrafting.botcraft.configuration.constant.UrlConstant.GIF_CTHULHU;

@Service
public class MessageHandler {

    @Autowired(required = false)
    private TelegramApi telegramApi;

    @Autowired(required = false)
    private GoogleBooksApi googleBooksApi;

    public void handle(Update telegramUpdate) {
        var chatId = telegramUpdate.getMessage().getChat().getId();
        var fullName = telegramUpdate.getMessage().getUser().getFirstName() + " " +  telegramUpdate.getMessage().getUser().getLastName();
        var message = telegramUpdate.getMessage().getText().toLowerCase();
        if (message.contains(COMMAND_HELP)) {
            telegramApi.sendMessageText(new TelegramMessageTextRequest(chatId, CTHULHU_AWAKEN));
        } else if (message.contains(COMMAND_SEARCH_BOOK)) {
            handleSearchBook(chatId, fullName, message);
        } else if (message.equals(EASTER_EGG_HELLO_WORLD) || message.contains(WORLD_CTHULHU) && !message.contains(COMMAND_HELP_EXCEPTION)) {
            telegramApi.sendMessageVideo(new TelegramMessageVideoRequest(chatId, GIF_CTHULHU));
        } else if (message.contains(BOTCRAFT_NAME)) {
            telegramApi.sendMessageText(new TelegramMessageTextRequest(chatId, String.format(ANSWER_BOTCRAFT_MENTION, fullName)));
        }
    }

    private void handleSearchBook(long chatId, String fullName, String message) {
        String searchText = message.replace("/livro ", "");
        if (!searchText.isBlank() && !searchText.isEmpty() && !searchText.equals(" ")) {
            try {
                GoogleBooksVolumesResponse response = googleBooksApi.searchBook(new GoogleBooksSearchRequest(searchText));
                if (response != null && response.getItemList() != null && response.getItemList().size() > 0) {
                    StringBuilder bookMessage = new StringBuilder();
                    Book book = BookMapper.map(response.getItemList().get(0));
                    bookMessage.append(String.format(TITLE_BOOK, book.getTitle()));
                    bookMessage.append(String.format(AUTHOR_BOOK, book.getAuthor()));
                    bookMessage.append(String.format(PUBLISHER_BOOK, book.getPublisher()));
                    bookMessage.append(String.format(PUBLISH_DATE_BOOK, book.getPublishDate()));
                    bookMessage.append(String.format(ISBN13_BOOK, book.getIsbn13()));
                    bookMessage.append(String.format(ISBN10_BOOK, book.getIsbn10()));
                    telegramApi.sendMessageText(new TelegramMessageTextRequest(chatId, String.format(ANSWER_BOOK_FOUND, fullName)));
                    telegramApi.sendMessagePhoto(new TelegramMessagePhotoRequest(chatId, book.getImageUrl(),bookMessage.toString()));
                    telegramApi.sendMessageText(new TelegramMessageTextRequest(chatId, String.format(DESCRIPTION_BOOK, book.getDescription())));
                } else {
                    telegramApi.sendMessageText(new TelegramMessageTextRequest(chatId, String.format(ANSWER_NO_BOOK_FOUND, fullName)));
                }
            } catch (Exception ex) {
                telegramApi.sendMessageText(new TelegramMessageTextRequest(chatId, String.format(ANSWER_ERROR_SEARCH_BOOK, fullName)));
            }
        } else {
            telegramApi.sendMessageText(new TelegramMessageTextRequest(chatId, String.format(ANSWER_NO_BOOK_FOUND, fullName)));
        }
    }
}
