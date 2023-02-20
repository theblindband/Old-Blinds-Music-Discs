package theblindbandit6.blinds_music_discs.blocks;

import net.minecraft.block.Block;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import theblindbandit6.blinds_music_discs.BlindsMusicDiscs;

public class ModBlockTags {
    public static final TagKey<Block> SMALL_JUKEBOXES = TagKey.of(Registry.BLOCK_KEY, new Identifier(BlindsMusicDiscs.MOD_ID, "small_jukeboxes"));
}
