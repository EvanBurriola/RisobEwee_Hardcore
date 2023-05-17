package risobewee_hardcore.block.entity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import risobewee_hardcore.RisobEwee_HardcoreMain;
import risobewee_hardcore.block.ModBlocks;
import risobewee_hardcore.block.entity.custom.Resurrection_BlockEntity;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, RisobEwee_HardcoreMain.MOD_ID);

    public static final RegistryObject<BlockEntityType<Resurrection_BlockEntity>> RESURRECTION_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("resurrection_block_entity", () ->
                    BlockEntityType.Builder.of(Resurrection_BlockEntity::new,
                            ModBlocks.RESURRECTION_BLOCK.get()).build(null));


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
