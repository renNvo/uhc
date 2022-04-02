package net.rennvo.uhc.utilities;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.rennvo.uhc.model.arena.IArena;
import org.bukkit.Bukkit;

public class PlayerUtilities {

    public static void sendMessageToArena(IArena arena, String message) {
        arena.getParticipatingList().stream()
                .map(user -> Bukkit.getPlayer(user.getUniqueId()))
                .forEach(player -> player.sendMessage(message));
    }
    public static void sendMessageToArena(IArena arena, ChatMessageType type, TextComponent message) {
        arena.getParticipatingList().stream()
                .map(user -> Bukkit.getPlayer(user.getUniqueId()))
                .forEach(player -> player.spigot().sendMessage(type, message));
    }
}
