package risobewee_hardcore.events;

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


@Mod.EventBusSubscriber(modid = RisobEwee_HardcoreMain.MOD_ID)
public class ModServerEvents {

    //Need to test for server sided events. May have to use ServerPlayer & Server event handlers..?
    private static void reducePlayerHealth(Player player){
        Component displayName = player.getDisplayName();
        CommandSourceStack cst = player.createCommandSourceStack().withMaximumPermission(2);
        Commands c = new Commands(Commands.CommandSelection.ALL);

        player.setInvulnerable(false);
        c.performCommand(cst, "attribute " + displayName.getString() + " minecraft:generic.max_health base set 10");
        player.hurt(DamageSource.FALL, 10);

        RisobEwee_HardcoreMain.LOGGER.info(displayName.getString() + " had their health reduced!");
    }

    private static void setPlayerGameMode(Player player, GameType mode){
        Component displayName = player.getDisplayName();
        CommandSourceStack cst = player.createCommandSourceStack().withMaximumPermission(2);

        Commands c = new Commands(Commands.CommandSelection.ALL);
        if(mode == GameType.SPECTATOR) {
            c.performCommand(cst, "gamemode spectator " + displayName.getString());
        } else if (mode == GameType.SURVIVAL) {
            c.performCommand(cst, "gamemode survival " + displayName.getString());
        }
    }

    //Event handler: First death makes player respawn in spectator. Second "death" (Next time "Respawn" is clicked)
    // player is set to survival with only 5 hearts.
    @SubscribeEvent
    public static void onRespawnEvent(PlayerEvent.PlayerRespawnEvent event){
        Player player = event.getPlayer();
        if(player.getTags().contains("has_died")) {
            player.removeTag("has_died");
            setPlayerGameMode(player,GameType.SURVIVAL);
            reducePlayerHealth(player);
        } else{
            player.addTag("has_died");
            setPlayerGameMode(player,GameType.SPECTATOR);
        }
        RisobEwee_HardcoreMain.LOGGER.info("onRespawnEvent Triggered");
    }


}
