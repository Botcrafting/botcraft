package io.botcrafting.botcraft.controller.message;

import io.botcrafting.botcraft.model.mapper.UpdateMapper;
import io.botcrafting.botcraft.model.response.UpdateResponse;
import io.botcrafting.botcraft.model.service.handler.MessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MessageController {

    @Autowired
    private MessageHandler messageHandler;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public String receiveTelegramUpdate(@RequestBody UpdateResponse telegramUpdateResponse){
        messageHandler.handle(UpdateMapper.map(telegramUpdateResponse));
        return "OK";
    }

    @RequestMapping(value = "/keep-alive" , method = RequestMethod.GET)
    @ResponseBody
    public String receiveKeepAliveRequest(){
        return "OK";
    }
}
