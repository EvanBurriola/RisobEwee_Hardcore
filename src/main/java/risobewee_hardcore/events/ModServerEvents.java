package risobewee_hardcore.events;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import risobewee_hardcore.RisobEwee_HardcoreMain;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import risobewee_hardcore.item.ModItems;


@Mod.EventBusSubscriber(modid = RisobEwee_HardcoreMain.MOD_ID)
public class ModServerEvents {

    @SubscribeEvent
    public static void dropTotemOnDeath(LivingDeathEvent event){
        if(event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if(!player.getTags().contains("full_dead")) {
                ItemStack totemStack = new ItemStack(ModItems.TOTEM_OF_RESURRECTION.get());
                //Put player 'soul' -> display name on item stack
                CompoundTag nbtData = new CompoundTag();
                nbtData.putString("risobewee_hardcore.soul", player.getDisplayName().getString());
                totemStack.setTag(nbtData);

                //Drop totem at death position
                double x = player.getX();
                double y = player.getY();
                double z = player.getZ();
                //Make initial dropped totem unlimited lifetime
                ItemEntity droppedTotem = new ItemEntity(player.getLevel(), x, y, z, totemStack);
                droppedTotem.setUnlimitedLifetime();
                player.getLevel().addFreshEntity(droppedTotem);
                RisobEwee_HardcoreMain.LOGGER.info("Dropped totem");
            }
            RisobEwee_HardcoreMain.LOGGER.info("Full Dead");
        }
    }

    //Event handler: First death makes player respawn in spectator. Second "death" (Next time "Respawn" is clicked)
    // player is set to survival with only 5 hearts.
    @SubscribeEvent
    public static void onRespawnEvent(PlayerEvent.PlayerRespawnEvent event){
        Player player = event.getPlayer();
        if(!player.getTags().contains("full_dead"))
            player.addTag("resurrectable");
        setPlayerGameMode(player, GameType.SPECTATOR);
        RisobEwee_HardcoreMain.LOGGER.info("onRespawnEvent Triggered");
    }



    //Adds tags to indicate the upcoming death(Player revived after first death will have tag death2)
    public static void resurrectFromAltar(Player player, BlockPos alterBlock){
        if(player.getTags().contains("resurrectable")) {
            //To teleport revived player above alter
            double x = alterBlock.above().getX();
            double y = alterBlock.above().getY();
            double z = alterBlock.above().getZ();
            player.removeTag("resurrectable");
            player.teleportTo(x, y, z);
            setPlayerGameMode(player, GameType.SURVIVAL);
            reducePlayerHealth(player, 14);
            player.addTag("full_dead");
            player.getLevel().playLocalSound(x, y, z, SoundEvents.LIGHTNING_BOLT_IMPACT, SoundSource.BLOCKS, 1.0F, 1.0F, false);
        }
        RisobEwee_HardcoreMain.LOGGER.info("Resurrect from altar Triggered");
    }

    //Need to test for server sided events. May have to use ServerPlayer & Server event handlers..?
    private static void reducePlayerHealth(Player player, int newHealth){
        Component displayName = player.getDisplayName();
        CommandSourceStack cst = player.createCommandSourceStack().withMaximumPermission(2);
        Commands c = new Commands(Commands.CommandSelection.ALL);

        player.setInvulnerable(false);
        c.performCommand(cst, "attribute " + displayName.getString() + " minecraft:generic.max_health base set " + Integer.toString(newHealth));
        player.hurt(DamageSource.FALL, newHealth);

        RisobEwee_HardcoreMain.LOGGER.info(displayName.getString() + " had their health reduced!");
    }

    private static void setPlayerGameMode(Player player, GameType mode){
        Component displayName = player.getDisplayName();
        CommandSourceStack cst = player.createCommandSourceStack().withMaximumPermission(2);

        Commands c = new Commands(Commands.CommandSelection.ALL);
        if(mode == GameType.SPECTATOR) {
            c.performCommand(cst, "gamemode spectator " + displayName.getString());
            //Puts spectators into the crypt realm to await revival.
            c.performCommand(cst, "execute in risobewee_hardcore:crypt run tp " + player.getDisplayName().getString() + " ~ 8 ~");
        } else if (mode == GameType.SURVIVAL) {
            c.performCommand(cst, "gamemode survival " + displayName.getString());
        }
    }

}
