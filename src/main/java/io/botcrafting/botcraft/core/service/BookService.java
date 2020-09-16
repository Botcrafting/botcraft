package io.botcrafting.botcraft.core.service;

import java.util.Optional;
import io.botcrafting.botcraft.core.model.Book;

public interface BookService {
	Optional<Book> searchBook(long chatId, String fullName, String searchText);
}
