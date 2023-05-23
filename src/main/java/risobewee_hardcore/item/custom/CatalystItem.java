package risobewee_hardcore.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import risobewee_hardcore.block.ModBlocks;
import risobewee_hardcore.block.custom.CryptPortalBlock;
import risobewee_hardcore.item.ModItems;
import risobewee_hardcore.util.InventoryUtil;
import risobewee_hardcore.world.dimension.ModDimensions;

public class CatalystItem extends Item {
    public CatalystItem() {
        super(new Properties()
                .tab(CreativeModeTab.TAB_MISC)
                .stacksTo(1)
                .rarity(Rarity.RARE)
                .fireResistant()
        );
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if(context.getPlayer() != null) {
            if(context.getPlayer().level.dimension() == ModDimensions.CRYPT_KEY
                    || context.getPlayer().level.dimension() == Level.OVERWORLD) {
                for(Direction direction : Direction.Plane.VERTICAL) {
                    BlockPos framePos = context.getClickedPos().relative(direction);
                    if(((CryptPortalBlock) ModBlocks.CRYPT_PORTAL.get()).trySpawnPortal(context.getLevel(), framePos)) {
                        context.getLevel().playSound(context.getPlayer(), framePos,
                                SoundEvents.PORTAL_TRIGGER, SoundSource.BLOCKS, 1.0F, 1.0F);

                        //REMOVES KEY FROM PLAYER INVENTORY
                        //This should always be true at this point (useOn within the item context itself.)
                        if(InventoryUtil.hasPlayerStackInInventory(context.getPlayer(), ModItems.PORTAL_KEY.get())){
                            InventoryUtil.replaceHeldItemWithEmpty(context.getPlayer(), ModItems.PORTAL_KEY.get());
                        }

                        return InteractionResult.CONSUME;
                    }
                    else return InteractionResult.FAIL;
                }
            }
        }
        return InteractionResult.FAIL;
    }
}