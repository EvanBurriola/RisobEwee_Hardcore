package risobewee_hardcore.world;

import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import risobewee_hardcore.RisobEwee_HardcoreMain;
import risobewee_hardcore.world.gen.ModEntityGeneration;

@Mod.EventBusSubscriber(modid = RisobEwee_HardcoreMain.MOD_ID)
public class ModWorldEvents {
    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event) {

        ModEntityGeneration.onEntitySpawn(event);
    }
}