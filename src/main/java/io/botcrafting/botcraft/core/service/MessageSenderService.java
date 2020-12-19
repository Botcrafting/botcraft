package io.botcrafting.botcraft.core.service;

public interface MessageSenderService {
	void sendMessageText(long chatId, String messageText);
	void sendPhoto(long chatId, String imageUrl, String messageText);
	void sendGif(long chatId, String gifUrl);
}
