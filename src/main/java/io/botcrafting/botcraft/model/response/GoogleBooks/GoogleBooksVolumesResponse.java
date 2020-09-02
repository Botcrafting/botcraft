package io.botcrafting.botcraft.model.response.GoogleBooks;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GoogleBooksVolumesResponse {
    private @JsonProperty("totalItems") int totalItems;
    private @JsonProperty("items") ArrayList<GoogleBooksItemResponse> itemList;
}
