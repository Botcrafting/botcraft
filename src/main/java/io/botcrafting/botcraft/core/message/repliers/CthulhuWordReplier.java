package io.botcrafting.botcraft.core.message.repliers;

import static io.botcrafting.botcraft.configuration.constant.MessageConstant.WORD_CTHULHU;
import static io.botcrafting.botcraft.configuration.constant.UrlConstant.BOTCRAFT_API_BASE_IMAGES_URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import io.botcrafting.botcraft.core.message.MessageReplier;
import io.botcrafting.botcraft.core.model.Message;
import io.botcrafting.botcraft.core.service.MessageSenderService;

@Component
@DependsOn({"cthulhuAwakePhraseReplier"})
public class CthulhuWordReplier implements MessageReplier{

	@Autowired
	private MessageSenderService service;
	
	@Override
	public boolean processMessage(Message message) {
		if (message.getLoweredText().contains(WORD_CTHULHU)) {
			service.sendGif(message.getChatId(), String.format("%s%s", BOTCRAFT_API_BASE_IMAGES_URL, "cthulhu.gif.mp4"));
			return true;
		}
		return false;
	}

}
