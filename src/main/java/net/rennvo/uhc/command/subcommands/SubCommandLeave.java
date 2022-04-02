package net.rennvo.uhc.command.subcommands;

import net.rennvo.uhc.command.SubCommand;
import net.rennvo.uhc.model.arena.IArena;
import net.rennvo.uhc.model.user.IUser;
import net.rennvo.uhc.service.ArenaManager;
import net.rennvo.uhc.service.ArenaService;
import net.rennvo.uhc.service.UserManager;
import org.bukkit.entity.Player;

public class SubCommandLeave implements SubCommand {

    private final UserManager userManager;

    public SubCommandLeave(UserManager userManager) {
        this.userManager  = userManager;
    }

    @Override
    public void execute(Player player, String[] args) {
        IUser user = userManager.get(player.getUniqueId());

        if(user.getArena() == null) {
            player.sendMessage("You aren't participating in any arena");
            return;
        }

        IArena arena = user.getArena();

        if(arena.isActive()) {
            player.sendMessage("Now you can't use this command, if you want leave - u have to quit server");
            return;
        }

        arena.getParticipatingList().remove(user.getUniqueId());
        user.setArena(null);

        player.sendMessage("You left '" + arena.getName() + "' arena");

        ArenaService.leave(player.getName(), arena);
    }

    @Override
    public int arguments() {
        return 0;
    }

    @Override
    public String permission() {
        return "";
    }
}
