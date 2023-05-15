package risobewee_hardcore.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import risobewee_hardcore.RisobEwee_HardcoreMain;

public class TotemOfResurrection extends Item {
    public TotemOfResurrection(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext){
        if(pContext.getLevel().isClientSide){
            BlockPos posClicked = pContext.getClickedPos();
            Block block = pContext.getLevel().getBlockState(posClicked).getBlock();
            if(block.getRegistryName().toString().equals("risobewee_hardcore:resurrection_block")){
                RisobEwee_HardcoreMain.LOGGER.info("BLOCK CLICKED");
            }
        }
        return super.useOn(pContext);
    }
}
