package io.botcrafting.botcraft.core.service;

public interface MessageSenderService {
	public void sendMessageText(long chatId, String messageText);
	public void sendPhoto(long chatId, String imageUrl, String messageText);
	public void sendInternalPhoto(long chatId, String fullName, String fileName, String messageText);
	public void sendGif(long chatId, String gifUrl);
}
