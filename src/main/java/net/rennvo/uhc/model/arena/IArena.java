package net.rennvo.uhc.model.arena;

import net.rennvo.uhc.model.user.IUser;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;

import java.util.List;
import java.util.UUID;

public interface IArena {

    public String getName();

    public World getWorld();

    public boolean isEnabled();

    public void setStatus(boolean status);

    public boolean isActive();

    public void setActivity(boolean activity);

    public ArenaTimer getTimer();

    public void setTimer(ArenaTimer timer);

    public List<IUser> getParticipatingList();

    public Sign getSign();

    public void setSign(Sign sign);
}
