package theblindbandit6.blinds_music_discs.sounds;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import theblindbandit6.blinds_music_discs.BlindsMusicDiscs;

public class ModSoundEvents {

    public static final SoundEvent MUSIC_DISC_SWEDEN = register("music_disc.music_sweden");
    public static final SoundEvent MUSIC_DISC_DOG = register("music_disc.music_dog");

    private static SoundEvent register(String name) {
        Identifier id = new Identifier(BlindsMusicDiscs.MOD_ID, name);
        return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
    }
}
