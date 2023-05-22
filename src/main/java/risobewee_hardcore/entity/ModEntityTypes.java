package risobewee_hardcore.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import risobewee_hardcore.RisobEwee_HardcoreMain;
import risobewee_hardcore.entity.custom.CryptCatEntity;
import risobewee_hardcore.entity.custom.CryptKeeperEntity;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITIES, RisobEwee_HardcoreMain.MOD_ID);


    public static final RegistryObject<EntityType<CryptCatEntity>> CRYPT_CAT =
            ENTITY_TYPES.register("crypt_cat",
                    () -> EntityType.Builder.of(CryptCatEntity::new, MobCategory.CREATURE)
                            .sized(.6f, .8f)
                            .build(new ResourceLocation(RisobEwee_HardcoreMain.MOD_ID, "crypt_cat").toString()));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
