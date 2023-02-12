package theblindbandit6.blinds_music_discs.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import theblindbandit6.blinds_music_discs.blocks.ModBlocks;
import theblindbandit6.blinds_music_discs.blocks.custom.SmallJukebox;

@Mixin(MusicDiscItem.class)
public class MusicDiscItemMixin extends Item {
	public MusicDiscItemMixin(Settings settings) {
		super(settings);
	}

	@Inject(at = @At("HEAD"), method = "useOnBlock")
	private ActionResult init(ItemUsageContext context, CallbackInfoReturnable info) {
		// This code is injected into the start of MusicDiscItem.useOnBlock()V
		BlockPos blockPos;
		World world = context.getWorld();
		BlockState blockState = world.getBlockState(blockPos = context.getBlockPos());
		if (!blockState.isOf(ModBlocks.SMALL_JUKEBOX) || blockState.get(SmallJukebox.HAS_RECORD).booleanValue()) {
			return ActionResult.PASS;
		}
		ItemStack itemStack = context.getStack();
		if (!world.isClient) {
			((SmallJukebox)ModBlocks.SMALL_JUKEBOX).setRecord(context.getPlayer(), world, blockPos, blockState, itemStack);
			world.syncWorldEvent(null, WorldEvents.MUSIC_DISC_PLAYED, blockPos, Item.getRawId(this));
			itemStack.decrement(1);
			PlayerEntity playerEntity = context.getPlayer();
			if (playerEntity != null) {
				playerEntity.incrementStat(Stats.PLAY_RECORD);
			}
		}
		return ActionResult.success(world.isClient);
	}

	@Override
	public boolean allowNbtUpdateAnimation(PlayerEntity player, Hand hand, ItemStack oldStack, ItemStack newStack) {
		return super.allowNbtUpdateAnimation(player, hand, oldStack, newStack);
	}
}