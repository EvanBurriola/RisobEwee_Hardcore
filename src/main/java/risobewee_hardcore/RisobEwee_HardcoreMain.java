package risobewee_hardcore;

import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import risobewee_hardcore.block.ModBlocks;
import risobewee_hardcore.block.entity.ModBlockEntities;
import risobewee_hardcore.entity.ModEntityTypes;
import risobewee_hardcore.entity.client.CryptCatRenderer;
import risobewee_hardcore.entity.custom.CryptCatEntity;
import risobewee_hardcore.item.ModItems;
import risobewee_hardcore.villager.ModPOIs;
import risobewee_hardcore.world.dimension.ModDimensions;
import risobewee_hardcore.world.structure.ModStructures;
import software.bernie.geckolib3.GeckoLib;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("risobewee_hardcore")
public class RisobEwee_HardcoreMain
{
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String MOD_ID = "risobewee_hardcore";

    public RisobEwee_HardcoreMain()
    {
        // Register the setup method for modloading
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        ModItems.register(eventBus);
        ModBlockEntities.register(eventBus);
        ModBlocks.register(eventBus);
        ModStructures.register(eventBus);
        ModDimensions.register();
        ModEntityTypes.register(eventBus);
        ModPOIs.register(eventBus);

        GeckoLib.initialize();

        eventBus.addListener(this::setup);
        eventBus.addListener(this::clientSetup);
    }
    private void clientSetup(final FMLClientSetupEvent event){

        ItemBlockRenderTypes.setRenderLayer(ModBlocks.RESURRECTION_BLOCK.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.CRYPT_PORTAL.get(), RenderType.translucent());

        EntityRenderers.register(ModEntityTypes.CRYPT_CAT.get(), CryptCatRenderer::new);

    }


    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {

            SpawnPlacements.register(ModEntityTypes.CRYPT_CAT.get(),
                    SpawnPlacements.Type.ON_GROUND,
                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    CryptCatEntity::checkCryptCatSpawnRules);
        });
    }


    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents
    {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent)
        {
            // Register a new block here
            LOGGER.info("HELLO from Register Block");
        }
    }
}
