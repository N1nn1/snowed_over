package com.ninni.snowed_over.mixin;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.ticks.ScheduledTick;
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
    @Final @Shadow public static IntegerProperty DISTANCE;

    private static final BooleanProperty SNOWY= BlockStateProperties.SNOWY;
    public LeavesBlockMixin(Properties settings) {super(settings);}


    @Inject(at = @At("TAIL"), method = "<init>")
    public void constructor(CallbackInfo info) {this.registerDefaultState(this.stateDefinition.any().setValue(SNOWY, false).setValue(LeavesBlock.DISTANCE, 7).setValue(LeavesBlock.WATERLOGGED, false).setValue(LeavesBlock.PERSISTENT, false));}

    @SuppressWarnings("deprecation")
    @Inject(at = @At("RETURN"), method = "updateShape", cancellable = true)
    public void updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos, CallbackInfoReturnable<BlockState> cir) {
        int i = getDistanceFromLog(neighborState) + 1;
        if (i != 1 || state.getValue(DISTANCE) != i) {
            world.getBlockTicks().schedule(ScheduledTick.probe(this, pos));
        }
        cir.setReturnValue( direction == Direction.UP ? state.setValue(SNOWY, isSnow(neighborState)) : super.updateShape(state, direction, neighborState, world, pos, neighborPos));
    }

    @Inject(at = @At("RETURN"), method = "getStateForPlacement", cancellable = true)
    public void getStateForPlacement(BlockPlaceContext ctx, CallbackInfoReturnable<BlockState> cir) {
        BlockState blockState = ctx.getLevel().getBlockState(ctx.getClickedPos().above());
        cir.setReturnValue(this.defaultBlockState().setValue(SNOWY, isSnow(blockState)).setValue(PERSISTENT, true));
    }

    private static boolean isSnow(BlockState state) {return state.is(BlockTags.SNOW);}

    @Inject(at = @At("TAIL"), method = "createBlockStateDefinition")
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder, CallbackInfo ci) {builder.add(SNOWY);}

    private static int getDistanceFromLog(BlockState state) {
        if (state.is(BlockTags.LOGS)) {
            return 0;
        } else {
            return state.getBlock() instanceof LeavesBlock ? state.getValue(DISTANCE) : 7;
        }
    }
}

