package risobewee_hardcore.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import risobewee_hardcore.RisobEwee_HardcoreMain;
import risobewee_hardcore.item.custom.CatalystItem;
import risobewee_hardcore.item.custom.TotemOfResurrection;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, RisobEwee_HardcoreMain.MOD_ID);

    public static final RegistryObject<Item> TOTEM_OF_RESURRECTION = ITEMS.register("totem_of_resurrection",
            () -> new TotemOfResurrection(new Item.Properties().tab(CreativeModeTab.TAB_MISC).rarity(Rarity.EPIC).stacksTo(1).durability(1).fireResistant()));

    public static final RegistryObject<Item> PORTAL_KEY = ITEMS.register("portal_key", CatalystItem::new);

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
