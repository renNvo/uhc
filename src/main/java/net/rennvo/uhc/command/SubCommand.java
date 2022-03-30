package net.rennvo.uhc.command;

import org.bukkit.entity.Player;

public interface SubCommand {

    public void execute(final Player player, final String[] args);

    public int arguments();

    public String permission();
}
