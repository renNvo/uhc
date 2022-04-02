package net.rennvo.uhc.command;

import com.google.common.collect.Maps;
import net.rennvo.uhc.command.subcommands.SubCommandCreate;
import net.rennvo.uhc.command.subcommands.SubCommandDelete;
import net.rennvo.uhc.command.subcommands.SubCommandJoin;
import net.rennvo.uhc.command.subcommands.SubCommandLeave;
import net.rennvo.uhc.service.ArenaManager;
import net.rennvo.uhc.service.UserManager;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;
import java.util.Map;

public class UhcCommand extends AbstractCommand {

    private final Map<String, SubCommand> subCommandMap = Maps.newConcurrentMap();

    public UhcCommand(Plugin plugin, ArenaManager arenaManager, UserManager userManager) {
        this.subCommandMap.put("create", new SubCommandCreate(arenaManager));
        this.subCommandMap.put("delete", new SubCommandDelete(arenaManager));
        this.subCommandMap.put("join", new SubCommandJoin(plugin, arenaManager, userManager));
        this.subCommandMap.put("leave", new SubCommandLeave(userManager));
        this.subCommandMap.put("toggle", new SubCommandCreate(arenaManager));
    }

    @Override
    public void execute(Player player, String[] args) {
        if(args.length == 0) {
            player.sendMessage("[usage]");
            return;
        }

        SubCommand subCommand = subCommandMap.get(args[0]);

        if(subCommand == null) {
            player.sendMessage("[usage]");
            return;
        }

        if(!subCommand.permission().isEmpty() || !player.hasPermission(subCommand.permission())) {
            player.sendMessage("You don't have permission for that");
        }

        if(subCommand.arguments() >= args.length) {
            subCommand.execute(player, Arrays.copyOfRange(args, 1, args.length));
        } else {
            player.sendMessage("[usage]");
        }
    }
}
