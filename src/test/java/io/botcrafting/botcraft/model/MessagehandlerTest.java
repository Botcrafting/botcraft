package io.botcrafting.botcraft.model;

import io.botcrafting.botcraft.core.service.handler.MessageHandler;
import io.botcrafting.botcraft.infra.mapper.UpdateMapper;
import io.botcrafting.botcraft.infra.service.api.telegram.TelegramApi;
import io.botcrafting.botcraft.infra.telegram.inbound.TelegramUpdateReceived;
import io.botcrafting.botcraft.infra.telegram.outbound.TelegramMessageText;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.atLeastOnce;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessagehandlerTest {
    @Mock
    private TelegramUpdateReceived telegramUpdate;

    @Mock
    private TelegramApi telegramApi;

    @InjectMocks
    private MessageHandler messageHandler;

    @Test
    public void whenHandlerGetsBotcraftMention() {
        List<String> texts = new ArrayList<>(Arrays.asList("botcraft", "E aí, Botcraft, beleza?", "BOTCRAFT",
                "Top, botcraft"));
        texts.forEach(text -> {
            when(telegramUpdate.getReceivedMessage().getText()).thenReturn(text);
            messageHandler.handle(UpdateMapper.map(telegramUpdate));
            //verify(messageService, atLeastOnce()).sendMessageText("Você é um mero mortal, test. Como ousa chamar o meu nome? Minha hora ainda não chegou.", 1);
            verify(telegramApi, atLeastOnce()).sendMessageText(new TelegramMessageText(1, "Você é um mero mortal, test. Como ousa chamar o meu nome? Minha hora ainda não chegou."));
        });
    }

    @Test
    public void whenHandlerGetsAjuda() {
        List<String> texts = new ArrayList<>(Arrays.asList("/ajuda", "/AJUDA",
                "/Ajuda"));
        texts.forEach(text -> {
            when(telegramUpdate.getReceivedMessage().getText()).thenReturn(text);
            messageHandler.handle(UpdateMapper.map(telegramUpdate));
            //verify(messageService, atLeastOnce()).sendMessageText("Ph'nglui mglw'nafh Cthulhu R'lyeh wagah'nagl fhtagn.", 1);
            verify(telegramApi, atLeastOnce()).sendMessageText(new TelegramMessageText(1, "Ph'nglui mglw'nafh Cthulhu R'lyeh wagah'nagl fhtagn."));
        });
    }
}
