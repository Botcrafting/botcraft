package io.botcrafting.botcraft.infra.googlebooks.inbound;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GoogleBooksVolumes {
    private @JsonProperty("totalItems") int totalItems;
    private @JsonProperty("items") ArrayList<GoogleBooksItem> itemList;
}
