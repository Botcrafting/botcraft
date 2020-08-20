package io.botcrafting.botcraft.controller.message;

import io.botcrafting.botcraft.model.Update;
import io.botcrafting.botcraft.model.usecase.MessageUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MessageController {
    @Autowired
    private MessageUseCase messageUseCase;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public String receiveTelegramUpdate(@RequestBody Update telegramUpdate){
        messageUseCase.handle(telegramUpdate);
        return "OK";
    }
}
