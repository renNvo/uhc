package net.rennvo.uhc;

import net.rennvo.uhc.service.ArenaManager;
import net.rennvo.uhc.service.UserManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class UhcPlugin extends JavaPlugin {

    private ArenaManager arenaManager;
    private UserManager userManager;

    @Override
    public void onEnable() {
        this.arenaManager = new ArenaManager();
        this.userManager = new UserManager();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
