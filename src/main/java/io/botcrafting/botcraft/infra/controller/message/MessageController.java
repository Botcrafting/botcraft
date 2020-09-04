package io.botcrafting.botcraft.infra.controller.message;

import io.botcrafting.botcraft.core.service.handler.MessageHandler;
import io.botcrafting.botcraft.infra.mapper.UpdateMapper;
import io.botcrafting.botcraft.infra.telegram.inbound.TelegramUpdateReceived;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MessageController {

    @Autowired
    private MessageHandler messageHandler;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public String receiveTelegramUpdate(@RequestBody TelegramUpdateReceived telegramUpdateReceived){
        messageHandler.handle(UpdateMapper.map(telegramUpdateReceived));
        return "OK";
    }

    @RequestMapping(value = "/keep-alive" , method = RequestMethod.GET)
    @ResponseBody
    public String receiveKeepAliveRequest(){
        return "OK";
    }
}
