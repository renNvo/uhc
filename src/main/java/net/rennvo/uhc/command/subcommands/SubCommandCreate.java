package net.rennvo.uhc.command.subcommands;

import net.rennvo.uhc.model.arena.DefaultArena;
import net.rennvo.uhc.model.arena.IArena;
import net.rennvo.uhc.command.SubCommand;
import net.rennvo.uhc.service.ArenaManager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class SubCommandCreate implements SubCommand {

    private final ArenaManager arenaManager;

    public SubCommandCreate(ArenaManager arenaManager) {
        this.arenaManager = arenaManager;
    }

    @Override
    public void execute(Player player, String[] args) {
        String name  = args[0];
        World  world = Bukkit.getWorld("name");

        if(arenaManager.getArenaMap().containsKey(name)) {
            player.sendMessage("Arena with name '" + name + "' is exist");
            return;
        }

        if(world == null) {
            player.sendMessage("At first you have to create world.");
            return;
        }

        IArena arena = new DefaultArena(name, world, true);
        arenaManager.getArenaMap().put(name, arena);

        player.sendMessage("Arena '" + name + "' has been created");
    }

    @Override
    public int arguments() {
        return 1;
    }

    @Override
    public String permission() {
        return "uhc.create";
    }
}
