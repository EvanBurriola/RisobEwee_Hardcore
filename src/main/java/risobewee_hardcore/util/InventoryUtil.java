package risobewee_hardcore.util;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import risobewee_hardcore.RisobEwee_HardcoreMain;

public class InventoryUtil {
    public static boolean hasPlayerStackInInventory(Player player, Item item) {
        for(int i = 0; i < player.getInventory().getContainerSize(); i++) {
            ItemStack currentStack = player.getInventory().getItem(i);
            if (!currentStack.isEmpty() && currentStack.sameItem(new ItemStack(item))) {
                return true;
            }
        }

        return false;
    }

    public static int getFirstInventoryIndex(Player player, Item item) {
        for(int i = 0; i < player.getInventory().getContainerSize(); i++) {
            ItemStack currentStack = player.getInventory().getItem(i);
            if (!currentStack.isEmpty() && currentStack.sameItem(new ItemStack(item))) {
                return i;
            }
        }

        return -1;
    }

    //Removes item in main or offhand, in that order respectively.
    public static boolean replaceHeldItemWithEmpty(Player player, Item item){
        Inventory inv = player.getInventory();
        if(inv.getSelected().getItem().equals(item)){
            inv.setItem(inv.selected,ItemStack.EMPTY);
            RisobEwee_HardcoreMain.LOGGER.info("Mainhand 'selected' removed");
            return true;
        } else if(inv.getItem(Inventory.SLOT_OFFHAND).getItem().equals(item)){
            inv.setItem(Inventory.SLOT_OFFHAND, ItemStack.EMPTY);
            RisobEwee_HardcoreMain.LOGGER.info("Offhand removed");
            return true;
        }
        RisobEwee_HardcoreMain.LOGGER.info("No item removed");
        return false;
    }
}
