package net.rennvo.uhc.listener;

import net.rennvo.uhc.model.user.UserImpl;
import net.rennvo.uhc.service.UserManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

public class PlayerJoinListener implements Listener {

    private final Plugin      plugin;
    private final UserManager userManager;

    public PlayerJoinListener(Plugin plugin, UserManager userManager) {
        this.plugin      = plugin;
        this.userManager = userManager;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();

        userManager.getUserMap().put(player.getUniqueId(), new UserImpl(player.getUniqueId()));

        Bukkit.getScheduler().runTaskLater(plugin, () -> player.sendMessage("Teleported")/*player.teleport(#Spawn*/, 5L);
    }
}
