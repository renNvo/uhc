package net.rennvo.uhc.listener;

import net.rennvo.uhc.model.arena.IArena;
import net.rennvo.uhc.model.user.IUser;
import net.rennvo.uhc.service.UserManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.Plugin;

public class PlayerDeathListener implements Listener {

    private final Plugin      plugin;
    private final UserManager userManager;

    public PlayerDeathListener(Plugin plugin, UserManager userManager) {
        this.plugin      = plugin;
        this.userManager = userManager;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        final Player player = event.getEntity();
        final IUser  user   = userManager.get(player.getUniqueId());

        if (user.getArena() == null) {
            return;
        }

        final IArena arena = user.getArena();

        if (!arena.isActive()) {
            //Testing
            Bukkit.getScheduler().runTaskLater(plugin, () -> player.spigot().respawn(), 5L);
            //might be
            //((CraftPlayer) player).getHandle().playerConnection.a(new PacketPlayInClientCommand(EnumClientCommand.PERFORM_RESPAWN));
            return;
        }

        arena.getParticipatingList().remove(user);
        user.setArena(null);
        //player.teleport(#Spawn);

        if(arena.getParticipatingList().size() < 1) {
            arena.setActivity(false);
            //no one won
        } else if(arena.getParticipatingList().size() == 1) {
            arena.setActivity(false);

            IUser winner = arena.getParticipatingList().get(0);
            Player playerWinner = Bukkit.getPlayer(winner.getUniqueId());

            playerWinner.sendMessage("You won!");
            //playerWinner.teleport(#Spawn);
        }

        arena.getSign().setLine(3, arena.getParticipatingList().size() + "/9");
    }
}
