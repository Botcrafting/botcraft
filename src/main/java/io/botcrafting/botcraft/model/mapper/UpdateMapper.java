package io.botcrafting.botcraft.model.mapper;

import io.botcrafting.botcraft.model.Message;
import io.botcrafting.botcraft.model.Update;
import io.botcrafting.botcraft.model.response.UpdateResponse;
import io.botcrafting.botcraft.model.response.MessageResponse;

public class UpdateMapper {
    public static Update map(UpdateResponse response) {
        Update update = new Update();
        update.setId(response.getUpdateId());
        update.setMessage((response.getMessage() != null) ? MessageMapper.map(response.getMessage()) : MessageMapper.map(new MessageResponse()));
        return update;
    }
}
