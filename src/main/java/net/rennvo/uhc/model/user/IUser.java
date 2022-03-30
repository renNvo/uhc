package net.rennvo.uhc.model.user;

import net.rennvo.uhc.model.arena.IArena;

import java.util.UUID;

public interface IUser {

    public UUID getUniqueId();

    public IArena getArena();

    public void setArena(IArena arena);

}
