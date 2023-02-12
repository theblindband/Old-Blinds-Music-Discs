package theblindbandit6.blinds_music_discs.blocks.custom;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;
import theblindbandit6.blinds_music_discs.blocks.entities.ModBlockEntityTypes;
import theblindbandit6.blinds_music_discs.blocks.entities.SmallJukeboxEntity;


public class SmallJukebox extends BlockWithEntity{
    public static final BooleanProperty HAS_RECORD = Properties.HAS_RECORD;
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    private static VoxelShape SHAPE_NS_EMPTY = Block.createCuboidShape(1, 0, 2, 15, 3, 14);
    private static VoxelShape SHAPE_EW_EMPTY = Block.createCuboidShape(2, 0, 1, 14, 3, 15);
    private static VoxelShape SHAPE_NS_DISC = Block.createCuboidShape(1, 0, 2, 15, 5, 14);
    private static VoxelShape SHAPE_EW_DISC = Block.createCuboidShape(2, 0, 1, 14, 5, 15);

    public SmallJukebox(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(HAS_RECORD, false));
    }

    /*Block Model*/
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (!(state.get(HAS_RECORD))) {
            switch (state.get(FACING)) {
                case EAST, WEST:
                    return SHAPE_EW_DISC;
                default:
                    return SHAPE_NS_DISC;
            }
        }else{
            switch (state.get(FACING)) {
                case EAST, WEST:
                    return SHAPE_EW_EMPTY;
                default:
                    return SHAPE_NS_EMPTY;
            }
        }
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    /*Block Entity*/
    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        NbtCompound nbtCompound = BlockItem.getBlockEntityNbt(itemStack);
        if (nbtCompound != null && nbtCompound.contains("RecordItem")) {
            world.setBlockState(pos, state.with(HAS_RECORD, true));
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(!world.isClient() && hand == Hand.MAIN_HAND) {
            if (state.get(HAS_RECORD).booleanValue()) {
                this.removeRecord(world, pos);
                world.emitGameEvent(GameEvent.JUKEBOX_STOP_PLAY, pos, GameEvent.Emitter.of(state));
                world.setBlockState(pos, state.with(HAS_RECORD, false), Block.NOTIFY_LISTENERS);
                world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(player, state));
                return ActionResult.success(world.isClient);
            }
        }
        return ActionResult.PASS;
    }

    public void setRecord(@Nullable Entity user, WorldAccess world, BlockPos pos, BlockState state, ItemStack stack) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof SmallJukeboxEntity) {
            SmallJukeboxEntity smallJukeboxEntity = (SmallJukeboxEntity)blockEntity;
            smallJukeboxEntity.setRecord(stack.copy());
            smallJukeboxEntity.startPlaying();
            world.setBlockState(pos, state.with(HAS_RECORD, true), Block.NOTIFY_LISTENERS);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(user, state));
        }
    }

    private void removeRecord(World world, BlockPos pos) {
        if (world.isClient) {
            return;
        }
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (!(blockEntity instanceof SmallJukeboxEntity)) {
            return;
        }
        SmallJukeboxEntity smallJukeboxEntity = (SmallJukeboxEntity)blockEntity;
        ItemStack itemStack = smallJukeboxEntity.getRecord();
        if (itemStack.isEmpty()) {
            return;
        }
        world.syncWorldEvent(WorldEvents.MUSIC_DISC_PLAYED, pos, 0);
        smallJukeboxEntity.clear();
        float f = 0.7f;
        double d = (double)(world.random.nextFloat() * 0.7f) + (double)0.15f;
        double e = (double)(world.random.nextFloat() * 0.7f) + 0.06000000238418579 + 0.6;
        double g = (double)(world.random.nextFloat() * 0.7f) + (double)0.15f;
        ItemStack itemStack2 = itemStack.copy();
        ItemEntity itemEntity = new ItemEntity(world, (double)pos.getX() + d, (double)pos.getY() + e - 0.7, (double)pos.getZ() + g, itemStack2);
        itemEntity.setToDefaultPickupDelay();
        world.spawnEntity(itemEntity);
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.isOf(newState.getBlock())) {
            return;
        }
        this.removeRecord(world, pos);
        super.onStateReplaced(state, world, pos, newState, moved);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new SmallJukeboxEntity(pos, state);
    }

    @Override
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        Item item;
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof SmallJukeboxEntity && (item = ((SmallJukeboxEntity)blockEntity).getRecord().getItem()) instanceof MusicDiscItem) {
            return ((MusicDiscItem)item).getComparatorOutput();
        }
        return 0;
    }

    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }


    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(HAS_RECORD);
        builder.add(FACING);
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        if (state.get(HAS_RECORD).booleanValue()) {
            return SmallJukebox.checkType(type, ModBlockEntityTypes.SMALL_JUKEBOX_ENTITY, SmallJukeboxEntity::tick);
        }
        return null;
    }
}
