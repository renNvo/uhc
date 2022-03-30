package net.rennvo.uhc.command.subcommands;

import net.rennvo.uhc.model.arena.IArena;
import net.rennvo.uhc.command.SubCommand;
import net.rennvo.uhc.service.ArenaManager;
import net.rennvo.uhc.service.ArenaService;
import net.rennvo.uhc.service.UserManager;
import org.bukkit.entity.Player;

public class SubCommandJoin implements SubCommand {

    private final ArenaManager arenaManager;
    private final UserManager userManager;

    public SubCommandJoin(ArenaManager arenaManager, UserManager userManager) {
        this.arenaManager = arenaManager;
        this.userManager  = userManager;
    }

    @Override
    public void execute(Player player, String[] args) {
        String name  = args[0];
        IArena arena = arenaManager.get(name);

        if(arena == null) {
            player.sendMessage("This arena doesn't exists");
            return;
        }

        ArenaService.join(player, userManager.get(player.getUniqueId()), arena);
    }

    @Override
    public int arguments() {
        return 1;
    }

    @Override
    public String permission() {
        return "";
    }
}
