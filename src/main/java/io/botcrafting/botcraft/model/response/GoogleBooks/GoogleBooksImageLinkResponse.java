package io.botcrafting.botcraft.model.response.GoogleBooks;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GoogleBooksImageLinkResponse {
    private @JsonProperty("thumbnail") String thumbnail;
}
