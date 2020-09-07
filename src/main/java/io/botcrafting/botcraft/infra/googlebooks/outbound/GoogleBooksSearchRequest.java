package io.botcrafting.botcraft.infra.googlebooks.outbound;

import static io.botcrafting.botcraft.configuration.constant.ValueConstant.VALUE_LANGUAGE_RESTRICT_PORTUGUESE;
import static io.botcrafting.botcraft.configuration.constant.ValueConstant.VALUE_PRINT_TYPE_BOOKS;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GoogleBooksSearchRequest {
    private @JsonProperty("q") final String query;
    private @JsonProperty("maxResults") final int maxResults;
    private @JsonProperty("langRestrict") final String langRestrict;
    private @JsonProperty("printType") final String printType;

    public GoogleBooksSearchRequest(String query) {
        this.query = query;
        this.langRestrict = VALUE_LANGUAGE_RESTRICT_PORTUGUESE;
        this.maxResults = 1;
        this.printType = VALUE_PRINT_TYPE_BOOKS;
    }
}
