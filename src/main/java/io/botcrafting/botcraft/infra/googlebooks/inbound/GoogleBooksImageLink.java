package io.botcrafting.botcraft.infra.googlebooks.inbound;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GoogleBooksImageLink {
    private @JsonProperty("thumbnail") String thumbnail;
}
