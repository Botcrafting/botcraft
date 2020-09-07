package io.botcrafting.botcraft.infra.googlebooks.mapper;

import io.botcrafting.botcraft.core.model.Book;
import io.botcrafting.botcraft.infra.googlebooks.inbound.GoogleBooksItem;
import io.botcrafting.botcraft.infra.googlebooks.inbound.GoogleBooksVolumeInfo;

import org.springframework.lang.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static io.botcrafting.botcraft.configuration.constant.UrlConstant.BOTCRAFT_API_BASE_IMAGES_URL;
import static io.botcrafting.botcraft.configuration.constant.ValueConstant.VALUE_TYPE_ISBN_10;
import static io.botcrafting.botcraft.configuration.constant.ValueConstant.VALUE_TYPE_ISBN_13;

public class BookMapper {
    public static Book map(GoogleBooksItem response) {
        Book book = new Book();
        book.setId((response.getId() != null) ? response.getId() : "");
        book.setTitle((response.getBook() != null) ? mapTitle(response.getBook()) : "");
        book.setDescription((response.getBook() != null) ? mapDescription(response.getBook()) : "");
        book.setAuthor((response.getBook() != null) ? mapAuthor(response.getBook()) : "");
        book.setImageUrl((response.getBook() != null) ? mapImageUrl(response.getBook()) : "");
        book.setIsbn10((response.getBook() != null) ? mapIsbn10(response.getBook()) : "");
        book.setIsbn13((response.getBook() != null) ? mapIsbn13(response.getBook()) : "");
        book.setPublisher((response.getBook() != null) ? mapPublisher(response.getBook()) : "");
        book.setPublishDate((response.getBook() != null) ? mapPublishDate(response.getBook()) : "");
        return book;
    }

    @NonNull
    private static String mapPublishDate(GoogleBooksVolumeInfo book) {
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat newFormatDate = new SimpleDateFormat("dd/MM/yyyy");
        String publishDateText = "";
        if (book.getPublishedDate() != null) {
            try {
                Date datePublish = formatDate.parse(book.getPublishedDate());
                publishDateText = newFormatDate.format(datePublish);
            } catch (ParseException e) {
                e.printStackTrace();
                publishDateText = book.getPublishedDate();
            }
        }
        return publishDateText;
    }

    @NonNull
    private static String mapPublisher(GoogleBooksVolumeInfo book) {
        return (book.getPublisher() != null) ? book.getPublisher() : "";
    }

    @NonNull
    private static String mapIsbn10(GoogleBooksVolumeInfo book) {
        String isbn10 = "";
        if (book.getIsbnList() != null) {
            for (int position = 0; position < book.getIsbnList().size(); position++) {
                var currentIsbn = book.getIsbnList().get(position);
                if (currentIsbn.getType() != null) {
                    if (currentIsbn.getType().equals(VALUE_TYPE_ISBN_10)) {
                        isbn10 = (currentIsbn.getIdentifier() != null) ? currentIsbn.getIdentifier() : "";
                        break;
                    }
                }
            }
        }
        return isbn10;
    }

    @NonNull
    private static String mapIsbn13(GoogleBooksVolumeInfo bookResponse) {
        String isbn13 = "";
        if (bookResponse.getIsbnList() != null) {
            for (int position = 0; position < bookResponse.getIsbnList().size(); position++) {
                var currentIsbn = bookResponse.getIsbnList().get(position);
                if (currentIsbn.getType() != null) {
                    if (currentIsbn.getType().equals(VALUE_TYPE_ISBN_13)) {
                        var currentIsbnIdentifier = currentIsbn.getIdentifier();
                        isbn13 = (currentIsbnIdentifier != null) ? formatIsbn13(currentIsbnIdentifier) : "";
                        break;
                    }
                }
            }
        }
        return isbn13;
    }

    @NonNull
    private static String formatIsbn13(String currentIsbnIdentifier) {
        return String.format("%s-%s", currentIsbnIdentifier.substring(0, 3), currentIsbnIdentifier.substring(3));
    }

    @NonNull
    private static String mapImageUrl(GoogleBooksVolumeInfo book) {
        String imageUrl;
        if(book.getImageLink() != null) {
            imageUrl = (book.getImageLink().getThumbnail() != null) ? String.format("%s%s", book.getImageLink().getThumbnail(), "&fife=w600-h800") : "";
        } else {
            imageUrl = String.format("%s%s", BOTCRAFT_API_BASE_IMAGES_URL, "no_image_book.jpeg");
        }
        return imageUrl;
    }

    @NonNull
    private static String mapTitle(GoogleBooksVolumeInfo book) {
        return (book.getTitle() != null) ? book.getTitle() : "";
    }

    @NonNull
    private static String mapDescription(GoogleBooksVolumeInfo book) {
        return (book.getDescription() != null) ? book.getDescription() : "";
    }

    @NonNull
    private static String mapAuthor(GoogleBooksVolumeInfo book) {
        StringBuilder author = new StringBuilder();
        if (book.getAuthorList() != null) {
            for (int position = 0; position < book.getAuthorList().size(); position++) {
                if (position != 0) {
                    author.append(", ");
                }
                author.append(book.getAuthorList().get(position));
            }
        }
        return author.toString();
    }
}
