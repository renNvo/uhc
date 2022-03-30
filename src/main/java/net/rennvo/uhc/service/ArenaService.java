package net.rennvo.uhc.service;

import net.rennvo.uhc.model.arena.IArena;
import net.rennvo.uhc.model.user.IUser;
import net.rennvo.uhc.utilities.PlayerUtilities;
import org.bukkit.entity.Player;

public class ArenaService {

    public static void join(Player player, IUser user, IArena arena) {

        if(user.getArena() != null) {
            player.sendMessage("You have to leave/end ur current arena");
            return;
        }

        if(!arena.isEnabled()) {
            player.sendMessage("This arena is disabled.");
            return;
        }

        if(arena.isActive()) {
            player.sendMessage("This arena is active.");
            return;
        }

        if(arena.getParticipatingList().size() == 9) {
            player.sendMessage("This arena is full.");
            return;
        }

        user.setArena(arena);
        arena.getParticipatingList().add(user.getUniqueId());

        PlayerUtilities.sendMessageToArena(arena, "Player " + player.getName() + " joined (" + arena.getParticipatingList().size() + "/9)");
    }
}
