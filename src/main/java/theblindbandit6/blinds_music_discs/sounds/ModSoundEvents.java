package theblindbandit6.blinds_music_discs.sounds;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import theblindbandit6.blinds_music_discs.BlindsMusicDiscs;

public class ModSoundEvents {

    public static final SoundEvent DOOR = register("music_disc.music_door");
    public static final SoundEvent DEATH = register("music_disc.music_death");
    public static final SoundEvent WET_HANDS = register("music_disc.music_wet_hands");
    public static final SoundEvent CHIRS = register("music_disc.music_chris");
    public static final SoundEvent SWEDEN = register("music_disc.music_sweden");
    public static final SoundEvent DOG = register("music_disc.music_dog");
    public static final SoundEvent DROOPY_LIKES_YOUR_FACE = register("music_disc.music_droopy_likes_your_face");
    public static final SoundEvent FLAKE = register("music_disc.music_flake");
    public static final SoundEvent KYOTO = register("music_disc.music_kyoto");
    public static final SoundEvent INTRO = register("music_disc.music_intro");

    private static SoundEvent register(String name) {
        Identifier id = new Identifier(BlindsMusicDiscs.MOD_ID, name);
        return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
    }
}
