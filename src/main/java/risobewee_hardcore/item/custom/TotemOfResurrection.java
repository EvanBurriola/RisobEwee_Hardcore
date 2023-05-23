package risobewee_hardcore.item.custom;


import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import risobewee_hardcore.RisobEwee_HardcoreMain;
import risobewee_hardcore.events.ModServerEvents;
import risobewee_hardcore.item.ModItems;
import risobewee_hardcore.util.InventoryUtil;

import javax.annotation.Nullable;
import java.util.List;


public class TotemOfResurrection extends Item {

    public TotemOfResurrection(Properties pProperties) {
        super(pProperties);
    }


    //Display player 'soul' => display name on item description
    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced){
        if(pStack.hasTag()){
            String playerSoul = pStack.getTag().getString("risobewee_hardcore.soul");
            pTooltipComponents.add(new TextComponent(playerSoul));
        }
        super.appendHoverText(pStack,pLevel,pTooltipComponents,pIsAdvanced);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext){
        if(pContext.getLevel().isClientSide){
            RisobEwee_HardcoreMain.LOGGER.info("CLIENTSIDE");
        } else {
            BlockPos posClicked = pContext.getClickedPos();
            Block block = pContext.getLevel().getBlockState(posClicked).getBlock();
            if(block.getRegistryName().toString().equals("risobewee_hardcore:resurrection_block")){
                RisobEwee_HardcoreMain.LOGGER.info("BLOCK CLICKED");
                List<Player> spectators = (List<Player>) pContext.getLevel().players().stream().filter(EntitySelector.NO_SPECTATORS.negate()).toList();

                Player playerSoul = null;
                RisobEwee_HardcoreMain.LOGGER.info("spectators");
                for(Player p : spectators){
                    RisobEwee_HardcoreMain.LOGGER.info(p.getDisplayName().getString());
                    RisobEwee_HardcoreMain.LOGGER.info(pContext.getItemInHand().getTag().getString("risobewee_hardcore.soul"));
                    if(pContext.getItemInHand().hasTag()){
                        if(p.getDisplayName().getString().equals(pContext.getItemInHand().getTag().getString("risobewee_hardcore.soul"))){
                            playerSoul = p;
                            break;
                        }
                    }
                }

                if(playerSoul != null) {
                    ModServerEvents.resurrectFromAltar(playerSoul, posClicked);
                    //Remove player essence from player inventory after summoning
                    if(InventoryUtil.hasPlayerStackInInventory(pContext.getPlayer(), ModItems.TOTEM_OF_RESURRECTION.get())){
                        InventoryUtil.replaceHeldItemWithEmpty(pContext.getPlayer(),ModItems.TOTEM_OF_RESURRECTION.get());
                    }
                }

            }
        }
        return super.useOn(pContext);
    }

}
