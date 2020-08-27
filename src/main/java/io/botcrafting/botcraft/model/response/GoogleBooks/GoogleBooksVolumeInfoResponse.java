package io.botcrafting.botcraft.model.response.GoogleBooks;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class GoogleBooksVolumeInfoResponse {
    private @JsonProperty("title") String title;
    private @JsonProperty("publisher") String publisher;
    private @JsonProperty("publishedDate") String publishedDate;
    private @JsonProperty("description") String description;
    private @JsonProperty("authors") ArrayList<String> authorList;
    private @JsonProperty("industryIdentifiers") ArrayList<GoogleBooksIndustryIdentifierResponse> isbnList;
    private @JsonProperty("pageCount") int pageCount;
    private @JsonProperty("imageLinks") GoogleBooksImageLinkResponse imageLink;

    public GoogleBooksVolumeInfoResponse() { }

    public String getTitle() {
        return title;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<String> getAuthorList() {
        return authorList;
    }

    public ArrayList<GoogleBooksIndustryIdentifierResponse> getIsbnList() {
        return isbnList;
    }

    public GoogleBooksImageLinkResponse getImageLink() {
        return imageLink;
    }
}
