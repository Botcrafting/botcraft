package io.botcrafting.botcraft.controller.message;

import io.botcrafting.botcraft.model.Update;
import io.botcrafting.botcraft.model.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public String receiveTelegramUpdate(@RequestBody Update telegramUpdate){
        messageService.handle(telegramUpdate);
        return "OK";
    }

    @RequestMapping(value = "/keep-alive" , method = RequestMethod.POST)
    @ResponseBody
    public String receiveKeepAliveRequest(){
        return "OK";
    }
}
