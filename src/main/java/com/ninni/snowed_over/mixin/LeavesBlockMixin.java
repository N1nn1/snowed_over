package com.ninni.snowed_over.mixin;


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.tick.OrderedTick;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LeavesBlock.class)
public class LeavesBlockMixin extends Block {
    @Final @Shadow public static BooleanProperty PERSISTENT;
    @Final @Shadow public static IntProperty DISTANCE;

    private static final BooleanProperty SNOWY= Properties.SNOWY;
    public LeavesBlockMixin(Settings settings) {super(settings);}


    @Inject(at = @At("TAIL"), method = "<init>")
    public void constructor(CallbackInfo info) {this.setDefaultState(this.stateManager.getDefaultState().with(SNOWY, false).with(LeavesBlock.DISTANCE, 7).with(LeavesBlock.PERSISTENT, false));}

    @SuppressWarnings("deprecation")
    @Inject(at = @At("RETURN"), method = "getStateForNeighborUpdate", cancellable = true)
    public void getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos, CallbackInfoReturnable<BlockState> cir) {
        int i = getDistanceFromLog(neighborState) + 1;
        if (i != 1 || state.get(DISTANCE) != i) {
            world.getBlockTickScheduler().scheduleTick(OrderedTick.create(this, pos));
        }
        cir.setReturnValue( direction == Direction.UP ? state.with(SNOWY, isSnow(neighborState)) : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos));
    }

    @Inject(at = @At("RETURN"), method = "getPlacementState", cancellable = true)
    public void getPlacementState(ItemPlacementContext ctx, CallbackInfoReturnable<BlockState> cir) {
        BlockState blockState = ctx.getWorld().getBlockState(ctx.getBlockPos().up());
        cir.setReturnValue(this.getDefaultState().with(SNOWY, isSnow(blockState)).with(PERSISTENT, true));
    }

    private static boolean isSnow(BlockState state) {return state.isIn(BlockTags.SNOW);}

    @Inject(at = @At("TAIL"), method = "appendProperties")
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder, CallbackInfo ci) {builder.add(SNOWY);}

    private static int getDistanceFromLog(BlockState state) {
        if (state.isIn(BlockTags.LOGS)) {
            return 0;
        } else {
            return state.getBlock() instanceof LeavesBlock ? state.get(DISTANCE) : 7;
        }
    }
}

