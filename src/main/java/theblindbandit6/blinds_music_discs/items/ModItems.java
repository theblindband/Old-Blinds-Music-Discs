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
    //Publish Version Discs
    public static final MusicDiscItem MUSIC_DISC_DOOR = registerMusicDiscItem("music_disc_door",
            new MusicDiscItem(1, ModSoundEvents.DOOR, MUSIC_DISC, 111));
    public static final MusicDiscItem MUSIC_DISC_DEATH = registerMusicDiscItem("music_disc_death",
            new MusicDiscItem(2, ModSoundEvents.DEATH, MUSIC_DISC, 41));
    public static final MusicDiscItem MUSIC_DISC_WET_HANDS = registerMusicDiscItem("music_disc_wet_hands",
            new MusicDiscItem(3, ModSoundEvents.WET_HANDS, MUSIC_DISC, 90));
    public static final MusicDiscItem MUSIC_DISC_CHIRS = registerMusicDiscItem("music_disc_chris",
            new MusicDiscItem(4, ModSoundEvents.CHIRS, MUSIC_DISC, 87));
    public static final MusicDiscItem MUSIC_DISC_SWEDEN = registerMusicDiscItem("music_disc_sweden",
            new MusicDiscItem(5, ModSoundEvents.SWEDEN, MUSIC_DISC, 215));
    public static final MusicDiscItem MUSIC_DISC_DOG = registerMusicDiscItem("music_disc_dog",
            new MusicDiscItem(6, ModSoundEvents.DOG, MUSIC_DISC, 146));
    public static final MusicDiscItem MUSIC_DISC_DROOPY_LIKES_YOUR_FACE = registerMusicDiscItem("music_disc_droopy_likes_your_face",
            new MusicDiscItem(7, ModSoundEvents.DROOPY_LIKES_YOUR_FACE, MUSIC_DISC, 116));
    public static final MusicDiscItem MUSIC_DISC_FLAKE = registerMusicDiscItem("music_disc_flake",
            new MusicDiscItem(8, ModSoundEvents.FLAKE, MUSIC_DISC, 170));
    public static final MusicDiscItem MUSIC_DISC_KYOTO = registerMusicDiscItem("music_disc_kyoto",
            new MusicDiscItem(9, ModSoundEvents.KYOTO, MUSIC_DISC, 249));
    public static final MusicDiscItem MUSIC_DISC_INTRO = registerMusicDiscItem("music_disc_intro",
            new MusicDiscItem(10, ModSoundEvents.INTRO, MUSIC_DISC, 276));
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
