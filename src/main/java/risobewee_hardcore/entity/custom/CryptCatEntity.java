package risobewee_hardcore.entity.custom;

import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import org.checkerframework.checker.units.qual.A;
import org.jetbrains.annotations.Nullable;
import risobewee_hardcore.RisobEwee_HardcoreMain;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.Random;

public class CryptCatEntity extends Monster implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);
    public CryptCatEntity(EntityType<? extends Monster> entityType, Level level) {

        super(entityType, level);
    }


    public static boolean checkCryptCatSpawnRules(EntityType<CryptCatEntity> pCryptCatEntity, ServerLevelAccessor pLevel, MobSpawnType pSpawnType, BlockPos pPos, Random pRandom) {
        Holder<Biome> holder = pLevel.getBiome(pPos);

        double chance = Math.random();
        if (chance <= 0.5) {
            if (holder.is(Biomes.WARPED_FOREST)) {
                RisobEwee_HardcoreMain.LOGGER.info("amongus"+ (checkMonsterSpawnRules(pCryptCatEntity, pLevel, pSpawnType, pPos, pRandom) && pLevel.getBlockState(pPos.below()).is(BlockTags.NYLIUM)));
                return checkMonsterSpawnRules(pCryptCatEntity, pLevel, pSpawnType, pPos, pRandom) && pLevel.getBlockState(pPos.below()).is(BlockTags.NYLIUM);
            }
        }
        return false;
    }


    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.ATTACK_DAMAGE, 3.0f)
                .add(Attributes.ATTACK_SPEED, 1.0f)
                .add(Attributes.MOVEMENT_SPEED, 0.3f).build();
    }


    protected SoundEvent getAmbientSound() {
        return SoundEvents.CAT_STRAY_AMBIENT;
    }
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.CAT_HURT;
    }
    protected SoundEvent getDeathSound() {
        return SoundEvents.CAT_DEATH;
    }



    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.crypt_cat.run", true));
            return PlayState.CONTINUE;
        }

        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.crypt_cat.idle",true));
        return PlayState.CONTINUE;
    }
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new PanicGoal(this, 1.5D));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller",
                0, this::predicate));

    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}
