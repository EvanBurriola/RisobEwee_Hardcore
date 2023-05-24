package risobewee_hardcore.events;


import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import risobewee_hardcore.RisobEwee_HardcoreMain;
import risobewee_hardcore.entity.ModEntityTypes;
import risobewee_hardcore.entity.custom.CryptCatEntity;
import risobewee_hardcore.events.loot.EvokerLootModifier;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = RisobEwee_HardcoreMain.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.CRYPT_CAT.get(), CryptCatEntity.setAttributes());
    }

    @SubscribeEvent
    public static void registerModifierSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {
        event.getRegistry().registerAll(
                new EvokerLootModifier.Serializer().setRegistryName
                        (new ResourceLocation(RisobEwee_HardcoreMain.MOD_ID,"evoker_loot_modifier"))
        );
    }
}
