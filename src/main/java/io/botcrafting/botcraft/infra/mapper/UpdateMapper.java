package io.botcrafting.botcraft.infra.mapper;

import io.botcrafting.botcraft.core.model.Message;
import io.botcrafting.botcraft.core.model.Update;
import io.botcrafting.botcraft.infra.telegram.inbound.TelegramMessage;
import io.botcrafting.botcraft.infra.telegram.inbound.TelegramUpdateReceived;

public class UpdateMapper {
    public static Update map(TelegramUpdateReceived telegramReceivedUpdate) {
        Update update = new Update();
        update.setId(telegramReceivedUpdate.getUpdateId());
        update.setMessage((telegramReceivedUpdate.getReceivedMessage() != null) ? MessageMapper.map(telegramReceivedUpdate.getReceivedMessage()) : MessageMapper.map(new TelegramMessage()));
        return update;
    }
}
