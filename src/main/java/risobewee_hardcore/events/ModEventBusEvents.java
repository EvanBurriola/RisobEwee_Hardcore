package risobewee_hardcore.events;


import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import risobewee_hardcore.RisobEwee_HardcoreMain;
import risobewee_hardcore.entity.ModEntityTypes;
import risobewee_hardcore.entity.custom.CryptCatEntity;

@Mod.EventBusSubscriber(modid = RisobEwee_HardcoreMain.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.CRYPT_CAT.get(), CryptCatEntity.setAttributes());
    }
}
