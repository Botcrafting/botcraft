package io.botcrafting.botcraft.infra.api.googlebooks;

import static io.botcrafting.botcraft.configuration.constant.UrlConstant.GOOGLE_BOOKS_API_BASE_URL;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.botcrafting.botcraft.core.model.Book;
import io.botcrafting.botcraft.core.service.BookService;
import io.botcrafting.botcraft.infra.googlebooks.inbound.GoogleBooksVolumes;
import io.botcrafting.botcraft.infra.googlebooks.mapper.BookMapper;
import io.botcrafting.botcraft.infra.googlebooks.outbound.GoogleBooksSearchRequest;

@Service
public class GoogleBooksApi implements BookService{

    @Autowired
    private RestTemplate restTemplate;


	@Override
	public Optional<Book> searchBook(long chatId, String fullName, String searchText) {
		Book book = null;
		GoogleBooksVolumes response = searchBookAtGoogleApi(new GoogleBooksSearchRequest(searchText));
        if (response != null && response.getItemList() != null && response.getItemList().size() > 0) {
            book = BookMapper.map(response.getItemList().get(0));
        }
        return Optional.of(book);
	}
	
	public GoogleBooksVolumes searchBookAtGoogleApi(GoogleBooksSearchRequest request) {
        String url = GOOGLE_BOOKS_API_BASE_URL + String.format("/volumes?q=%s&maxResults=%d&langRestrict=%s&printType=%s",
                request.getQuery(),
                request.getMaxResults(),
                request.getLangRestrict(),
                request.getPrintType()
        );
        System.out.println("I'm calling rest template on: " + url);
        return restTemplate.getForObject(url, GoogleBooksVolumes.class);
    }
}