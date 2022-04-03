package net.rennvo.uhc.listener;

import net.rennvo.uhc.model.arena.IArena;
import net.rennvo.uhc.model.user.IUser;
import net.rennvo.uhc.service.ArenaService;
import net.rennvo.uhc.service.UserManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    private final UserManager userManager;

    public PlayerQuitListener(UserManager userManager) {
        this.userManager = userManager;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        final Player player = event.getPlayer();
        final IUser  user   = userManager.get(player.getUniqueId());

        if(user.getArena() != null) {
            IArena arena = user.getArena();

            arena.getParticipatingList().remove(user);

            if(arena.isActive()) {
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
            } else {
                ArenaService.leave(player.getName(), arena);
            }
        }

        userManager.getUserMap().remove(player.getUniqueId());
    }
}
