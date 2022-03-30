package net.rennvo.uhc.model.arena;

import org.bukkit.World;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DefaultArena implements IArena {

    private final String  name;
    private final World   world;
    private       boolean enabled;

    private       boolean    activity;
    private final List<UUID> participatingList;

    public DefaultArena(String name, World world, boolean isEnabled) {
        this.name              = name;
        this.world             = world;
        this.enabled           = isEnabled;
        this.activity          = false;
        this.participatingList = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public World getWorld() {
        return world;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setStatus(boolean status) {
        this.enabled = status;
    }

    @Override
    public boolean isActive() {
        return activity;
    }

    @Override
    public void setActivity(boolean activity) {
        this.activity = activity;
    }

    @Override
    public List<UUID> getParticipatingList() {
        return participatingList;
    }
}
