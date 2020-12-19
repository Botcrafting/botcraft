package io.botcrafting.botcraft.infra.controller.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.botcrafting.botcraft.core.handler.MessageHandler;
import io.botcrafting.botcraft.infra.telegram.inbound.TelegramMessage;
import io.botcrafting.botcraft.infra.telegram.inbound.TelegramUpdateReceived;

@RestController
public class MessageController {
    @Autowired
    private MessageHandler messageHandler;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public String receiveTelegramUpdate(@RequestBody TelegramUpdateReceived telegramUpdateReceived){
    	if(telegramUpdateReceived.getReceivedMessage() != null) {
    		messageHandler.handle(TelegramMessage.toCoreMessage(telegramUpdateReceived.getReceivedMessage()));
    	}
        return "OK";
    }

    @RequestMapping(value = "/keep-alive" , method = RequestMethod.GET)
    @ResponseBody
    public String receiveKeepAliveRequest(){
        return "OK";
    }
}
