package risobewee_hardcore.world.dimension;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import risobewee_hardcore.RisobEwee_HardcoreMain;

public class ModDimensions {
    public static final ResourceKey<Level> CRYPT_KEY = ResourceKey.create(Registry.DIMENSION_REGISTRY,
            new ResourceLocation(RisobEwee_HardcoreMain.MOD_ID, "crypt"));
    public static final ResourceKey<DimensionType> CRYPT_TYPE =
            ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, CRYPT_KEY.getRegistryName());

    public static void register() {
        System.out.println("Registering ModDimensions for " + RisobEwee_HardcoreMain.MOD_ID);
    }
}
