package theblindbandit6.blinds_music_discs.blocks;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import theblindbandit6.blinds_music_discs.BlindsMusicDiscs;
import theblindbandit6.blinds_music_discs.blocks.custom.SmallJukebox;
import theblindbandit6.blinds_music_discs.items.ModItemGroup;

public class ModBlocks {

    //Blocks
    public static final Block SMALL_JUKEBOX = registerBlock("small_jukebox",
            new SmallJukebox(FabricBlockSettings.copy(Blocks.JUKEBOX)),
            ModItemGroup.BLINDS_MUSIC_DISCS_ITEMGROUP);
    //Block Registers
    private static Block registerBlock(String name, Block block, ItemGroup tab) {
        registerBlockItem(name, block, tab);
        return Registry.register(Registry.BLOCK, new Identifier(BlindsMusicDiscs.MOD_ID, name),block);
    }
    //Block Item Registers
    private static Item registerBlockItem(String name, Block block, ItemGroup tab) {
        return Registry.register(Registry.ITEM, new Identifier(BlindsMusicDiscs.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(tab)));
    }
    //Logger Method
    public static void registerModBlocks() {
        BlindsMusicDiscs.LOGGER.info("Registering Mod Blocks for " + BlindsMusicDiscs.MOD_ID);
    }
}
