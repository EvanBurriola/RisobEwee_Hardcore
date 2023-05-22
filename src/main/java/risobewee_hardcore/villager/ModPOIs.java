package risobewee_hardcore.villager;

import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import risobewee_hardcore.RisobEwee_HardcoreMain;
import risobewee_hardcore.block.ModBlocks;

public class ModPOIs {
    public static final DeferredRegister<PoiType> POI
            = DeferredRegister.create(ForgeRegistries.POI_TYPES, RisobEwee_HardcoreMain.MOD_ID);

    public static final RegistryObject<PoiType> CRYPT_PORTAL =
            POI.register("crypt_portal", () -> new PoiType("crypt_portal",
                    PoiType.getBlockStates(ModBlocks.CRYPT_PORTAL.get()), 0, 1));


    public static void register(IEventBus eventBus) {
        POI.register(eventBus);
    }
}
