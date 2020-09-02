package io.botcrafting.botcraft.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Update {
    private int id;
    private Message message;
}
