package org.kurw1k.create_slag_leech.base;

import com.simibubi.create.content.kinetics.base.AbstractEncasedShaftBlock;
import com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockEntity;
import com.simibubi.create.foundation.block.IBE;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.kurw1k.create_slag_leech.index.ModBlockEntityTypes;

public class SlagEncasedShaftBlock extends AbstractEncasedShaftBlock implements IBE<BracketedKineticBlockEntity> {

    public SlagEncasedShaftBlock(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull Direction.Axis getRotationAxis(@NotNull BlockState state) {
        return state.getValue(AXIS);
    }

    @Override
    public boolean hasShaftTowards(LevelReader world, BlockPos pos, BlockState state, Direction face) {
        return face.getAxis() == state.getValue(AXIS);
    }

    @Override
    public Class<BracketedKineticBlockEntity> getBlockEntityClass() {
        return BracketedKineticBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends BracketedKineticBlockEntity> getBlockEntityType() {
        return ModBlockEntityTypes.SLAG_ENCASED_SHAFT.get();
    }
}