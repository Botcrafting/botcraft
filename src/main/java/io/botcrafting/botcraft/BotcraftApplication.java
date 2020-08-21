package io.botcrafting.botcraft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BotcraftApplication {

	public static void main(String[] args) {
		SpringApplication.run(BotcraftApplication.class, args);
	}

}
