package theblindbandit6.blinds_music_discs.blocks.entities;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import theblindbandit6.blinds_music_discs.BlindsMusicDiscs;
import theblindbandit6.blinds_music_discs.blocks.ModBlocks;

public class ModBlockEntityTypes{

    public static final BlockEntityType<SmallJukeboxEntity> SMALL_JUKEBOX_ENTITY = Registry.register(
            Registry.BLOCK_ENTITY_TYPE,
            new Identifier(BlindsMusicDiscs.MOD_ID, "small_jukebox_entity"),
            FabricBlockEntityTypeBuilder.create(SmallJukeboxEntity::new, ModBlocks.SMALL_OAK_JUKEBOX).build()
    );

    public static void registerModBlockEntities() {
        BlindsMusicDiscs.LOGGER.info("Registering Mod Block Entities for " + BlindsMusicDiscs.MOD_ID);
    }
}
