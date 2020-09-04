package io.botcrafting.botcraft.core.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Message {
    private User user;
    private Chat chat;
    private int id;
    private int date;
    private String text;
}
