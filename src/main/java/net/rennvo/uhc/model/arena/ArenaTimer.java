package net.rennvo.uhc.model.arena;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.rennvo.uhc.service.ArenaManager;
import net.rennvo.uhc.utilities.MathUtilities;
import net.rennvo.uhc.utilities.PlayerUtilities;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class ArenaTimer extends BukkitRunnable {

    private final IArena arena;
    private       int    time = 60;

    public ArenaTimer(IArena arena) {
        this.arena = arena;
    }

    @Override
    public void run() {
        time--;

        if (time <= 0) {
            TextComponent textComponent = new TextComponent("START! GOOD LUCK!");

            arena.getParticipatingList().stream()
                    .map(user -> Bukkit.getPlayer(user.getUniqueId()))
                    .forEach(player -> {

                        int    x = MathUtilities.randomInt(-1240, 1240);
                        int    z = MathUtilities.randomInt(-1240, 1240);
                        double y = arena.getWorld().getHighestBlockYAt(x, z) + 0.5;

                        Location location = new Location(arena.getWorld(), x, y, z);

                        player.teleport(location);
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, textComponent);
                    });

            Bukkit.getScheduler().cancelTask(this.getTaskId());
            arena.setTimer(null);
        } else {

            PlayerUtilities.sendMessageToArena(arena, ChatMessageType.ACTION_BAR, new TextComponent("Time: " + arena + "s"));
        }

    }

    public void setTime(int time) {
        this.time = time;
    }
}
