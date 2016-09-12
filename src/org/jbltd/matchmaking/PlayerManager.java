package org.jbltd.matchmaking;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

import org.jbltd.matchmaking.util.PlayerData;


public class PlayerManager {

    public HashMap<UUID, PlayerData> MasterData = new HashMap<>();
    public HashSet<UUID> OnlinePlayers = new HashSet<>();
    public HashSet<UUID> InGame = new HashSet<>();

}
