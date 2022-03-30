package net.rennvo.uhc.utilities;

import net.rennvo.uhc.model.arena.IArena;
import org.bukkit.Bukkit;

public class PlayerUtilities {

    public static void sendMessageToArena(IArena arena, String message) {
        arena.getParticipatingList().stream()
                .map(Bukkit::getPlayer)
                .forEach(player -> player.sendMessage(message));
    }
}
