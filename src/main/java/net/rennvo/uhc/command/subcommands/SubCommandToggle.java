package net.rennvo.uhc.command.subcommands;

import net.rennvo.uhc.model.arena.IArena;
import net.rennvo.uhc.command.SubCommand;
import net.rennvo.uhc.service.ArenaManager;
import org.bukkit.entity.Player;

public class SubCommandToggle implements SubCommand {

    private final ArenaManager arenaManager;

    public SubCommandToggle(ArenaManager arenaManager) {
        this.arenaManager = arenaManager;
    }

    @Override
    public void execute(Player player, String[] args) {
        String name  = args[0];
        IArena arena = arenaManager.get(name);

        if(arena == null) {
            player.sendMessage("This arena doesn't exists");
            return;
        }

        if(!arena.getParticipatingList().isEmpty()) {
            player.sendMessage("At first you have to kick all players from this arena");
            return;
        }

        arena.setStatus(!arena.isEnabled());

        if(arena.isEnabled()) {
            player.sendMessage("Successfully u enabled arena " + name);
        } else {
            player.sendMessage("Successfully u disabled arena " + name);
        }
    }

    @Override
    public int arguments() {
        return 1;
    }
}
