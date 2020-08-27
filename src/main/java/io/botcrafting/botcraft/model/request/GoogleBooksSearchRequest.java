package io.botcrafting.botcraft.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import static io.botcrafting.botcraft.configuration.constant.ValueConstant.VALUE_LANGUAGE_RESTRICT_PORTUGUESE;
import static io.botcrafting.botcraft.configuration.constant.ValueConstant.VALUE_PRINT_TYPE_BOOKS;

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

    public String getQuery() {
        return query;
    }

    public int getMaxResults() {
        return maxResults;
    }

    public String getLangRestrict() {
        return langRestrict;
    }

    public String getPrintType() {
        return printType;
    }
}
