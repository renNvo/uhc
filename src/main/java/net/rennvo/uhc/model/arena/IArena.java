package net.rennvo.uhc.model.arena;

import org.bukkit.World;

import java.util.List;
import java.util.UUID;

public interface IArena {

    public String getName();

    public World getWorld();

    public boolean isEnabled();

    public void setStatus(boolean status);

    public boolean isActive();

    public void setActivity(boolean activity);

    public List<UUID> getParticipatingList();
}
