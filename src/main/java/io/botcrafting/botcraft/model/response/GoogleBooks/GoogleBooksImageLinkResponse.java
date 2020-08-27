package io.botcrafting.botcraft.model.response.GoogleBooks;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GoogleBooksImageLinkResponse {
    private @JsonProperty("thumbnail") String thumbnail;

    public GoogleBooksImageLinkResponse() { }

    public String getThumbnail() {
        return thumbnail;
    }
}
