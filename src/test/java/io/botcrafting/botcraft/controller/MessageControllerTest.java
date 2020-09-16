package io.botcrafting.botcraft.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import io.botcrafting.botcraft.core.message.MessageReplierChain;
import io.botcrafting.botcraft.infra.controller.message.MessageController;
import io.botcrafting.botcraft.infra.telegram.inbound.TelegramUpdateReceived;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MessageController.class)
public class MessageControllerTest {
    @MockBean
    private MessageReplierChain messageReplierChain;

    @Autowired
    private MessageController messageController;

    @MockBean
    private TelegramUpdateReceived telegramUpdate;


    @Test
    public void testIfMessageControllerCallsMessageHandlerService(){
        messageController.receiveTelegramUpdate(telegramUpdate);
        //verify(messageHandler, atLeastOnce()).handle(telegramUpdate);
    }
}
