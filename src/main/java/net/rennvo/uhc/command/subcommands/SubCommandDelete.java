package net.rennvo.uhc.command.subcommands;

import net.rennvo.uhc.model.arena.IArena;
import net.rennvo.uhc.command.SubCommand;
import net.rennvo.uhc.service.ArenaManager;
import org.bukkit.entity.Player;

public class SubCommandDelete implements SubCommand {

    private final ArenaManager arenaManager;

    public SubCommandDelete(ArenaManager arenaManager) {
        this.arenaManager = arenaManager;
    }

    @Override
    public void execute(Player player, String[] args) {
        String name = args[0];
        IArena arena = arenaManager.get(name);

        if(arena == null) {
            player.sendMessage("This arena doesn't exists");
            return;
        }

        if(arena.isEnabled()) {
            player.sendMessage("At first you have to disable your arena!");
            return;
        }

        arena.setStatus(false);
        player.sendMessage("Successfully you removed arena " + name);
    }

    @Override
    public int arguments() {
        return 1;
    }

    @Override
    public String permission() {
        return "uhc.delete";
    }
}
