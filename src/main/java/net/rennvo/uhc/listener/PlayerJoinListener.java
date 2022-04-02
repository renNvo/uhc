package net.rennvo.uhc.listener;

import net.rennvo.uhc.model.user.UserImpl;
import net.rennvo.uhc.service.UserManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private final UserManager userManager;

    public PlayerJoinListener(UserManager userManager) {
        this.userManager = userManager;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();

        userManager.getUserMap().put(player.getUniqueId(), new UserImpl(player.getUniqueId()));
    }
}
