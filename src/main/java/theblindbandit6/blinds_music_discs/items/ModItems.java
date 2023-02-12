package theblindbandit6.blinds_music_discs.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import theblindbandit6.blinds_music_discs.BlindsMusicDiscs;
import theblindbandit6.blinds_music_discs.sounds.ModSoundEvents;

public class ModItems {

    //Item Settings
    private static final FabricItemSettings MUSIC_DISC = new FabricItemSettings().maxCount(1).rarity(Rarity.RARE)
            .group(ModItemGroup.BLINDS_MUSIC_DISCS_ITEMGROUP);
    //Music Discs
    //Minecraft Music
    public static final MusicDiscItem SWEDEN = registerMusicDiscItem("music_disc_sweden",
            new MusicDiscItem(1, ModSoundEvents.MUSIC_DISC_SWEDEN, MUSIC_DISC, 215));
    public static final MusicDiscItem DOG = registerMusicDiscItem("music_disc_dog",
            new MusicDiscItem(2, ModSoundEvents.MUSIC_DISC_DOG, MUSIC_DISC, 146));
    //Item Registers
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(BlindsMusicDiscs.MOD_ID, name),item);
    }
    //Music Disc Item Registers
    private static MusicDiscItem registerMusicDiscItem(String name, MusicDiscItem item) {
        return Registry.register(Registry.ITEM, new Identifier(BlindsMusicDiscs.MOD_ID, name),item);
    }
    //Logger Method
    public static void registerModItems() {
        BlindsMusicDiscs.LOGGER.info("Registering Mod Items for " + BlindsMusicDiscs.MOD_ID);
    }
}

//Items found here were removed. Code has been kept in case I decide to re-add them in the future.
    /*
    public static final Item BLANK_DISC = registerItem("blank_disc", new Item(BASE));
    public static final Item BLANK_RECORD_CENTER = registerItem("blank_record_center", new Item(BASE));
    public static final Item RECORD_CENTER_IMPOSTER = registerItem("record_center_imposter", new Item(BASE));
    public static final Item RECORD_CENTER_STARDEW_VALLEY = registerItem("record_center_stardew_valley", new Item(BASE));
    public static final Item RECORD_CENTER_MANTIS_LORDS = registerItem("record_center_mantis_lords", new Item(BASE));
    public static final Item RECORD_CENTER_FROST_MOON = registerItem("record_center_frost_moon", new Item(BASE));
    public static final Item RECORD_CENTER_LOONBOON = registerItem("record_center_loonboon", new Item(BASE));
    public static final Item RECORD_CENTER_ACCUMULA_TOWN = registerItem("record_center_accumula_town", new Item(BASE));
    */
