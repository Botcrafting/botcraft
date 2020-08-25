package io.botcrafting.botcraft.model.mapper;

import io.botcrafting.botcraft.model.Message;
import io.botcrafting.botcraft.model.Update;
import io.botcrafting.botcraft.model.response.UpdateResponse;

public class UpdateMapper {
    public static Update map(UpdateResponse response) {
        Update update = new Update();
        update.setId(response.getUpdateId());
        update.setMessage((response.getMessage() != null) ? MessageMapper.map(response.getMessage()) : new Message());
        return update;
    }
}
