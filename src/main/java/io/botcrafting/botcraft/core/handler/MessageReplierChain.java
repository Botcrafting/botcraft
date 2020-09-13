package io.botcrafting.botcraft.core.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.botcrafting.botcraft.core.model.Message;

@Service
public class MessageReplierChain {
	
	@Autowired
	MessageChain chain;

    public void handle(Message message) {
        chain.processMessage(message);
    }
}
