package io.botcrafting.botcraft.model.service.api;

import io.botcrafting.botcraft.model.request.GoogleBooksSearchRequest;
import io.botcrafting.botcraft.model.response.GoogleBooks.GoogleBooksVolumesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import static io.botcrafting.botcraft.configuration.constant.UrlConstant.GOOGLE_BOOKS_API_BASE_URL;

@Service
public class GoogleBooksApi {

    @Autowired
    private RestTemplate restTemplate;

    public GoogleBooksVolumesResponse searchBook(GoogleBooksSearchRequest request) {
        String url = GOOGLE_BOOKS_API_BASE_URL + String.format("/volumes?q=%s&maxResults=%d&langRestrict=%s&printType=%s",
                request.getQuery(),
                request.getMaxResults(),
                request.getLangRestrict(),
                request.getPrintType()
        );
        System.out.println("I'm calling rest template on: " + url);
        return restTemplate.getForObject(url, GoogleBooksVolumesResponse.class);
    }
}