package risobewee_hardcore.block.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import risobewee_hardcore.block.entity.ModBlockEntities;

public class Resurrection_BlockEntity extends BlockEntity {
    public Resurrection_BlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.RESURRECTION_BLOCK_ENTITY.get(), pPos, pBlockState);
    }
}
