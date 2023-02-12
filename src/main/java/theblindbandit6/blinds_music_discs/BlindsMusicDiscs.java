package theblindbandit6.blinds_music_discs;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static theblindbandit6.blinds_music_discs.blocks.ModBlocks.registerModBlocks;
import static theblindbandit6.blinds_music_discs.blocks.entities.ModBlockEntityTypes.registerModBlockEntities;
import static theblindbandit6.blinds_music_discs.items.ModItems.registerModItems;

public class BlindsMusicDiscs implements ModInitializer {
	public static final String MOD_ID = "blinds_music_discs";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		registerModBlocks();
		registerModItems();
		registerModBlockEntities();

	}
}