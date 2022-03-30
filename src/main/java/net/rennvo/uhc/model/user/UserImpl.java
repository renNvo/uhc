package net.rennvo.uhc.model.user;

import net.rennvo.uhc.model.arena.IArena;

import java.util.UUID;

public class UserImpl implements IUser{

    private final UUID uniqueId;

    private IArena arena;

    public UserImpl(UUID uniqueId) {
        this.uniqueId = uniqueId;
    }

    @Override
    public UUID getUniqueId() {
        return uniqueId;
    }

    @Override
    public IArena getArena() {
        return arena;
    }

    @Override
    public void setArena(IArena arena) {
        this.arena = arena;
    }
}
