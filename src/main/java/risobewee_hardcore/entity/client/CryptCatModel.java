package risobewee_hardcore.entity.client;

import net.minecraft.resources.ResourceLocation;
import risobewee_hardcore.RisobEwee_HardcoreMain;
import risobewee_hardcore.entity.custom.CryptCatEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CryptCatModel extends AnimatedGeoModel<CryptCatEntity> {
    @Override
    public ResourceLocation getModelLocation(CryptCatEntity object) {
        return new ResourceLocation(RisobEwee_HardcoreMain.MOD_ID, "geo/crypt_cat.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(CryptCatEntity object) {
        return new ResourceLocation(RisobEwee_HardcoreMain.MOD_ID, "textures/entity/crypt_cat/crypt_cat_texture.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(CryptCatEntity animatable) {
        return new ResourceLocation(RisobEwee_HardcoreMain.MOD_ID, "animations/crypt_cat.animation.json");
    }
}

