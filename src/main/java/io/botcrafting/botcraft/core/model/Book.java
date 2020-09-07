package io.botcrafting.botcraft.core.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Book {
    private String id;
    private String title;
    private String description;
    private String isbn13;
    private String isbn10;
    private String imageUrl;
    private String author;
    private String publisher;
    private String publishDate;
}
