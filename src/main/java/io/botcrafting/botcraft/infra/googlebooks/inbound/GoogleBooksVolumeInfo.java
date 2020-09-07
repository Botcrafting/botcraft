package io.botcrafting.botcraft.infra.googlebooks.inbound;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GoogleBooksVolumeInfo {
    private @JsonProperty("title") String title;
    private @JsonProperty("publisher") String publisher;
    private @JsonProperty("publishedDate") String publishedDate;
    private @JsonProperty("description") String description;
    private @JsonProperty("authors") ArrayList<String> authorList;
    private @JsonProperty("industryIdentifiers") ArrayList<GoogleBooksIndustryIdentifier> isbnList;
    private @JsonProperty("pageCount") int pageCount;
    private @JsonProperty("imageLinks") GoogleBooksImageLink imageLink;
}
