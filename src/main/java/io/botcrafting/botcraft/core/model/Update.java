package io.botcrafting.botcraft.core.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Update {
    private int id;
    private Message message;
}
