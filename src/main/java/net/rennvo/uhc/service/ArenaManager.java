package net.rennvo.uhc.service;

import net.rennvo.uhc.model.arena.IArena;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ArenaManager {

    private final Map<String, IArena> arenaMap = new ConcurrentHashMap<>();

    public Map<String, IArena> getArenaMap() {
        return arenaMap;
    }

    public IArena get(String name) {
        return this.arenaMap.get(name);
    }
}
