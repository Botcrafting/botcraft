package io.botcrafting.botcraft.core.message;

import java.util.LinkedList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.botcrafting.botcraft.core.message.repliers.BotcraftMentionReplier;
import io.botcrafting.botcraft.core.message.repliers.CthulhuAwakePhraseReplier;
import io.botcrafting.botcraft.core.message.repliers.CthulhuWordReplier;
import io.botcrafting.botcraft.core.message.repliers.EasterEggListCommandReplier;
import io.botcrafting.botcraft.core.message.repliers.ForTheHordePhraseReplier;
import io.botcrafting.botcraft.core.message.repliers.HelloWorldPhraseReplier;
import io.botcrafting.botcraft.core.message.repliers.HelpCommandReplier;
import io.botcrafting.botcraft.core.message.repliers.IsGoingToRainQuestionReplier;
import io.botcrafting.botcraft.core.message.repliers.KillingMonstersPhraseReplier;
import io.botcrafting.botcraft.core.message.repliers.LetsPartyWordReplier;
import io.botcrafting.botcraft.core.message.repliers.SearchBooksReplier;
import io.botcrafting.botcraft.core.message.repliers.StephenKingQuestionReplier;
import io.botcrafting.botcraft.core.message.repliers.WhereToGoQuestionReplier;
import io.botcrafting.botcraft.core.model.Message;

@Component
public class MessageReplierChain {
	private LinkedList<MessageReplier> repliers =  new LinkedList<>();
	
	@Autowired
	private HelpCommandReplier help;
	@Autowired
	private EasterEggListCommandReplier easterEggList;
	@Autowired
	private CthulhuAwakePhraseReplier cthulhuAwake;
	@Autowired
	private CthulhuWordReplier cthulhuWord;
	@Autowired
	private HelloWorldPhraseReplier helloWord;
	@Autowired
	private ForTheHordePhraseReplier horde;
	@Autowired
	private KillingMonstersPhraseReplier monsters;
	@Autowired
	private LetsPartyWordReplier party;
	@Autowired
	private IsGoingToRainQuestionReplier rain;
	@Autowired
	private StephenKingQuestionReplier king;
	@Autowired
	private WhereToGoQuestionReplier whereTo;
	@Autowired
	private SearchBooksReplier books;
	@Autowired
	private BotcraftMentionReplier mention;
	
	@PostConstruct
	public void buildChain() {
		this.repliers.add(help);
		this.repliers.add(easterEggList);
		this.repliers.add(cthulhuAwake);
		this.repliers.add(cthulhuWord);
		this.repliers.add(helloWord);
		this.repliers.add(horde);
		this.repliers.add(monsters);
		this.repliers.add(party);
		this.repliers.add(rain);
		this.repliers.add(whereTo);
		this.repliers.add(king);
		this.repliers.add(books);
		this.repliers.add(mention);
	}
	
	
	public void processMessage(Message message) {
		for(MessageReplier processor : repliers) {
			if(processor.processMessage(message)) {
				break;
			}
				
		}
	}
}
