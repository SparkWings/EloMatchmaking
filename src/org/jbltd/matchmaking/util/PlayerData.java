package org.jbltd.matchmaking.util;

import org.bukkit.entity.Player;

public class PlayerData {

    private Player _player;
    private int _ping, _rating;

    
    public PlayerData(Player player, int ping, int rating) {
	this._player = player;
	this._ping = ping;
	this._rating = rating;
    }

    public Player getPlayer()
    {
	return _player;
    }
    
    public int getPing() {
	return _ping;
    }

    public int getElo() {
	return _rating;
    }

    public double getCombatValue()
    {
	return getElo() / getPing();
    }
    
}