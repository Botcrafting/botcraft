package io.botcrafting.botcraft.infra.googlebooks.inbound;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GoogleBooksIndustryIdentifier {
    private @JsonProperty("type") String type;
    private @JsonProperty("identifier") String identifier;
}
