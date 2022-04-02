package net.rennvo.uhc.service;

import net.rennvo.uhc.model.arena.ArenaTimer;
import net.rennvo.uhc.model.arena.IArena;
import net.rennvo.uhc.model.user.IUser;
import net.rennvo.uhc.utilities.PlayerUtilities;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class ArenaService {

    public static void join(Plugin plugin, Player player, IUser user, IArena arena) {

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
        arena.getParticipatingList().add(user);

        PlayerUtilities.sendMessageToArena(arena, "Player " + player.getName() + " joined (" + arena.getParticipatingList().size() + "/9)");

        //TODO configurable

        ArenaTimer timer = arena.getTimer();

        if(arena.getParticipatingList().size() >= 9) {
            timer.setTime(15);
        } else if(arena.getParticipatingList().size() >= 6) {
            timer.setTime(30);
        } else if(arena.getParticipatingList().size() >= 3) {
            timer.setTime(60);

            if(!Bukkit.getScheduler().isCurrentlyRunning(timer.getTaskId())) {
                Bukkit.getScheduler().runTaskTimer(plugin, timer, 20L, 20L);
            }
        }
    }

    public static void leave(String name, IArena arena) {
        ArenaTimer timer = arena.getTimer();

        if(arena.getParticipatingList().size() < 3) {
            Bukkit.getScheduler().cancelTask(timer.getTaskId());
        }

        PlayerUtilities.sendMessageToArena(arena, "Player " + name + " leaved (" + arena.getParticipatingList().size() + "/9)");

    }
}
