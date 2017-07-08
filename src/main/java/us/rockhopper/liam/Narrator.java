package us.rockhopper.liam;

import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

import java.lang.Math;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;

import com.google.inject.Inject;

@Plugin(id = "narrator", name = "Narrator", version = "0.1")
public class Narrator {
	@Inject
	private Logger logger;

	@Listener
	public void onServerStart(GameStartedServerEvent event) {
		logger.info("Started Narrator plugin.");

	}

	// Entries
	String entryOne = "filler";
	int entriesTotal = 1;

	@Listener
	public void playerJoin(ClientConnectionEvent.Join event) {
		Player p = event.getTargetEntity();
		Text test = Text.of("test");
		event.getTargetEntity().sendMessage(test);
		int entryNumber = (int) (Math.random() * entriesTotal+1);

		if (true) {
			event.getTargetEntity().sendMessages(Text.of(entryOne));

			ItemStack book = ItemStack.builder().itemType(ItemTypes.WRITTEN_BOOK).build();

			List<Text> pages = new ArrayList<Text>();
			pages.add(Text.of("Page 1"));
			pages.add(Text.of("Page 2"));
			pages.add(Text.of("Page 3"));

			if (book.supports(Keys.BOOK_PAGES)) {
				book.tryOffer(Keys.BOOK_PAGES, pages);
			} else {
				logger.error("BOOK DOES NOT SUPPORT PAGES; WRONG ITEM USED");
			}

			book.offer(Keys.BOOK_AUTHOR, Text.of(TextColors.DARK_PURPLE, TextStyles.BOLD, "The Narrator"));

			book.offer(Keys.DISPLAY_NAME, Text.of(TextColors.GOLD, "Entry 1"));

			p.getInventory().offer(book);
		}
	}
}
