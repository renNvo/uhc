package net.rennvo.uhc.command;

import com.google.common.collect.Maps;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Map;

public class UhcCommand extends AbstractCommand {

    private final Map<String, SubCommand> subCommandMap = Maps.newConcurrentMap();

    public UhcCommand() {

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

        if(subCommand.arguments() >= args.length) {
            subCommand.execute(player, Arrays.copyOfRange(args, 1, args.length));
        } else {
            player.sendMessage("[usage]");
        }
    }
}
