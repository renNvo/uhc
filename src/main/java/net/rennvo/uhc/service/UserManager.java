package net.rennvo.uhc.service;

import com.google.common.collect.Maps;
import net.rennvo.uhc.model.user.IUser;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class UserManager {

    private final Map<UUID, IUser> userMap = Maps.newConcurrentMap();

    public IUser get(UUID uniqueId) {
        return this.userMap.get(uniqueId);
    }

    public Map<UUID, IUser> getUserMap() {
        return userMap;
    }
}
