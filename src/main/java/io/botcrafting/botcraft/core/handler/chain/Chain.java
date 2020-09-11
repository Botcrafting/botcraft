package io.botcrafting.botcraft.core.handler.chain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.botcrafting.botcraft.core.handler.processors.AnswerBotcraftMention;
import io.botcrafting.botcraft.core.handler.processors.CommandEasterEggList;
import io.botcrafting.botcraft.core.handler.processors.CommandHelp;
import io.botcrafting.botcraft.core.handler.processors.CommandSearchBook;
import io.botcrafting.botcraft.core.handler.processors.ContainsCthulhuWord;
import io.botcrafting.botcraft.core.handler.processors.EasterEggCthulhuAwake;
import io.botcrafting.botcraft.core.handler.processors.MessageProcessor;
import io.botcrafting.botcraft.core.handler.processors.QuestionIsGoingToRain;
import io.botcrafting.botcraft.core.handler.processors.QuestionStephenKing;
import io.botcrafting.botcraft.core.handler.processors.QuestionWhereToGo;
import io.botcrafting.botcraft.core.model.Message;
import io.botcrafting.botcraft.core.service.BookService;
import io.botcrafting.botcraft.core.service.MessageSenderService;

@Component
public class Chain {
	
	@Autowired
	MessageSenderService msgService;
	
	@Autowired
	BookService bookService;
	
	public MessageProcessor buildChain(Message message) {
		CommandHelp help = new CommandHelp(message, msgService, bookService);
		CommandSearchBook book = new CommandSearchBook(message, msgService, bookService);
		CommandEasterEggList easterEggList = new CommandEasterEggList(message, msgService, bookService);
		ContainsCthulhuWord wordCthulhu = new ContainsCthulhuWord(message, msgService, bookService);
		EasterEggCthulhuAwake cthulhuAwake = new EasterEggCthulhuAwake(message, msgService, bookService);
		QuestionIsGoingToRain rain = new QuestionIsGoingToRain(message, msgService, bookService);
		QuestionWhereToGo whereTo = new QuestionWhereToGo(message, msgService, bookService);
		QuestionStephenKing king = new QuestionStephenKing(message, msgService, bookService);
		AnswerBotcraftMention mention = new AnswerBotcraftMention(message, msgService, bookService);
		help.setNextProcessor(book);
		book.setNextProcessor(easterEggList);
		easterEggList.setNextProcessor(wordCthulhu);
		wordCthulhu.setNextProcessor(cthulhuAwake);
		cthulhuAwake.setNextProcessor(rain);
		rain.setNextProcessor(whereTo);
		whereTo.setNextProcessor(king);
		king.setNextProcessor(mention);
		return help;
	}
}
