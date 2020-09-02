package io.botcrafting.botcraft.model.response.GoogleBooks;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GoogleBooksItemResponse {
    private @JsonProperty("id") String id;
    private @JsonProperty("volumeInfo") GoogleBooksVolumeInfoResponse book;
}
