package theblindbandit6.blinds_music_discs.items;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import theblindbandit6.blinds_music_discs.BlindsMusicDiscs;

public class ModItemGroup {
    public static ItemGroup BLINDS_MUSIC_DISCS_ITEMGROUP = FabricItemGroupBuilder.build(new Identifier
                    (BlindsMusicDiscs.MOD_ID, "blinds_music_discs_itemgroup"), () -> new ItemStack(ModItems.MUSIC_DISC_DOG));
}
