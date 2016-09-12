package org.jbltd.matchmaking.util;

import org.bukkit.entity.Player;

/**
 * Player Data class. Stores the player's elo and player object. Upon request the class will pull the player's ping.
 * 
 * @param The Player to store
 * @param The Player's elo
 * 
 * @see java.lang.Object
 *
 */
public class PlayerData {

    private Player _player;
    private int _rating;

    public PlayerData(Player player, int ping, int rating) {
	this._player = player;
	this._rating = rating;
    }

    public Player getPlayer() {
	return _player;
    }

    public int getPing() {
	return UtilPing.getPing(_player);
    }

    public int getElo() {
	return _rating;
    }

    public double getCombatValue() {
	return getElo() / getPing();
    }

}
