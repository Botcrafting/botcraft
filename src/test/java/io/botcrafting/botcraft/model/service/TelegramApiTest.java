package io.botcrafting.botcraft.model.service;

import io.botcrafting.botcraft.model.request.TelegramMessageTextRequest;
import io.botcrafting.botcraft.model.service.api.TelegramApi;
import io.botcrafting.botcraft.model.service.handler.MessageHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TelegramApiTest {
    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    private MessageHandler sendMessageToTelegram;

    @InjectMocks
    private TelegramApi telegramApi;

    @Test
    public void callsSendMessaqeText() {
        List<String> responses = new ArrayList<>();
        responses.addAll(Arrays.asList("Hi there, I'm Scilobot! Let's make some science today?",
                "https://media1.tenor.com/images/1701ca955ab813437e4457667c980123/tenor.gif?itemid=12528285",
                "https://media.giphy.com/media/jlfulL2NK1D2M/giphy.gif", "That's obvious, it's 42!!!"));
        responses.forEach(response -> {
            telegramApi.sendMessageText(new TelegramMessageTextRequest(608316978, response));
            //String url = "https://api.telegram.org/bot" + System.getenv("botToken") + "/sendMessage";
            String url = "https://api.telegram.org/bot" + System.getenv("botBarzaiToken") + "/sendMessage";
            MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
            parts.add("chat_id", 608316978);
            parts.add("text", response);
            Mockito.verify(restTemplate, Mockito.atLeastOnce()).postForObject(url, parts, String.class);
        });
    }
}
