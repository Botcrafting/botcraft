package io.botcrafting.botcraft.model.response.GoogleBooks;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GoogleBooksIndustryIdentifierResponse {
    private @JsonProperty("type") String type;
    private @JsonProperty("identifier") String identifier;
}
