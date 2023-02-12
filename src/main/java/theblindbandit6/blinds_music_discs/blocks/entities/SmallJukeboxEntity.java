package theblindbandit6.blinds_music_discs.blocks.entities;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.util.Clearable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import theblindbandit6.blinds_music_discs.blocks.custom.SmallJukebox;

public class SmallJukeboxEntity
        extends BlockEntity
        implements Clearable {
    private ItemStack record = ItemStack.EMPTY;
    private int ticksThisSecond;
    private long tickCount;
    private long recordStartTick;
    private boolean isPlaying;

    public SmallJukeboxEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.SMALL_JUKEBOX_ENTITY, pos, state);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        if (nbt.contains("RecordItem", NbtElement.COMPOUND_TYPE)) {
            this.setRecord(ItemStack.fromNbt(nbt.getCompound("RecordItem")));
        }
        this.isPlaying = nbt.getBoolean("IsPlaying");
        this.recordStartTick = nbt.getLong("RecordStartTick");
        this.tickCount = nbt.getLong("TickCount");
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        if (!this.getRecord().isEmpty()) {
            nbt.put("RecordItem", this.getRecord().writeNbt(new NbtCompound()));
        }
        nbt.putBoolean("IsPlaying", this.isPlaying);
        nbt.putLong("RecordStartTick", this.recordStartTick);
        nbt.putLong("TickCount", this.tickCount);
    }

    public ItemStack getRecord() {
        return this.record;
    }

    public void setRecord(ItemStack stack) {
        this.record = stack;
        this.markDirty();
    }

    public void startPlaying() {
        this.recordStartTick = this.tickCount;
        this.isPlaying = true;
    }

    @Override
    public void clear() {
        this.setRecord(ItemStack.EMPTY);
        this.isPlaying = false;
    }

    public static void tick(World world, BlockPos pos, BlockState state, SmallJukeboxEntity blockEntity) {
        Item item;
        ++blockEntity.ticksThisSecond;
        if (SmallJukeboxEntity.isPlayingRecord(state, blockEntity) && (item = blockEntity.getRecord().getItem()) instanceof MusicDiscItem) {
            MusicDiscItem musicDiscItem = (MusicDiscItem)item;
            if (SmallJukeboxEntity.isSongFinished(blockEntity, musicDiscItem)) {
                world.emitGameEvent(GameEvent.JUKEBOX_STOP_PLAY, pos, GameEvent.Emitter.of(state));
                blockEntity.isPlaying = false;
            } else if (SmallJukeboxEntity.hasSecondPassed(blockEntity)) {
                blockEntity.ticksThisSecond = 0;
                world.emitGameEvent(GameEvent.JUKEBOX_PLAY, pos, GameEvent.Emitter.of(state));
            }
        }
        ++blockEntity.tickCount;
    }

    private static boolean isPlayingRecord(BlockState state, SmallJukeboxEntity blockEntity) {
        return state.get(SmallJukebox.HAS_RECORD) != false && blockEntity.isPlaying;
    }

    private static boolean isSongFinished(SmallJukeboxEntity blockEntity, MusicDiscItem musicDisc) {
        return blockEntity.tickCount >= blockEntity.recordStartTick + (long)musicDisc.getSongLengthInTicks();
    }

    private static boolean hasSecondPassed(SmallJukeboxEntity blockEntity) {
        return blockEntity.ticksThisSecond >= 20;
    }
}
