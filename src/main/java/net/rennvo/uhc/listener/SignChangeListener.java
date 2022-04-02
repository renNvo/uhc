package net.rennvo.uhc.listener;

import net.rennvo.uhc.model.arena.IArena;
import net.rennvo.uhc.service.ArenaManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignChangeListener implements Listener {

    private final ArenaManager arenaManager;

    public SignChangeListener(ArenaManager arenaManager) {
        this.arenaManager = arenaManager;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onSign(SignChangeEvent event) {
        final String[] lines = event.getLines();

        if(!lines[0].equals("[UHC]")) {
            return;
        }

        String name = lines[1];

        if(name == null || name.isEmpty()) {
            return;
        }

        IArena arena = arenaManager.get(name);

        if(arena == null) {
            event.setLine(1, "Not found");
        } else {
            event.setLine(2, arena.isEnabled() ? "Enabled" : "Disabled");
            event.setLine(3, arena.getParticipatingList().size() + "/9");
        }
    }
}
