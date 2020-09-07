package io.botcrafting.botcraft.infra.googlebooks.inbound;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GoogleBooksItem {
    private @JsonProperty("id") String id;
    private @JsonProperty("volumeInfo") GoogleBooksVolumeInfo book;
}
