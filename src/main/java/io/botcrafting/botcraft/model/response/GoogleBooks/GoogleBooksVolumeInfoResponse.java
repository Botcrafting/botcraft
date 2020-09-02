package io.botcrafting.botcraft.model.response.GoogleBooks;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GoogleBooksVolumeInfoResponse {
    private @JsonProperty("title") String title;
    private @JsonProperty("publisher") String publisher;
    private @JsonProperty("publishedDate") String publishedDate;
    private @JsonProperty("description") String description;
    private @JsonProperty("authors") ArrayList<String> authorList;
    private @JsonProperty("industryIdentifiers") ArrayList<GoogleBooksIndustryIdentifierResponse> isbnList;
    private @JsonProperty("pageCount") int pageCount;
    private @JsonProperty("imageLinks") GoogleBooksImageLinkResponse imageLink;
}
