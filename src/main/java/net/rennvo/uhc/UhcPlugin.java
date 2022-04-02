package net.rennvo.uhc;

import net.rennvo.uhc.command.UhcCommand;
import net.rennvo.uhc.listener.PlayerInteractListener;
import net.rennvo.uhc.listener.PlayerJoinListener;
import net.rennvo.uhc.listener.PlayerQuitListener;
import net.rennvo.uhc.listener.SignChangeListener;
import net.rennvo.uhc.model.user.UserImpl;
import net.rennvo.uhc.service.ArenaManager;
import net.rennvo.uhc.service.UserManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class UhcPlugin extends JavaPlugin {

    private ArenaManager arenaManager;
    private UserManager  userManager;

    @Override
    public void onEnable() {
        this.arenaManager = new ArenaManager();
        this.userManager  = new UserManager();

        this.getCommand("uhc").setExecutor(new UhcCommand(this, arenaManager, userManager));

        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(userManager), this);
        Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(userManager), this);
        Bukkit.getPluginManager().registerEvents(new SignChangeListener(arenaManager), this);
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(this, arenaManager, userManager), this);

        Bukkit.getOnlinePlayers().stream()
                .map(player -> new UserImpl(player.getUniqueId()))
                .forEach(user -> userManager.getUserMap().put(user.getUniqueId(), user));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
