package net.rennvo.uhc.command.subcommands;

import net.rennvo.uhc.command.SubCommand;
import net.rennvo.uhc.model.arena.IArena;
import net.rennvo.uhc.model.user.IUser;
import net.rennvo.uhc.service.ArenaService;
import net.rennvo.uhc.service.UserManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class SubCommandKick implements SubCommand {

    private final UserManager userManager;

    public SubCommandKick(UserManager userManager) {
        this.userManager = userManager;
    }

    @Override
    public void execute(Player player, String[] args) {
        Player victim = Bukkit.getPlayer(args[0]);

        if(victim == null) {
            player.sendMessage("Player '" + args[0] + "' not found");
            return;
        }

        IUser user = userManager.get(victim.getUniqueId());

        if(user == null) {
            player.sendMessage("404 - User not found.");
            return;
        }

        if(user.getArena() == null) {
            player.sendMessage("This player is not participating in any arena");
            return;
        }

        IArena arena = user.getArena();

        arena.getParticipatingList().remove(user);
        user.setArena(null);
        //victim.teleport(#Spawn);

        if(arena.isActive()) {
            if(arena.getParticipatingList().size() < 1) {
                arena.setActivity(false);
                //no one won
            } else if(arena.getParticipatingList().size() == 1) {
                arena.setActivity(false);

                IUser winner = arena.getParticipatingList().get(0);
                Player playerWinner = Bukkit.getPlayer(winner.getUniqueId());

                playerWinner.sendMessage("You won!");
                //playerWinner.teleport(#Spawn);
            }

            arena.getSign().setLine(3, arena.getParticipatingList().size() + "/9");
        } else {
            ArenaService.leave(victim.getName(), arena);
        }
    }

    @Override
    public int arguments() {
        return 1;
    }

    @Override
    public String permission() {
        return "uhc.kick";
    }
}
