package net.rennvo.uhc.listener;

import net.rennvo.uhc.model.arena.IArena;
import net.rennvo.uhc.service.ArenaManager;
import net.rennvo.uhc.service.ArenaService;
import net.rennvo.uhc.service.UserManager;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

public class PlayerInteractListener implements Listener {

    private final Plugin       plugin;
    private final ArenaManager arenaManager;
    private final UserManager  userManager;

    public PlayerInteractListener(Plugin plugin, ArenaManager arenaManager, UserManager userManager) {
        this.plugin       = plugin;
        this.arenaManager = arenaManager;
        this.userManager  = userManager;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        final Player player       = event.getPlayer();
        final Block  clickedBlock = event.getClickedBlock();

        if (clickedBlock == null) {
            return;
        }

        if (clickedBlock.getType() == Material.SIGN || clickedBlock.getType() == Material.SIGN_POST || clickedBlock.getType() == Material.WALL_SIGN) {
            final Sign     sign  = (Sign) clickedBlock;
            final String[] lines = sign.getLines();

            String name = lines[1];

            if (name == null || name.isEmpty()) {
                return;
            }

            IArena arena = arenaManager.get(name);

            if (arena == null) {
                player.sendMessage("Not found");
                return;
            }

            ArenaService.join(plugin, player, userManager.get(player.getUniqueId()), arena);
        }
    }
}
